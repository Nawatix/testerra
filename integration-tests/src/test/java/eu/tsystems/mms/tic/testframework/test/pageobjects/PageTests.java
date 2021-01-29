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
package eu.tsystems.mms.tic.testframework.test.pageobjects;

import eu.tsystems.mms.tic.testframework.AbstractTestSitesTest;
import eu.tsystems.mms.tic.testframework.annotations.Fails;
import eu.tsystems.mms.tic.testframework.core.pageobjects.testdata.WebTestPage;
import eu.tsystems.mms.tic.testframework.internal.asserts.QuantityAssertion;
import eu.tsystems.mms.tic.testframework.internal.asserts.StringAssertion;
import eu.tsystems.mms.tic.testframework.logging.Loggable;
import eu.tsystems.mms.tic.testframework.test.PageFactoryTest;
import org.testng.annotations.Test;

public class PageTests extends AbstractTestSitesTest implements Loggable, PageFactoryTest {

    @Override
    public WebTestPage getPage() {
        return pageFactory.createPage(WebTestPage.class, getClassExclusiveWebDriver());
    }

    @Test
    public void test_Page_title() {
        WebTestPage page = getPage();

        StringAssertion<String> title = page.expectThat().title();

        title.is("Input test");
        title.isNot("Affentest");
        title.contains("Input").is(true);
        title.contains("SuperTestPage").is(false);
        title.containsWords("Input", "test").is(true);

        QuantityAssertion<Integer> length = page.expectThat().title().length();
        length.is(10);
        length.isLowerThan(100);
        length.isGreaterThan(5);
        length.isBetween(1,11);
        length.isGreaterEqualThan(-10);
        length.isLowerEqualThan(10);
    }

    @Test
    public void test_Page_waitFor() {
        WebTestPage page = getPage();
        Control.withTimeout(0, () -> {
            Assert.assertFalse(page.waitFor().title().contains("Katzentitel").is(true));
            Assert.assertTrue(page.waitFor().title().is("Input test"));
        });
    }

    @Test
    public void test_Page_title_matches() {
        WebTestPage page = getPage();
        page.expectThat().title().matches("input\\s+.es.").is(true);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void test_Page_title_matches_fails() {
        WebTestPage page = getPage();
        page.expectThat().title().matches("input\\s+.es.").is(false);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void test_Page_title_length_fails() {
        WebTestPage page = getPage();
        page.expectThat().title().length().isGreaterThan(10);
    }

    @Test
    @Fails(description = "The test itself passes, but collected assertions will always fail")
    public void test_Page_title_length_fails_collected() {
        WebTestPage page = getPage();
        Control.collectAssertions(() -> page.expectThat().title().length().isGreaterThan(10));
    }

    @Test
    public void test_Page_title_length_fails_nonFunctional() {
        WebTestPage page = getPage();
        Control.optionalAssertions(()-> page.expectThat().title().length().isGreaterThan(10));
    }

    @Test
    public void test_Page_url() {
        WebTestPage page = getPage();
        page.expectThat().url().startsWith("http");
        page.expectThat().url().endsWith("input.html");
        page.expectThat().url().length().isGreaterEqualThan(10);
    }

    @Test()
    public void test_Page_url_fails() {
        WebTestPage page = getPage();
        try {
            page.expectThat().url().endsWith("nonexistingfile.html").is(true, "Wrong URL");
        } catch (AssertionError e) {
            Assert.assertContains(e.getMessage(), "Wrong URL");
            Assert.assertEndsWith(e.getMessage(), "ends with [nonexistingfile.html]");
        }
    }

    @Test()
    public void test_Page_url_length_fails() {
        WebTestPage page = getPage();

        try {
            page.expectThat().url().length().isGreaterEqualThan(10000, "URL is too short");
        } catch (AssertionError e) {
            Assert.assertContains(e.getMessage(), "URL is too short");
            Assert.assertEndsWith(e.getMessage(), "is greater or equal than [10000]");
        }
    }


    @Test
    public void test_Component() {
        final String input = "Ich gebe etwas ein";
        WebTestPage page = getPage();
        page.inputForm().button().expectThat().value().is("Button1");
        page.inputForm().input().clear().sendKeys(input).expectThat().value().is(input);
        page.inputForm().button().expectThat().numberOfElements().is(1);
    }
}