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
package eu.tsystems.mms.tic.testframework.test.layoutcheck;

import eu.tsystems.mms.tic.testframework.AbstractExclusiveTestSitesTest;
import eu.tsystems.mms.tic.testframework.annotations.Fails;
import eu.tsystems.mms.tic.testframework.core.pageobjects.testdata.BasePage;
import eu.tsystems.mms.tic.testframework.core.testpage.TestPage;
import eu.tsystems.mms.tic.testframework.pageobjects.UiElement;
import org.testng.annotations.Test;

public class LayoutCheckTest_NewApi extends AbstractExclusiveTestSitesTest<BasePage> {

    @Override
    protected TestPage getTestPage() {
        return TestPage.LAYOUT;
    }

    @Override
    public Class getPageClass() {
        return BasePage.class;
    }

    @Test
    public void testCheckElementLayout() {
        BasePage page = getPage();
        UiElement guiElement = page.getFinder().findByQa("section/layoutTestArticle");
        guiElement.expect().screenshot().pixelDistance("TestArticle").isLowerThan(1.3);

        guiElement = page.getFinder().findByQa("section/invisibleTestArticle");
        guiElement.expect().screenshot().pixelDistance("InvisibleTestArticle").isLowerThan(1.3);
    }

    @Test
    public void testCheckElementVisibility() {
        BasePage page = getPage();
        UiElement guiElement = page.getFinder().findByQa("section/layoutTestArticle");
        guiElement.expect().visible(true).is(true);

        guiElement = page.getFinder().findByQa("section/invisibleTestArticle");
        guiElement.expect().visible(false).is(false);

        // Scroll to offset doesn't work
        //guiElement.scrollToElement(300);
        //Assert.assertFalse(guiElement.isVisible(true));

        guiElement.scrollIntoView();
        guiElement.expect().visible(true).is(true);
    }

//    @Test()
//    @Fails(description = "This test should fail")
//    public void testCheckElementLayoutDistance() {
//        BasePage page = getPage();
//        UiElement guiElement = page.getFinder().findByQa("section/layoutTestArticle");
//        Control.retryFor(10,() -> {
//            Control.withTimeout(0, () -> guiElement.expect().screenshot().pixelDistance("TestArticleFailed").isLowerThan(1));
//        });
//    }

    @Test
    public void testCheckPageLayout() {
        BasePage page = getPage();
        page.expect().screenshot().pixelDistance("LayoutTestPage").isLowerThan(1);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testCheckPageLayout_failed() {
        BasePage page = getPage();
        Control.withTimeout(0, () -> page.expect().screenshot().pixelDistance("LayoutTestPage").isGreaterThan(100));
    }
}