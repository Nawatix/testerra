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
package eu.tsystems.mms.tic.testframework.utils;

import eu.tsystems.mms.tic.testframework.constants.Constants;
import eu.tsystems.mms.tic.testframework.exceptions.TesterraSystemException;
import eu.tsystems.mms.tic.testframework.execution.testng.NonFunctionalAssert;
import eu.tsystems.mms.tic.testframework.transfer.ThrowablePackedResponse;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverManager;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverManagerUtils;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverProxy;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <Beschreibung der Klasse>
 *
 * @author pele
 */
public final class WebDriverUtils {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverUtils.class);

    /**
     * Hidden constructor.
     */
    private WebDriverUtils() {
    }

    /**
     * Does what the name says.
     *
     * @param windowTitle Title of the window to switch to.
     * @param withoutWait True for don't wait and don't get a second chance.
     * @return true if switching was successful.
     */
    public static boolean findWindowAndSwitchTo(final String windowTitle, final boolean withoutWait) {
        return findWindowAndSwitchTo(windowTitle, withoutWait, null);
    }

    /**
     * Does what the name says.
     *
     * @param windowTitle          Title of the window to switch to.
     * @param fast                 True for don't wait and don't get a second chance.
     * @param driver               Webdriver object or null (then the default session will be used)
     * @param excludeWindowHandles .
     * @return true if switching was successful.
     */
    public static boolean findWindowAndSwitchTo(final String windowTitle, final boolean fast, WebDriver driver,
                                                final String... excludeWindowHandles) {
        return findWindowAndSwitchTo(windowTitle, null, fast, driver, excludeWindowHandles);
    }

    /**
     * Does what the name says.
     *
     * @param windowTitle          Title of the window to switch to.
     * @param fast                 True for don't wait and don't get a second chance.
     * @param driver               Webdriver object or null (then the default session will be used)
     * @param excludeWindowHandles .
     * @return true if switching was successful.
     */
    public static boolean findWindowAndSwitchTo(final String windowTitle, final String urlContains, final boolean fast, WebDriver driver,
                                                final String... excludeWindowHandles) {
        if (driver == null) {
            driver = WebDriverManager.getWebDriver();
        }

        final WebDriver wdRef = driver;

        Timer timer;
        if (fast) {
            timer = new Timer(500, 1000);
        } else {
            timer = new Timer();
        }

        final String expectedCriteriaMsg =
                "\ntitle: " + windowTitle +
                        "\nurl  : " + urlContains;

        final ThrowablePackedResponse<Boolean> response = timer.executeSequence(new Timer.Sequence<Boolean>() {
            @Override
            public void run() {
                final Set<String> windowHandles = wdRef.getWindowHandles();
                LOGGER.info("Found " + windowHandles.size() + " opened windows");
                setPassState(false);
                setReturningObject(false);
                for (String windowHandle : windowHandles) {
                    boolean skip = false;
                    if (excludeWindowHandles != null) {
                        for (String excludeHandle : excludeWindowHandles) {
                            if (excludeHandle.equals(windowHandle)) {
                                // skip this one
                                skip = true;
                                break;
                            }
                        }
                    }

                    if (!skip) {
                        WebDriver window = wdRef.switchTo().window(windowHandle);

                        String realTitle = window.getTitle();
                        String url = window.getCurrentUrl();
                        String handle = window.getWindowHandle();

                        String actualWindowMsg =
                                "\ntitle : " + realTitle +
                                        "\nurl   : " + url +
                                        "\nhandle: " + handle;

                        boolean matchesTitle = windowTitle == null;
                        boolean matchesUrl = urlContains == null;
                        // search criteria
                        if (windowTitle != null) {
                            matchesTitle = realTitle.contains(windowTitle);
                        }
                        if (urlContains != null) {
                            matchesUrl = url.contains(urlContains);
                        }

                        if (matchesTitle && matchesUrl) {
                            LOGGER.info("Switched to window:" + actualWindowMsg);
                            setPassState(true);
                            setReturningObject(true);
                        } else {
                            LOGGER.debug("Window title -" + realTitle + "- does not match" + expectedCriteriaMsg);
                        }
                    } else {
                        LOGGER.debug("Skipping window handle " + windowHandle);
                    }
                }

            }
        });
        return response.getResponse();
    }

    /**
     * Generate DesiredCapabilities.
     *
     * @return DesiredCapabilities.
     */
    @Deprecated
    public static DesiredCapabilities generateNewDesiredCapabilities() {
        return WebDriverManagerUtils.generateNewDesiredCapabilities();
    }

    public static WebElement findElementByLocation(WebDriver driver, int x, int y) {
        /*
        TODO: x and y are switched sometimes, i really dont know why this is. I have changed this multiple times... :(
         */
        Object out = JSUtils.executeScript(driver, "return document.elementFromPoint(" + x + "," + y + ");");
        if (out != null) {
            LOGGER.debug("Element from coordinates " + x + "," + y + " : " + out);
            if (out instanceof WebElement) {
                WebElement webElement = (WebElement) out;
                LOGGER.debug("WebElement at " + x + "," + y + " is found at " + webElement.getLocation().getX() + ","
                        + webElement.getLocation().getY());
                return webElement;
            } else {
                throw new TesterraSystemException("Could not get WebElement under " + x + "," + y);
            }
        }
        return null;
    }

    /**
     * Grabs cookies from WebDriver session and prepare them in a string like 'key=value;...'
     * to mimic the current cookie state of the session
     *
     * @param driver WebDriver the current driver
     * @return String
     */
    public static String getCookieString(final WebDriver driver) {

        String cookieString = "";

        final Set<Cookie> cookieSet = driver.manage().getCookies();
        for (Cookie cookie : cookieSet) {
            cookieString += String.format("%s=%s;", cookie.getName(), cookie.getValue());
        }

        if (cookieString.length() > 0) {
            cookieString = StringUtils.removeEnd(cookieString, ";");
        }

        return cookieString;
    }

    /**
     * Checks for all links in the current frame to be accessable (response code 200).
     * Response codes other than 200 generate nonfunctional errors.
     *
     * @param description A desciption for the current linkChecker run.
     * @param driver      the current driver.
     */
    public static void linkChecker(final String description, final WebDriver driver) {
        LOGGER.info("LinkChecker: " + description);
        final List<WebElement> links = driver.findElements(By.tagName("a"));
        Map<String, String> hrefs = new HashMap<String, String>(links.size());
        for (WebElement link : links) {
            String linkname = "unknown";
            final String id = link.getAttribute("id");
            final String name = link.getAttribute("name");
            final String alt = link.getAttribute("alt");

            if (!StringUtils.isAnyStringEmpty(id)) {
                linkname = "id: " + id;
            } else if (!StringUtils.isAnyStringEmpty(name)) {
                linkname = "name: " + name;
            } else if (!StringUtils.isAnyStringEmpty(alt)) {
                linkname = "alt: " + alt;
            }

            final Object o = JSUtils.executeScript(driver, "return arguments[0].href;", link);
            if (o != null && o instanceof String) {
                hrefs.put(linkname, (String) o);
            } else {
                hrefs.put(linkname, null);
            }
        }

        // show found links
        String foundLinks = "";
        for (String linkname : hrefs.keySet()) {
            foundLinks += linkname + " - " + hrefs.get(linkname) + "\n";
        }

        LOGGER.info(description + " - Found links:\n" + foundLinks);

        /*
         * Get Webdriver cookies
         */
        final String cookieString = getCookieString(driver);

        /*
         * Check these links
         */
        int timeoutInMilliseconds = Constants.PAGE_LOAD_TIMEOUT_SECONDS * 1000;
        for (String key : hrefs.keySet()) {
            LOGGER.info("Checking " + key);
            final String url = hrefs.get(key);
            if (url != null) {
                try {
                    LOGGER.info("Connect to " + url);
                    URL u = new URL(url);
                    HttpURLConnection huc = (HttpURLConnection) u.openConnection();
                    huc.setRequestMethod("GET");
                    huc.setConnectTimeout(timeoutInMilliseconds);

                    if (cookieString.length() > 0) {
                        huc.setRequestProperty("Cookie", cookieString);
                    }

                    huc.connect();
                    final int responseCode = huc.getResponseCode();
                    LOGGER.info("Response code: " + responseCode);
                    NonFunctionalAssert.assertEquals(responseCode, 200, description + " Link " + key + ": " + url);
                } catch (Exception e) {
                    LOGGER.error("Error connecting to " + url, e);
                }
            } else {
                LOGGER.info("Skipping. No URL.");
            }
        }
    }

    public static WebDriver getLowestWebDriver(WebDriver driver) {
        if (driver instanceof EventFiringWebDriver) {
            EventFiringWebDriver efWd = (EventFiringWebDriver) driver;
            driver = efWd.getWrappedDriver();
        }

        if (driver instanceof Proxy) {
            Proxy proxy = (Proxy) driver;

            try {
                Field h = Proxy.class.getDeclaredField("h");
                h.setAccessible(true);
                Object handler = h.get(proxy);
                if (handler instanceof WebDriverProxy) {
                    WebDriverProxy webDriverProxy = (WebDriverProxy) handler;
                    driver = webDriverProxy.getWrappedWebDriver();
                }
            } catch (Exception e) {
                LOGGER.error("Could not get proxy instance out of proxied webDriver");
            }
        }

        return driver;
    }

    public static String getSessionId(WebDriver driver) {
        driver = getLowestWebDriver(driver);
        if (driver instanceof RemoteWebDriver) {
            RemoteWebDriver remoteWebDriver = (RemoteWebDriver) driver;
            return remoteWebDriver.getSessionId().toString();
        }

        return null;
    }

    public static Rectangle getViewport(WebDriver driver) {
        final ArrayList<Long> list = (ArrayList<Long>) JSUtils.executeScript(driver, "return [window.pageXOffset, window.pageYOffset, window.innerWidth, window.innerHeight];");
        return new Rectangle(list.get(0).intValue(), list.get(1).intValue(), list.get(2).intValue(), list.get(3).intValue());
    }

    /**
     * Initialize a {@link WebDriverKeepAliveSequence} and runs it with {@link Timer} in given interval.
     * This will keep the {@link WebDriver} alive, when acting with another driver in same test or waiting for something to happen in main thread.
     * NOTE: Please use this method with care AND clean up your Sequence by calling {@link WebDriverUtils#removeKeepAliveForWebDriver(WebDriver)}
     *
     * @param driver                     {@link WebDriver}
     * @param intervalSleepTimeInSeconds int
     * @param durationInSeconds          int
     * @return WebDriverKeepAliveSequence
     */
    public static WebDriverKeepAliveSequence keepWebDriverAlive(final WebDriver driver, final int intervalSleepTimeInSeconds, int durationInSeconds) {

        if (durationInSeconds > WebDriverKeepAliveSequence.GLOBAL_KEEP_ALIVE_TIMEOUT_IN_SECONDS) {
            LOGGER.warn(String.format("Your duration %s is higher than the global duration. We will set your duration to %s to avoid abuse.",
                    durationInSeconds,
                    WebDriverKeepAliveSequence.GLOBAL_KEEP_ALIVE_TIMEOUT_IN_SECONDS));

            durationInSeconds = WebDriverKeepAliveSequence.GLOBAL_KEEP_ALIVE_TIMEOUT_IN_SECONDS;
        }

        final WebDriverKeepAliveSequence webDriverKeepAliveSequence = new WebDriverKeepAliveSequence(driver);
        new Timer(intervalSleepTimeInSeconds * 1000, durationInSeconds * 1000).executeSequenceThread(webDriverKeepAliveSequence);

        return webDriverKeepAliveSequence;
    }

    /**
     * Removes an active {@link WebDriverKeepAliveSequence} if present.
     *
     * @param driver {@link WebDriver} the current driver
     */
    public static void removeKeepAliveForWebDriver(final WebDriver driver) {

        new WebDriverKeepAliveSequence().removeKeepAliveForDriver(driver);
    }

}
