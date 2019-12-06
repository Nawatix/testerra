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
 *     Peter Lehmann
 *     pele
 */
package eu.tsystems.mms.tic.testframework.hooks;

import eu.tsystems.mms.tic.testframework.execution.testng.RetryAnalyzer;
import eu.tsystems.mms.tic.testframework.execution.testng.WebDriverRetryAnalyzer;
import eu.tsystems.mms.tic.testframework.execution.worker.finish.*;
import eu.tsystems.mms.tic.testframework.execution.worker.shutdown.WebDriverShutDownAfterTestsWorker;
import eu.tsystems.mms.tic.testframework.execution.worker.start.PerformanceStartWorker;
import eu.tsystems.mms.tic.testframework.execution.worker.start.WebDriverLoggingStartWorker;
import eu.tsystems.mms.tic.testframework.interop.TestEvidenceCollector;
import eu.tsystems.mms.tic.testframework.report.*;
import eu.tsystems.mms.tic.testframework.watchdog.WebDriverWatchDog;

public class UIDriverHook implements ModuleHook {

    @Override
    public void init() {
        /*
        init test step integration
         */
        UITestStepIntegration.init();

        /*
        init TesterraListener Workers
         */
        //start
        TesterraListener.registerBeforeMethodWorker(PerformanceStartWorker.class);
        //TesterraListener.registerBeforeMethodWorker(WebDriverLoggingStartWorker.class);

        //finish
        TesterraListener.registerAfterMethodWorker(ConditionalBehaviourWorker.class);
        TesterraListener.registerAfterMethodWorker(LogWDSessionsWorker.class);
        TesterraListener.registerAfterMethodWorker(TakeInSessionEvidencesWorker.class);

        TesterraListener.registerAfterMethodWorker(WebDriverSessionsAfterMethodWorker.class); // the utilizable one

        /*
        ********* SESSIONS SHUTDOWN *********
         */
        TesterraListener.registerAfterMethodWorker(WebDriverShutDownWorker.class);

        TesterraListener.registerAfterMethodWorker(TakeOutOfSessionsEvidencesWorker.class);
        TesterraListener.registerAfterMethodWorker(TestMethodFinishWorker.class);

        //shutdown
        TesterraListener.registerGenerateReportsWorker(WebDriverShutDownAfterTestsWorker.class);

        /*
        register services
         */
        // RetryAnalyzer
        RetryAnalyzer.registerAdditionalRetryAnalyzer(new WebDriverRetryAnalyzer());
        // Screenshots and Videos
        TestEvidenceCollector.registerScreenshotCollector(new ScreenshotGrabber());
        TestEvidenceCollector.registerSourceCollector(new SourceGrabber());
    }

    @Override
    public void terminate() {
        WebDriverWatchDog.stop();
    }
}
