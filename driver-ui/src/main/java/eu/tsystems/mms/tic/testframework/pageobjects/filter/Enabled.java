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
 *     Peter Lehmann
 *     pele
 */
package eu.tsystems.mms.tic.testframework.pageobjects.filter;

import org.openqa.selenium.WebElement;

public class Enabled extends WebElementFilter {

    private Boolean expectedEnablement;

    Enabled() {
    }

    private Enabled(boolean expectedEnablement) {
        this.expectedEnablement = expectedEnablement;
    }

    public WebElementFilter is(boolean expectedVisibility) {
        Enabled enabled = new Enabled(expectedVisibility);
        return enabled;
    }

    @Override
    public boolean isSatisfiedBy(WebElement webElement) {
        checkCorrectUsage(STD_ERROR_MSG, expectedEnablement);
        boolean enabled = webElement.isEnabled();
        return enabled == expectedEnablement;
    }

    @Override
    public String toString() {
        return "Enabled=" + expectedEnablement;
    }
}