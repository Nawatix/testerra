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
package eu.tsystems.mms.tic.testframework.core.test.utils;

import eu.tsystems.mms.tic.testframework.AbstractTestSitesTest;
import eu.tsystems.mms.tic.testframework.annotations.Fails;
import eu.tsystems.mms.tic.testframework.core.test.TestPage;
import eu.tsystems.mms.tic.testframework.pageobjects.IGuiElement;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Test;

/**
 * Created by pele on 21.12.2015.
 */
public abstract class AbstractDragAndDropTest extends AbstractTestSitesTest {

    final By sourceLocatorSimple = By.id("dragLogo");
    final By sourceLocatorFrames = By.xpath(".//img[@alt='Ringo Starr']");

    private WebDriver getDriver() {
        return WebDriverManager.getWebDriver();
    }

    private IGuiElement[] beforeDragAndDropSimple() {
        final WebDriver driver = getDriver();

        String url = TestPage.DRAG_AND_DROP.getUrl();

        driver.get(url);

        IGuiElement sourceGuiElement = new GuiElement(driver, sourceLocatorSimple);
        IGuiElement destinationGuiElement = new GuiElement(driver, By.id("divRectangle"));
        return new GuiElement[] { sourceGuiElement, destinationGuiElement };
    }

    private IGuiElement[] beforeDragAndDropFrames() {
        final WebDriver driver = getDriver();

        String url = TestPage.DRAG_AND_DROP_OVER_FRAMES.getUrl();
        driver.get(url);

        IGuiElement leftFrame = new GuiElement(driver, By.id("draggableNodes"));
        IGuiElement rightFrame = new GuiElement(driver, By.id("dropTargets"));

        IGuiElement sourceGuiElement = new GuiElement(driver, sourceLocatorFrames, leftFrame);
        IGuiElement destinationGuiElement = new GuiElement(driver, By.id("dropTarget"), rightFrame);
        return new GuiElement[] { sourceGuiElement, destinationGuiElement };
    }

    private void checkResultSimple(IGuiElement destinationGuiElement) {
        final IGuiElement subElement = destinationGuiElement.getSubElement(sourceLocatorSimple);
        subElement.asserts().assertIsDisplayed();
    }

    private void checkResultFrames(IGuiElement destinationGuiElement) {
        final IGuiElement subElement = destinationGuiElement.getSubElement(sourceLocatorFrames);
        subElement.asserts().assertIsDisplayed();
    }

    @Test
    @Fails(validFor = "unsupportedBrowser=true", description = "Does not work in this browser!")
    public void testT01_DragAndDrop() {
        if (this instanceof DragAndDropWDActionsTest) {
            throw new SkipException("Skipped. Would end up in a watchdog bite while mouseMove");
        }

        final IGuiElement[] guiElements = beforeDragAndDropSimple();

        IGuiElement sourceGuiElement = guiElements[0];
        IGuiElement destinationGuiElement = guiElements[1];

        WebDriver driver = getDriver();

        execute(driver, sourceGuiElement, destinationGuiElement);

        checkResultSimple(destinationGuiElement);
    }

    @Test
    @Fails(validFor = "unsupportedBrowser=true", description = "Does not work in this browser!")
    public void testT2_DragAndDropOverFrames() throws Exception {
        if (this instanceof DragAndDropWDActionsTest) {
            throw new SkipException("Skipped. Would end up in a watchdog bite while mouseMove");
        }

        final IGuiElement[] guiElements = beforeDragAndDropFrames();

        IGuiElement sourceGuiElement = guiElements[0];
        IGuiElement destinationGuiElement = guiElements[1];

        WebDriver driver = getDriver();
        execute(driver, sourceGuiElement, destinationGuiElement);

        checkResultFrames(destinationGuiElement);
    }

    protected abstract void execute(WebDriver driver, IGuiElement sourceGuiElement, IGuiElement destinationGuiElement);

}
