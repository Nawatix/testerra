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
package eu.tsystems.mms.tic.testframework.core.playground.webdriver;

import eu.tsystems.mms.tic.testframework.AbstractTest;
import eu.tsystems.mms.tic.testframework.constants.Browsers;
import eu.tsystems.mms.tic.testframework.report.FennecListener;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverManager;
import eu.tsystems.mms.tic.testframework.webdrivermanager.desktop.WebDriverMode;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by pele on 20.07.2017.
 */
public class AWSWebDriverTest extends AbstractTest {

    static {
        WebDriverManager.config().webDriverMode = WebDriverMode.remote;
        System.setProperty("fennec.selenium.server.url", "http://test:test@localhost:4001/wd/hub");
        WebDriverManager.config().browser = Browsers.chrome;
        WebDriverManager.config().browserVersion = "64";
    }

    @Test
    public void testSettingConfig() {
        WebDriverManager.setBaseURL("http://www.google.de");
        WebDriver driver = WebDriverManager.getWebDriver();
        String title = driver.getTitle();
        System.out.println("Title: " + title);
    }
}
