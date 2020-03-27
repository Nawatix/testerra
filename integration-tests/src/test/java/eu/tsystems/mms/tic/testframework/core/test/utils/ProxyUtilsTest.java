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
 *      Eric Kubenka
 */
package eu.tsystems.mms.tic.testframework.core.test.utils;

import eu.tsystems.mms.tic.testframework.testing.TesterraTest;
import eu.tsystems.mms.tic.testframework.utils.ProxyUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Tests class ProxyUtils
 * <p>
 * Date: 27.03.2020
 * Time: 07:31
 *
 * @author Eric Kubenka
 */
public class ProxyUtilsTest extends TesterraTest {

    private static String PROXY_HOST_HTTP = "dummy-proxy-http.de";
    private static String PROXY_HOST_HTTPS = "dummy-proxy-https.de";
    private static String PROXY_PORT_HTTP = "8080";
    private static String PROXY_PORT_HTTPS = "8443";

    private static String BACKUP_PROXY_HTTP_HOST = "";
    private static String BACKUP_PROXY_HTTP_PORT = "";

    private static String BACKUP_PROXY_HTTPS_HOST = "";
    private static String BACKUP_PROXY_HTTPS_PORT = "";

    @BeforeClass()
    public void provideProxySettings() {

        BACKUP_PROXY_HTTP_HOST = System.getProperty("http.proxyHost", "");
        BACKUP_PROXY_HTTP_PORT = System.getProperty("http.proxyPort", "");

        BACKUP_PROXY_HTTPS_HOST = System.getProperty("https.proxyHost", "");
        BACKUP_PROXY_HTTPS_PORT = System.getProperty("https.proxyPort", "");

        System.setProperty("http.proxyHost", PROXY_HOST_HTTP);
        System.setProperty("http.proxyPort", PROXY_PORT_HTTP);

        System.setProperty("https.proxyHost", PROXY_HOST_HTTPS);
        System.setProperty("https.proxyPort", PROXY_PORT_HTTPS);

    }

    @AfterClass()
    public void removeProxySettings() {
        System.setProperty("http.proxyHost", BACKUP_PROXY_HTTP_HOST);
        System.setProperty("http.proxyPort", BACKUP_PROXY_HTTP_PORT);

        System.setProperty("https.proxyHost", BACKUP_PROXY_HTTPS_HOST);
        System.setProperty("https.proxyPort", BACKUP_PROXY_HTTPS_PORT);
    }

    @Test
    public void testT01_getHttpProxyString() {

        final String expectedUrlString = PROXY_HOST_HTTP + ":" + PROXY_PORT_HTTP;

        final String actualProxyString = ProxyUtils.getSystemHttpProxyString();
        Assert.assertEquals(actualProxyString, expectedUrlString, "Generated proxy string equals.");
    }

    @Test
    public void testT02_getHttpsProxyString() {

        final String expectedUrlString = PROXY_HOST_HTTPS + ":" + PROXY_PORT_HTTPS;

        final String actualProxyString = ProxyUtils.getSystemHttpsProxyString();
        Assert.assertEquals(actualProxyString, expectedUrlString, "Generated proxy string equals.");
    }

    @Test
    public void testT03_getHttpProxyUrl() {

        final String expectedUrlString = "http://" + PROXY_HOST_HTTP + ":" + PROXY_PORT_HTTP;

        final URL actualProxyUrl = ProxyUtils.getSystemHttpProxyUrl();
        Assert.assertNotNull(actualProxyUrl, "Proxy url generated!");

        final String actualProxyString = actualProxyUrl.toString();
        Assert.assertEquals(actualProxyString, expectedUrlString, "Generated proxy string equals.");
    }

    @Test
    public void testT04_getHttpsProxyUrl() {

        final String expectedUrlString = "http://" + PROXY_HOST_HTTPS + ":" + PROXY_PORT_HTTPS;

        final URL actualProxyUrl = ProxyUtils.getSystemHttpsProxyUrl();
        Assert.assertNotNull(actualProxyUrl, "Proxy url generated!");

        final String actualProxyString = actualProxyUrl.toString();
        Assert.assertEquals(actualProxyString, expectedUrlString, "Generated proxy string equals.");
    }
}
