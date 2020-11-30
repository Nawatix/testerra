/*
 * Testerra
 *
 * (C) 2020,  Peter Lehmann, T-Systems Multimedia Solutions GmbH, Deutsche Telekom AG
 *
 * Deutsche Telekom AG and all other contributors /
 * copyright owners license this file to you under the Apache
 * License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
 package eu.tsystems.mms.tic.testframework.report.model.steps;

import eu.tsystems.mms.tic.testframework.clickpath.ClickPathEvent;
import eu.tsystems.mms.tic.testframework.report.model.LogMessage;
import eu.tsystems.mms.tic.testframework.report.model.Serial;
import eu.tsystems.mms.tic.testframework.report.model.context.Screenshot;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TestStepAction implements Serializable {

    private static final long serialVersionUID = Serial.SERIAL;

    private final String name;
    private long timestamp;

    private final List<TestStepActionEntry> testStepActionEntries = Collections.synchronizedList(new LinkedList<>());

    public TestStepAction(String name) {
        this.name = name;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Usually, a TestStep already contains a {@link TestStepActionEntry} which contains a {@link LogMessage},
     * where the timestamp can be retrieved by calling {@link LogMessage#getTimestamp()}
     * But when running under platform, the log messages are handled differently and not stored to the database within entities.
     * In this case, the TestSteps are stored WITHOUT log messages and WITHOUT timestamp, which is still required to find log messages according to time ranges.
     * Therefore, the TestStepAction gets it's own timestamp.
     */
    public long getTimestamp() {
        return this.timestamp;
    }

    public String getName() {
        return name;
    }

    public List<TestStepActionEntry> getTestStepActionEntries() {
        return testStepActionEntries;
    }

    public void addLogMessage(LogMessage logMessage) {
        // We take the first real log message timestamp here
        if (testStepActionEntries.size()==0) {
            this.timestamp = logMessage.getTimestamp();
        }
        testStepActionEntries.add(new TestStepActionEntry(logMessage));
    }

    public void addClickPathEvent(ClickPathEvent event) {
        testStepActionEntries.add(new TestStepActionEntry(event));
    }

    public void addScreenshot(Screenshot screenshot) {
        testStepActionEntries.add(new TestStepActionEntry(screenshot));
    }
//
//    public void addFailingLogMessage(final String msg) {
//        LogMessage logMessage = new LogMessage(
//            Level.ERROR,
//            System.currentTimeMillis(),
//            Thread.currentThread().getName(),
//            "Test Failed",
//            msg
//        );
//        final TestStepActionEntry testStepActionEntry = new TestStepActionEntry();
//        testStepActionEntry.logMessage = logMessage;
//        testStepActionEntries.add(testStepActionEntry);
//    }
}

