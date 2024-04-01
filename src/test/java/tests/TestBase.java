package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import utils.TestData;

public class TestBase {
    static TestData testData;

    @BeforeAll
    static void setUpConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        testData = TestData.getInstance();
    }

    @AfterEach
    void clearEnv() {
        Selenide.closeWebDriver();
    }
}
