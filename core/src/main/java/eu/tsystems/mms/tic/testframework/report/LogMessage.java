/*
 * Testerra
 *
 * (C) 2020, Mike Reiche, T-Systems Multimedia Solutions GmbH, Deutsche Telekom AG
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
 */
 package eu.tsystems.mms.tic.testframework.report;

import eu.tsystems.mms.tic.testframework.logging.Loggable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;

import java.io.Serializable;
import java.util.Date;

/**
 * Wrapper for {@link LogEvent}
 * @deprecated Only required for report
 */
@Deprecated
public class LogMessage implements Serializable, Loggable {
    private LogEvent logEvent;
//
//    public LogMessage(Level logLevel, long timestamp, String threadName, String loggerName, String message) {
//        this.level = logLevel;
//        this.timestamp = timestamp;
//        this.threadName = threadName;
//        this.loggerName = loggerName;
//        this.message = message;
//    }

    public LogMessage(LogEvent event) {
        this.logEvent = event;
    }

    public Level getLogLevel() {
        return this.logEvent.getLevel();
    }

    /**
     * Required by velocity templates
     */
    @Deprecated
    public Date getDate() {
        return new Date(logEvent.getTimeMillis());
    }

    public long getTimestamp() {
        return this.logEvent.getTimeMillis();
    }

    public String getThreadName() {
        return this.logEvent.getThreadName();
    }

    public String getLoggerName() {
        return this.logEvent.getLoggerName();
    }

    public String getMessage() {
        return this.logEvent.getMessage().getFormattedMessage();
    }
}