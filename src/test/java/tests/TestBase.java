package tests;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.TestData;

import java.util.Map;

public class TestBase {
    static TestData testData;

    @BeforeAll
    static void setUpConfig() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@"+System.getProperty("remoteDriverURL", "selenoid.autotests.cloud")+"/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        proxy.setHttpProxy("139.99.148.90:3128");

        capabilities.setCapability(CapabilityType.PROXY,proxy);

        Configuration.browserCapabilities = capabilities;
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize","1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion", "122");

        testData = TestData.getInstance();
    }

    @AfterEach
    void clearEnv() {
        Selenide.closeWebDriver();
    }

    @AfterEach
    void addAttachment() {
        Attach.pageSource();
        Attach.screenshotAs("Last screenshot");
        Attach.addVideo();
        if (!WebDriverRunner.isFirefox()) {
            Attach.browserConsoleLogs();
        }

    }

}
