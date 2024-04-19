package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
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
        Configuration.browser = System.getProperty("BROWSER", "chrome");
        Configuration.browserVersion = System.getProperty("BROWSER_VERSION", "123.0");
        Configuration.browserSize = System.getProperty("BROWSER_SIZE","1920x1080");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@"+System.getProperty("REMOTE_DRIVER_URL", "selenoid.autotests.cloud")+"/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

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
        Attach.browserConsoleLogs();

    }

}
