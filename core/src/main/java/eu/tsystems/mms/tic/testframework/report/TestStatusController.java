/*
 * (C) Copyright T-Systems Multimedia Solutions GmbH 2018, ..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Peter Lehmann <p.lehmann@t-systems.com>
 *     pele <p.lehmann@t-systems.com>
 */
package eu.tsystems.mms.tic.testframework.report;

import eu.tsystems.mms.tic.testframework.exceptions.FennecSystemException;
import eu.tsystems.mms.tic.testframework.internal.Flags;
import eu.tsystems.mms.tic.testframework.internal.MethodRelations;
import eu.tsystems.mms.tic.testframework.report.external.junit.SimpleReportEntry;
import eu.tsystems.mms.tic.testframework.report.model.context.MethodContext;
import eu.tsystems.mms.tic.testframework.report.utils.ReportUtils;
import eu.tsystems.mms.tic.testframework.utils.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static eu.tsystems.mms.tic.testframework.report.utils.ExecutionContextController.RUN_CONTEXT;

/**
 * Created by pele on 31.08.2016.
 */
public class TestStatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestStatusController.class);
    private static final String PASSED = "PASSED";
    private static final String FAILED = "FAILED";
    private static int testsSuccessful = 0;
    private static int testsSkipped = 0;
    private static int testsFailed = 0;

    /*
    Failure corridor values
     */
    private static int testsFailedHIGH = 0;
    private static int testsFailedMID = 0;
    private static int testsFailedLOW = 0;

    private static int testsFailedRetried = 0;
    private static int testsExpectedFailed = 0;
    private static String SEPERATOR = ",";

    public static JSONObject createStatusJSON() {
        Map<String, Object> statusMap = new HashMap<>();

        statusMap.put("TestsSuccessful", testsSuccessful);
        statusMap.put("TestsSkipped", testsSkipped);
        statusMap.put("TestsFailed", testsFailed);

        statusMap.put("FailureCorridorActive", Flags.FAILURE_CORRIDOR_ACTIVE);

        // set status
        String status;
        String statusMessage;
        boolean statusBool;
        if (Flags.FAILURE_CORRIDOR_ACTIVE) {
            if (FailureCorridor.isCorridorMatched()) {
                status = PASSED;
                statusBool = true;
            } else {
                status = FAILED;
                statusBool = false;
            }
            statusMessage = FailureCorridor.getStatusMessage();
        } else {
            statusMessage = getFinalCountersMessage();
            if (testsSuccessful > 0 && testsSkipped == 0 && testsFailed == 0) {
                status = PASSED;
                statusBool = true;
            } else {
                status = FAILED;
                statusBool = false;
            }
        }
        statusMap.put("Status", status);
        statusMap.put("StatusBool", statusBool);
        statusMap.put("StatusMessage", statusMessage);

        statusMap.put("RunCfg", RUN_CONTEXT.runConfig.RUNCFG);
        statusMap.put("Date", RUN_CONTEXT.startTime.toString());

        return new JSONObject(statusMap);
    }

    public static synchronized void setMethodStatus(MethodContext methodContext, Status status, Method method) {
        /*
        synchronized for safely raising counters...
         */

        if (methodContext == null) {
            throw new FennecSystemException("Test method object is null");
        }

        /*
        check for additional marker annotations
         */
        Annotation[] annotations = method.getAnnotations();
        methodContext.methodTags = Arrays.stream(annotations).collect(Collectors.toList());

        /*
        set status
         */
        if (methodContext.testResult != null && methodContext.testResult.getStatus() == ITestResult.CREATED && status == Status.FAILED) {
            LOGGER.warn("TestNG bug - result status is CREATED, which is wrong. Method status is " + Status.FAILED + ", which is also wrong. Assuming SKIPPED.");
            status = Status.SKIPPED;
        }

        methodContext.status = status;
        methodContext.updateEndTimeRecursive(new Date());

        // announce to run context
        MethodRelations.announceRun(method, methodContext);

        if (methodContext.isConfigMethod()) {
            return;
            // stop here
        }

        // create xml results entry
        SimpleReportEntry reportEntry = new SimpleReportEntry(method.getDeclaringClass().getName(), method.getName());

        // dont count if infoStatusMethod
        if (methodContext.status == Status.INFO) {
            return;
        }

        // raise counters
        switch (status) {
            case NO_RUN:
            case INFO:
                break;

            case PASSED_RETRY:
            case MINOR:
            case PASSED:
                testsSuccessful++;

                // set xml status
                FennecListener.XML_REPORTER.testSucceeded(reportEntry);

                break;

            case FAILED_EXPECTED:
                testsExpectedFailed++;
                break;

            case FAILED_MINOR:
            case FAILED:
                testsFailed++;

                // set xml status
                FennecListener.XML_REPORTER.testFailed(reportEntry, "", "");

                FailureCorridor.Value failureCorridorValue = methodContext.failureCorridorValue;

                if (failureCorridorValue != null) {
                    switch (failureCorridorValue) {
                        case High:
                            testsFailedHIGH++;
                            break;
                        case Mid:
                            testsFailedMID++;
                            break;
                        case Low:
                            testsFailedLOW++;
                            break;
                        default:
                            throw new FennecSystemException("Could not set explicit Failure Corridor value. Missing state: " + failureCorridorValue);
                    }
                    LOGGER.debug("FC: " + testsFailedHIGH + "/" + testsFailedMID + "/" + testsFailedLOW);
                }
                break;

            case SKIPPED:
                testsSkipped++;

            case FAILED_RETRIED:
                testsFailedRetried++;
                break;

            default: throw new FennecSystemException("Not implemented: " + status);
        }

        // update team city progress
        reportCountersToTeamCity();
    }

    public static String getFinalCountersMessage() {
        // V-X-S: 3-2-1  H-M-L: 0-0-0 (1-1-1)
        // 3 Passed, 2 Failed, 1 ExpFailed, 1 Skipped
        String out = "";

        if (testsSuccessful > 0) {
            out = StringUtils.enhanceList(out, testsSuccessful + " Passed", SEPERATOR, true);
        }
        if (testsFailed > 0) {
            out = StringUtils.enhanceList(out, testsFailed + " Failed", SEPERATOR, true);
        }
        if (testsSkipped > 0) {
            out = StringUtils.enhanceList(out, testsSkipped + " Skipped", SEPERATOR, true);
        }
        if (testsExpectedFailed > 0) {
            out = StringUtils.enhanceList(out, testsExpectedFailed + " ExpFailed", SEPERATOR, true);
        }

        return out;
    }

    public static String getCounterInfoMessage() {
        String out = getFinalCountersMessage();
        if (testsFailedRetried > 0) {
            out = StringUtils.enhanceList(out, testsFailedRetried + " Retried", SEPERATOR, true);
        }
        return out;
    }

    public static void reportCountersToTeamCity() {
        String counterInfoMessage = getCounterInfoMessage();
        String teamCityMessage = ReportUtils.getReportName() + " " + RUN_CONTEXT.runConfig.RUNCFG + ": " + counterInfoMessage;

        LOGGER.info(teamCityMessage);
    }

    public static int getTestsFailed() {
        return testsFailed;
    }

    public static int getTestsFailedHIGH() {
        return testsFailedHIGH;
    }

    public static int getTestsFailedMID() {
        return testsFailedMID;
    }

    public static int getTestsFailedLOW() {
        return testsFailedLOW;
    }

    public static int getTestsSuccessful() {
        return testsSuccessful;
    }

    public static int getTestsSkipped() {
        return testsSkipped;
    }

    public static int getTestsFailedRetried() {
        return testsFailedRetried;
    }

    public static int getTestsExpectedFailed() {
        return testsExpectedFailed;
    }

    public static int getAllFailed() {
        return testsFailed + testsExpectedFailed;
    }

    public static boolean areAllTestsPassedYet() {
        return (getAllFailed() + getTestsSkipped()) == 0;
    }

    public enum Status {
        PASSED("green", "&#x2714;", "Passed", true, true),
        MINOR("skyblue", "&#x2714;", "Minor", true, true),
        PASSED_RETRY("#6abd00", "&#x2714;", "Passed after Retry", false, true),
        INFO("#b9b900", "i", "Info", true, false),

        FAILED("red", "&#x2718;", "Failed", true, true),
        FAILED_MINOR("deeppink", "&#x2718;", "Failed with Minor", true, true),
        FAILED_RETRIED("pink", "R", "Retried", true, false),
        FAILED_EXPECTED("grey", "&#x2718;", "Expected Failed", true, false),

        SKIPPED("orange", "s", "Skipped", true, true),
        NO_RUN("lightgrey", "x", "No run", false, true); // this is basically an illegal state

        public final String color;
        public final String symbol;
        public final String title;
        public final boolean active;
        public final boolean relevant;

        public transient Map<Status, Integer> counts = new LinkedHashMap<>();

        Status(String color, String symbol, String title, boolean active, boolean relevant) {
            this.color = color;
            this.symbol = symbol;
            this.title = title;
            this.active = active;
            this.relevant = relevant;
        }

        public boolean isPassed() {
            switch (this) {
                case PASSED:
                case PASSED_RETRY:
                case MINOR:
                case INFO:
                    return true;

                case SKIPPED:
                case NO_RUN:
                case FAILED:
                case FAILED_MINOR:
                case FAILED_EXPECTED:
                case FAILED_RETRIED:
                    return false;

                default: throw new FennecSystemException("Unhandled state: " + this);
            }
        }

        public boolean isFailed(boolean orSkipped, boolean withFailedExpected, boolean withRetried) {
            switch (this) {
                case SKIPPED:
                    if (orSkipped) {
                        return true;
                    }
                case NO_RUN:
                case PASSED:
                case PASSED_RETRY:
                case MINOR:
                case INFO:
                    return false;

                case FAILED_RETRIED:
                    if (!withRetried) {
                        return false;
                    }
                case FAILED_EXPECTED:
                    if (!withFailedExpected) {
                            return false;
                    }
                case FAILED:
                case FAILED_MINOR:
                    return true;


                default: throw new FennecSystemException("Unhandled state: " + this);
            }
        }

        public boolean isSkipped() {
            switch (this) {
                case PASSED:
                case MINOR:
                case PASSED_RETRY:
                case INFO:
                case FAILED:
                case FAILED_MINOR:
                case FAILED_RETRIED:
                case FAILED_EXPECTED:
                    return false;

                case SKIPPED:
                case NO_RUN:
                    return true;

                default: throw new FennecSystemException("Unhandled state: " + this);
            }
        }

    }
}