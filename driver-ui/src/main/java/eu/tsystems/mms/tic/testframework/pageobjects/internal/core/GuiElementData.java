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
package eu.tsystems.mms.tic.testframework.pageobjects.internal.core;

import eu.tsystems.mms.tic.testframework.internal.ExecutionLog;
import eu.tsystems.mms.tic.testframework.logging.LogLevel;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.IGuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.Locate;
import eu.tsystems.mms.tic.testframework.pageobjects.POConfig;
import eu.tsystems.mms.tic.testframework.pageobjects.internal.TimerWrapper;
import eu.tsystems.mms.tic.testframework.pageobjects.internal.WebElementAdapter;
import eu.tsystems.mms.tic.testframework.pageobjects.internal.frames.FrameLogic;
import eu.tsystems.mms.tic.testframework.pageobjects.internal.frames.IFrameLogic;
import eu.tsystems.mms.tic.testframework.utils.StringUtils;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverManager;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @todo Create interface
 */
public class GuiElementData {
    public final Locate locate;
    public final WebElementAdapter adapter;
    public final WebDriver webDriver;
    public String name;
    public final ExecutionLog executionLog;
    private int timeoutInSeconds;
    @Deprecated
    public final TimerWrapper timerWrapper;
    public WebElement webElement;
    public final IFrameLogic frameLogic;
    private final int timerSleepTimeInMs = 500;
    public boolean sensibleData = false;
    public final GuiElementData parent;
    public final int index;
    private LogLevel logLevel = LogLevel.DEBUG;
    private LogLevel storedLogLevel = logLevel;
    public final String browser;
    public boolean shadowRoot = false;

    public GuiElementData(GuiElementData parent, int index) {
        this(
            parent.webDriver,
            parent.frameLogic,
            parent.locate,
            parent.adapter,
            parent,
            index
        );
    }

    public GuiElementData(
        WebDriver webDriver,
        IFrameLogic frameLogic,
        Locate locate,
        WebElementAdapter adapter
    ) {
        this(webDriver, frameLogic, locate, adapter, null, -1);
    }

    public GuiElementData(
        WebDriver webDriver,
        IFrameLogic frameLogic,
        Locate locate,
        WebElementAdapter adapter,
        GuiElementData parent,
        int index
    ) {
        this.webDriver = webDriver;
        this.adapter = adapter;
        this.locate = locate;
        this.executionLog = new ExecutionLog();
        this.timeoutInSeconds = POConfig.getUiElementTimeoutInSeconds();
        this.frameLogic = frameLogic;
        WebDriverRequest request = WebDriverManager.getRelatedWebDriverRequest(webDriver);
        this.browser = request.browser;
        // Central Timer Object which is used by all sequence executions
        this.timerWrapper = new TimerWrapper(timerSleepTimeInMs, timeoutInSeconds, webDriver, executionLog);
        this.parent = parent;
        if (parent == null) index = -1;
        this.index = index;
    }

    public int getTimeoutInSeconds() {
        return timeoutInSeconds;
    }

    public void setTimeoutInSeconds(int timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
        timerWrapper.setTimeoutInSeconds(timeoutInSeconds);
    }

    public boolean hasName() {
        return !StringUtils.isEmpty(name);
    }

    public boolean hasFrameLogic() {
        return frameLogic != null;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        // store previous log level away
        this.storedLogLevel = this.logLevel;
        // set new log level
        this.logLevel = logLevel;
    }

    public void resetLogLevel() {
        this.logLevel = this.storedLogLevel;
    }

}
