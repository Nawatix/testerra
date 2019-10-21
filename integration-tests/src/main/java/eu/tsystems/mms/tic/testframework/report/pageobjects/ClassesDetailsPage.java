package eu.tsystems.mms.tic.testframework.report.pageobjects;

import eu.tsystems.mms.tic.testframework.exceptions.TesterraRuntimeException;
import eu.tsystems.mms.tic.testframework.execution.testng.AssertCollector;
import eu.tsystems.mms.tic.testframework.pageobjects.Check;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.IGuiElement;
import eu.tsystems.mms.tic.testframework.pageobjects.factory.PageFactory;
import eu.tsystems.mms.tic.testframework.report.abstracts.AbstractReportPage;
import eu.tsystems.mms.tic.testframework.report.model.IReportAnnotationVerifier;
import eu.tsystems.mms.tic.testframework.report.model.ReportAnnotationType;
import eu.tsystems.mms.tic.testframework.report.model.ReportConfigMethodStateHelper;
import eu.tsystems.mms.tic.testframework.report.model.TestResultHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by jlma on 14.06.2017.
 */
public class ClassesDetailsPage extends AbstractReportPage implements IReportAnnotationVerifier, ReportConfigMethodStateHelper {

    private String testResultLocatorPattern = "//th[text()='%s']";

    @Check
    private IGuiElement headLine = new GuiElement(this.driver, By.xpath("//div[@class='dashboardTextBig']"), mainFrame);

    private IGuiElement configMethodsButton = new GuiElement(this.driver, By.id("toggleSuccessfulConfigMethodsView"), mainFrame);

    private IGuiElement successfulConfigMethodsHeader = new GuiElement(this.driver, By.id("successfulConfigMethods"), mainFrame);

    public ClassesDetailsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Page flow method to change the class for the classes details
     *
     * @param testUnderTestClassName
     * @return
     */
    public ClassesDetailsPage changeTestUnderTestClass(String testUnderTestClassName) {
        ClassesPage classesPage = this.goToClasses();
        return classesPage.gotoClassesDetailsPageForClass(testUnderTestClassName);
    }

    private IGuiElement getTestResultTableHeaderForTestResult(TestResultHelper.TestResult testResult) {
        IGuiElement testResultTableHeader = new GuiElement(driver, By.xpath(String.format(testResultLocatorPattern, testResult.getXpathClassesDetailsHeader())), mainFrame);
        testResultTableHeader.setName("testResultTableHeader");
        return testResultTableHeader;
    }

    public void assertColorIsDisplayedForTestResult(TestResultHelper.TestResult testResult) {
        IGuiElement testResultTableHeader = getTestResultTableHeaderForTestResult(testResult);
        testResultTableHeader.asserts().assertIsDisplayed();
        String colorString = testResultTableHeader.getCssValue("background-color");
        Color actualColor = toColorFromCSSColorString(colorString);
        Color expectedColor = TestResultHelper.convertColorForTestResult(testResult);
        Assert.assertEquals(actualColor, expectedColor, "The actual color for TestResult " + testResult.toString() + " is correct");
    }

    public void assertMethodNameIsDisplayedForTestMethod(String expectedMethodName, String... expectedSuiteNames) {
        IGuiElement actualMethodNameElement = getInformationMethodBodyForTestMethodName(expectedMethodName);
        actualMethodNameElement.asserts().assertIsDisplayed();
        String actualMethodName = actualMethodNameElement.getText();
        AssertCollector.assertTrue(actualMethodName.contains(expectedMethodName), "The displayed method name is equal to the expected method name");
        if(expectedSuiteNames != null){
            for(String expectedSuiteName : expectedSuiteNames)
                AssertCollector.assertTrue(actualMethodName.contains(expectedSuiteName), "The displayed suite name is equal to the expected one.");
        }
    }

    public void assertMethodIsDisplayedInTheCorrectTestResultCategory(String testundertestMethodName, TestResultHelper.TestResult expectedTestResultCategory) {
        assertMethodIsDisplayedInTheCorrectTestResultCategory(testundertestMethodName, null, expectedTestResultCategory)    ;
    }

    public void assertMethodIsDisplayedInTheCorrectTestResultCategory(String testundertestMethodName, String controlMethodName, TestResultHelper.TestResult expectedTestResultCategory) {

        final String expectedHeaderClassAttribute = expectedTestResultCategory.getXpathClassesDetailsHeader();

        IGuiElement testResultTableHeader = getTestResultTableHeaderForTestResult(expectedTestResultCategory);
        testResultTableHeader.asserts().assertIsDisplayed();
        IGuiElement methodNameElement = getInformationMethodBodyForTestMethodName(testundertestMethodName, controlMethodName);
        methodNameElement.asserts().assertIsDisplayed();
        IGuiElement actualHeader = methodNameElement.getSubElement(By.xpath("./../tr/th"));
        String actualHeaderClassAttribute = actualHeader.getText();
        AssertCollector.assertEquals(actualHeaderClassAttribute, expectedHeaderClassAttribute, "The Test method is in the correct test result category for test result + " + expectedTestResultCategory);
    }

    public void assertMethodIsDisplayedInTheCorrectTestResultCategoryWithTag(String testundertestMethodName, TestResultHelper.TestResult expectedTestResultCategory, String tagName) {

        final String expectedHeaderClassAttribute = expectedTestResultCategory.getTestXPath();

        IGuiElement testResultTableHeader = getTestResultTableHeaderForTestResult(expectedTestResultCategory);
        testResultTableHeader.asserts().assertIsDisplayed();

        IGuiElement methodTagNameElement = getInformationMethodBodyForTestMethodNameWithTag(tagName);
        methodTagNameElement.asserts().assertIsDisplayed();

        IGuiElement actualMethodName = methodTagNameElement.getSubElement(By.xpath("./../../a[contains(text(),'"+testundertestMethodName+"')]"));
        actualMethodName.asserts().assertIsDisplayed();

        IGuiElement actualHeader = methodTagNameElement.getSubElement(By.xpath("./../../.."));
        String actualHeaderClassAttribute = actualHeader.getAttribute("class");
        AssertCollector.assertEquals(actualHeaderClassAttribute, expectedHeaderClassAttribute, "The Test method is in the correct test result category for test result + " + expectedTestResultCategory);
    }

    /**
     * Method to get the left info column for a given test method name
     *
     * @param testMethodName
     * @return the left test method info column as IGuiElement
     */
    private IGuiElement getInformationMethodBodyForTestMethodName(String testMethodName, String configMethodName) {
        IGuiElement informationMethodBody = new GuiElement(driver, By.xpath(String.format("//*[@id='%s']",testMethodName)), mainFrame);
        if(null != configMethodName){
            informationMethodBody = new GuiElement(driver, By.xpath(String.format("//*[@id='%s <i>for %s</i>']",testMethodName, configMethodName)), mainFrame);
        }
        informationMethodBody.setName("informationMethodBody");
        return informationMethodBody;
    }

    /**
     * Method to get the left info column for a given test method name
     *
     * @param testMethodName
     * @return the left test method info column as IGuiElement
     */
    private IGuiElement getInformationMethodBodyForTestMethodName(String testMethodName) {
        IGuiElement informationMethodBody = new GuiElement(driver, By.xpath(String.format("//*[@id='%s']",testMethodName)), mainFrame);
        informationMethodBody.setName("informationMethodBody");
        return informationMethodBody;
    }

    /**
     * Method to get the left info column for a given test method name
     *
     * @param testMethodName
     * @return the left test method info column as IGuiElement
     */
    private IGuiElement getInformationMethodBodyForTestMethodNameRetried(String testMethodName) {
        //TODO delete this when id for retried method is given automatically
        //needed for config test methods
        IGuiElement informationMethodBody = new GuiElement(driver, By.xpath(String.format("//*[contains(text(),'%s')]/..",testMethodName)), mainFrame);
        informationMethodBody.setName("informationMethodBody");
        return informationMethodBody;
    }

    /**
     * Method to get the left info column for a given test method tag name
     *
     * @param testMethodTagName
     * @return the left test method info column as IGuiElement
     */
    private IGuiElement getInformationMethodBodyForTestMethodNameWithTag(String testMethodTagName) {

        IGuiElement informationMethodBody = new GuiElement(driver, By.xpath("//font[contains(text(),'"+testMethodTagName+"')]"), mainFrame);
        informationMethodBody.setName("informationMethodBody");
        return informationMethodBody;
    }


    public void assertRetrySymbolIsDisplayedForMethod(String testundertestMethodName) {
        IGuiElement methodInfoBody = getInformationMethodBodyForTestMethodName(testundertestMethodName);
        IGuiElement retrySymbol = methodInfoBody.getSubElement(By.xpath("./..//img[@title='Retry']"));
        retrySymbol.setName("retrySymbol");
        retrySymbol.asserts().assertIsDisplayed();
    }

    public void assertErrorMessageIsDisplayedForTestMethod(String testMethodName) {
        IGuiElement resultInfoBody = getResultInfoBody(testMethodName);
        IGuiElement exceptionMessageElement = resultInfoBody.getSubElement(By.className("message"));
        exceptionMessageElement.setName("exceptionMessageElement");
        exceptionMessageElement.asserts().assertIsDisplayed();
        AssertCollector.assertFalse(exceptionMessageElement.getText().isEmpty(), "The exception message is empty");

    }

    public void assertStackTraceLinkIsDisplayedForTestMethod(String testMethodName) {
        IGuiElement resultInfoBody = getResultInfoBody(testMethodName);
        IGuiElement stackTraceLink = resultInfoBody.getSubElement(By.xpath(".//a[@title='Stacktrace']"));
        stackTraceLink.setName("stackTraceLink");
        stackTraceLink.asserts().assertIsDisplayed();
        stackTraceLink.click();
        IGuiElement stackTrace = stackTraceLink.getSubElement(By.xpath("./../..//div[@class='stackTrace']"));
        stackTrace.setName("stackTrace");
        stackTrace.asserts().assertIsDisplayed();
        Assert.assertTrue(stackTrace.getText().contains(testMethodName), "The StackTrace contains the testmethod name: " + testMethodName);
    }

    public void assertDetailsLinkIsDisplayedAndWorks(String testMethodName) {
        IGuiElement detailsLink = getDetailsLinkByMethodName(testMethodName);
        detailsLink.asserts().assertIsDisplayed();
        gotoMethodDetailsPage(detailsLink);
    }

    public void assertScreenshotIsNotDisplayedForMethod(String testundertestMethod) {
        IGuiElement screenshotElement = getScreenShotOfMethod(testundertestMethod, 1);
        screenshotElement.asserts().assertIsNotDisplayed();
    }

    @Override
    public void assertHidingAndShowingOfConfigMethodSection() {
        final String configMethodsButtonLabelShow = ConfigMethodState.SHOWING.getLabel().toUpperCase();
        final String configMethodsButtonLabelHide = ConfigMethodState.HIDING.getLabel().toUpperCase();
        AssertCollector.assertEquals(configMethodsButton.getText(), configMethodsButtonLabelHide, "The showConfigMethod Button has the correct state");
        switchConfigMethodDisplayState();
        AssertCollector.assertEquals(configMethodsButton.getText(), configMethodsButtonLabelShow, "The showConfigMethod Button has the correct state");
        AssertCollector.assertTrue(isSuccessfulConfigurationMethodsHeaderDisplayed(), "The Header for successful Configuration Methods is displayed");
    }

    @Override
    public boolean changeConfigMethodDisplayStateTo(ConfigMethodState stateToChangeTo) {
        if (hasExpectedState(stateToChangeTo)) {
            return true;
        }
        configMethodsButton.click();
        return hasExpectedState(stateToChangeTo);
    }

    @Override
    public void switchConfigMethodDisplayState() {
        configMethodsButton.click();
    }

    @Override
    public boolean hasExpectedState(ConfigMethodState expectedState) {
        return configMethodsButton.getText().contains(expectedState.getStateIndicator());
    }

    public IGuiElement getDetailsLinkByMethodName(String methodName) {
        IGuiElement informationMethodBody = getInformationMethodBodyForTestMethodName(methodName);
        IGuiElement detailsLink = informationMethodBody.getSubElement(By.xpath(".//a[@title='Details']"));
        detailsLink.setName("detailsLink");
        return detailsLink;
    }

    public IGuiElement getDetailsLinkByMethodNameWithTag(String methodTagName) {
        IGuiElement resultInfoBody = getResultInfoBodyWithTag(methodTagName);
        IGuiElement detailsLink = resultInfoBody.getSubElement(By.xpath(".//a[@title='Details']"));
        detailsLink.setName("detailsLink");
        return detailsLink;
    }

    public boolean isSuccessfulConfigurationMethodsHeaderDisplayed() {
        return successfulConfigMethodsHeader.isDisplayed();
    }

    private boolean isElementTextDisplayedAndParsableToDate(IGuiElement element) {

        final String durationIndicator = "duration";
        final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMAN);
        final DateFormat durationFormat = new SimpleDateFormat("HH'h' mm'm' ss's'", Locale.GERMAN);

        element.asserts().assertIsDisplayed();
        String elementText = element.getText();
        String datePart = elementText.substring(elementText.indexOf(": ") + 1);
        try {
            if (elementText.contains(durationIndicator)) {
                durationFormat.parse(datePart);
                return true;
            }
            dateFormat.parse(datePart);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Method to get the right result column for a given test method name
     *
     * @param testMethodName
     * @return the right test method result column as IGuiElement
     */
    private IGuiElement getResultInfoBody(String testMethodName) {
        IGuiElement methodInfoBody = getInformationMethodBodyForTestMethodName(testMethodName);
        IGuiElement resultInfoBody = methodInfoBody.getSubElement(By.xpath("./..//td[@class='result']"));
        resultInfoBody.setName("resultInfoBody");
        return resultInfoBody;
    }

    /**
     * Method to get the right result column for a given test method tag name
     *
     * @param testMethodTagName
     * @return the right test method result column as IGuiElement
     */
    private IGuiElement getResultInfoBodyWithTag(String testMethodTagName) {
        IGuiElement methodInfoBody = getInformationMethodBodyForTestMethodNameWithTag(testMethodTagName);
        IGuiElement resultInfoBody = methodInfoBody.getSubElement(By.xpath("./..//*[@class='result']"));
        resultInfoBody.setName("resultInfoBody");
        return resultInfoBody;
    }

    /**
     * Returns the Screenshot IGuiElement for a given method in report. The index specify the screenshot if the method has more then one
     *
     * @param testundertestMethodName the simple method name of the testundertest method in report
     * @param screenShotIndex         specify the screenShot since there could be more then one. 1 == First, 2 == Second, ...
     * @return
     */
    private IGuiElement getScreenShotOfMethod(String testundertestMethodName, int screenShotIndex) {
        IGuiElement resultInfoBody = getResultInfoBody(testundertestMethodName);
        resultInfoBody.asserts().assertIsDisplayed();
        IGuiElement screenShotElement = resultInfoBody.getSubElement(By.xpath(".//div[@class='spacy shadow'][" + screenShotIndex + "]"));
        screenShotElement.setName("screenShotElement#" + screenShotIndex);
        return screenShotElement;
    }

    public MethodDetailsPage gotoMethodDetailsPage(IGuiElement detailsLink) {
        detailsLink.click();
        return PageFactory.create(MethodDetailsPage.class, driver);
    }

    /**
     * Utility method to convert the CSS report representation of a color to java.awt Color Object.
     *
     * @param cssColorString the css value of 'background-color' in report CSS. Example cssColorString: "rgba(255, 136, 136, 1)"
     * @return java.awt.Color with four channels
     */
    private static Color toColorFromCSSColorString(String cssColorString) {
        // Example cssColorString: "rgba(255, 136, 136, 1)"
        String[] colorValues = cssColorString.split(",");
        int redChannel = Integer.parseInt(colorValues[0].substring(colorValues[0].indexOf("(") + 1).trim());
        int greenChannel = Integer.parseInt(colorValues[1].trim());
        int blueChannel = Integer.parseInt(colorValues[2].trim());
        int alphaChannel = Integer.parseInt(colorValues[3].replace(")", "").trim());
        alphaChannel = (int) (alphaChannel * 255 + 0.5); // from java Color(float...) constructor
        return new Color(redChannel, greenChannel, blueChannel, alphaChannel);
    }

    @Override
    public void assertAnnotationMarkIsDisplayed(ReportAnnotationType annotationType, String methodName) {
        IGuiElement methodBody = getInformationMethodBodyForTestMethodName(methodName);
        switch (annotationType){
            case RETRIED:
                methodBody = getInformationMethodBodyForTestMethodNameRetried(methodName);
                break;
        }
        IGuiElement annotationElement = methodBody.getSubElement(By.xpath(String.format(LOCATOR_FONT_ANNOTATION, annotationType.getAnnotationDisplayedName())));
        annotationElement.setName("annotationElementFor_" + annotationType.getAnnotationDisplayedName());
        annotationElement.asserts().assertIsDisplayed();
    }

    @Override
    public void assertAllAnnotationMarksAreDisplayed(String methodName) {
        for (ReportAnnotationType annotationType : ReportAnnotationType.values()) {
            assertAnnotationMarkIsDisplayed(annotationType, methodName);
        }
    }

    public void assertMethodExecutionOrder() {
        Map<Integer, Date> allExecutionNumbers = getAllExecutionEntries();
        int uppermostExecutionNumber = Collections.max(allExecutionNumbers.keySet());
        int lowestExecutionNumber = Collections.min(allExecutionNumbers.keySet());
        Date lastDate = Date.from(Instant.EPOCH);
        for (int i = lowestExecutionNumber; i < uppermostExecutionNumber; i++) {
            Date entryDate = allExecutionNumbers.get(i);
            if (entryDate == null || entryDate.getTime() <= 0) {
                continue;
            }
            Assert.assertTrue(entryDate.after(lastDate) || entryDate.equals(lastDate), "Date " + entryDate + " is NOT equal to or after date" + lastDate);
            lastDate = entryDate;
        }


    }

    public Map<Integer, Date> getAllExecutionEntries() {
        final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMAN);
        Map<Integer, Date> executionEntries = new HashMap<>();
        List<IGuiElement> testMethodInfos = new GuiElement(driver, By.xpath(".//table[contains(@class,'resultsTable')]//div[5]"), mainFrame).getList();
        for (IGuiElement testMethodInfo : testMethodInfos) {
            if (!testMethodInfo.isDisplayed()) {
                continue;
            }
            String executionNumberString = testMethodInfo.getText().replace("(", "").replace(")", "");
            String startDateString = testMethodInfo.getSubElement(By.xpath("./../div[1]")).getText().replace("Start time: ", "");
            if (startDateString.contains("-")) {
                startDateString = "01.01.1970 00:00:00";
            }
            Date startDate = null;
            try {
                startDate = dateFormat.parse(startDateString);
            } catch (ParseException e) {
                throw new TesterraRuntimeException("Could not parse start date " + startDateString + " of method with execution number " + executionNumberString);
            }
            int executionNumber = Integer.parseInt(executionNumberString);
            executionEntries.put(executionNumber, startDate);
        }
        return executionEntries;
    }

}
