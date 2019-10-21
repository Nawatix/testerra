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
package eu.tsystems.mms.tic.testframework.core.test.pageobjects.IGuiElement.variations;

import eu.tsystems.mms.tic.testframework.core.test.TestPage;
import eu.tsystems.mms.tic.testframework.pageobjects.IGuiElement;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class FrameAwareGuiElementTest extends GuiElementTestCollector {

    /**
     * Test if FrameAwareElement in deepest hierarchy is present via AllFrameLogic
     */
    @Test
    public void testTFA01_GuiElement_FrameLogic_AllFrames() {
        final WebDriver driver = WebDriverManager.getWebDriver();
        IGuiElement frame1 = new GuiElement(driver, By.name("frame1"));
        IGuiElement frame12 = new GuiElement(driver, By.name("frame12"), frame1);
        IGuiElement frame123 = new GuiElement(driver, By.name("frame123"), frame1, frame12);
        IGuiElement frame1234 = new GuiElement(driver, By.name("InputFrame1234"), frame1, frame12, frame123);
        IGuiElement element = new GuiElement(driver, By.id("16"), frame1, frame12, frame123, frame1234);
        element.asserts().assertIsPresent();
    }

    @Override
    public IGuiElement getGuiElementBy(By locator) {
        final WebDriver driver = WebDriverManager.getWebDriver();
        IGuiElement frame = new GuiElement(driver, By.name("InputFrame1"));
        return new GuiElement(driver, locator, frame);
    }

    @Override
    protected TestPage getTestPage() {
        return TestPage.FRAME_TEST_PAGE;
    }
}
