/*
 * Testerra
 *
 * (C) 2020, Peter Lehmann, T-Systems Multimedia Solutions GmbH, Deutsche Telekom AG
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
 package eu.tsystems.mms.tic.testframework.pageobjects.internal.core;

import eu.tsystems.mms.tic.testframework.pageobjects.internal.frames.FrameAwareSelect;
import eu.tsystems.mms.tic.testframework.pageobjects.internal.frames.IFrameLogic;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class GuiElementCoreFrameAwareDecorator extends AbstractGuiElementCoreDecorator {

    private final GuiElementData guiElementData;

    public GuiElementCoreFrameAwareDecorator(GuiElementCore core, GuiElementData guiElementData) {
        super(core);
        this.guiElementData = guiElementData;
    }

    @Override
    protected void beforeDelegation() {
        if (guiElementData.hasFrameLogic()) {
            IFrameLogic frameLogic = guiElementData.getFrameLogic();
            frameLogic.switchToCorrectFrame();
        }
    }

    @Override
    protected void afterDelegation() {
        guiElementData.getFrameLogic().switchToDefaultFrame();
    }

    @Override
    public Select getSelectElement() {
        beforeDelegation();
        WebElement webElement = decoratedCore.findWebElement();
        Select select = new Select(webElement);
        Select frameAwareSelect = new FrameAwareSelect(select, webElement, guiElementData.getFrameLogic().getFrames(), guiElementData.getWebDriver() );
        afterDelegation();
        return frameAwareSelect;
    }
}
