/*
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
 *      Peter Lehmann
 *      Eric Kubenka
 */
package eu.tsystems.mms.tic.testframework.webdrivermanager;

import eu.tsystems.mms.tic.testframework.events.TesterraEvent;
import eu.tsystems.mms.tic.testframework.events.TesterraEventDataType;
import eu.tsystems.mms.tic.testframework.events.TesterraEventService;
import eu.tsystems.mms.tic.testframework.events.TesterraEventType;
import eu.tsystems.mms.tic.testframework.report.model.context.MethodContext;
import eu.tsystems.mms.tic.testframework.report.model.context.SessionContext;
import eu.tsystems.mms.tic.testframework.report.utils.ExecutionContextController;
import eu.tsystems.mms.tic.testframework.utils.StringUtils;

import java.net.URL;

public class WebDriverProxyUtils {

    static void updateSessionContextRelations(SessionContext sessionContext) {
        /*
        assign usage in current method
        (this is useful for sessions that are shared between method contexts)
         */
        ExecutionContextController.setCurrentSessionContext(sessionContext);
        MethodContext methodContext = ExecutionContextController.getCurrentMethodContext();
        if (methodContext != null) {
            if (!methodContext.sessionContexts.contains(sessionContext)) {
                methodContext.sessionContexts.add(sessionContext);

                // fire sync of method context
                TesterraEventService.getInstance().fireEvent(new TesterraEvent(TesterraEventType.CONTEXT_UPDATE)
                        .addUserData()
                        .addData(TesterraEventDataType.CONTEXT, methodContext));

            }
        }
    }

    /**
     * Will return proxy String for Selenium WebDriver commands like {host}:{port}
     *
     * @param proxyUrl {@link URL} proxy Url
     * @return String - The proxy URL
     */
    public static String toProxyString(final URL proxyUrl) {

        String proxyString = null;

        if (proxyUrl != null && StringUtils.isNotBlank(proxyUrl.getHost())) {

            proxyString = proxyUrl.getHost();

            if (proxyUrl.getPort() > -1) {
                proxyString += ":" + proxyUrl.getPort();
            }
        }

        return proxyString;
    }

}