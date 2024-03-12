package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
    }

    @AfterAll
    public static void afterAll() {
        WebDriverRunner.getWebDriver().close();
    }

    @Test
    void fillPracticeFormTest() {
        String userName = "Evgeniia";
        String lastName = "Liasheva";
        String email = "evgenia_lozovik@mail.ru";
        String gender = "Female";
        String mobile = "9110002233";
        LocalDate dateOfBirth = LocalDate.parse("2020-11-18");
        String address = "Some address, 1/5";

        open("/automation-practice-form");
        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(By.xpath("//input[@name='gender' and @value='"+gender+"']/..//label")).scrollTo().click();
        $("#userNumber").setValue(mobile);
        SelenideElement datePickerInput = $("#dateOfBirthInput");
        datePickerInput.sendKeys(Keys.CONTROL + "a");
        datePickerInput.sendKeys(dateOfBirth.toString() + Keys.ENTER);
        SelenideElement subjectPickerInput =$(By.cssSelector("#subjectsInput"));
        subjectPickerInput.click();
        sleep(500);
        subjectPickerInput.sendKeys("Eng");
        sleep(500);
        subjectPickerInput.sendKeys(  Keys.ARROW_DOWN + "" + Keys.ENTER);
        sleep(500);
        $(By.xpath("//input[@id='hobbies-checkbox-1']/..//label")).scrollTo().click();
        $(By.xpath("//input[@id='hobbies-checkbox-2']/..//label")).scrollTo().click();
        $(By.xpath("//input[@id='hobbies-checkbox-3']/..//label")).scrollTo().click();

        $("#uploadPicture").sendKeys("C:\\Users\\Evgeniia\\Desktop\\1.jpg");
        $("#currentAddress").setValue(address);
        $("#state").click();
        sleep(500);
        $("#state input").sendKeys(Keys.ENTER);
        sleep(500);
        $("#city").click();
        sleep(500);
        $("#city input").sendKeys(Keys.ENTER);
        sleep(500);
        $("#submit").click();

        List<SelenideElement> tableRows = $$(By.xpath("//div[@role='dialog']//tbody/tr"));
        tableRows.get(0).$$("td").get(1).shouldHave(text(userName+" "+ lastName));
        tableRows.get(1).$$("td").get(1).shouldHave(text(email));
        tableRows.get(2).$$("td").get(1).shouldHave(text(gender));
        tableRows.get(3).$$("td").get(1).shouldHave(text(mobile));

        String pattern = "dd MMMM,yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        tableRows.get(4).$$("td").get(1).shouldHave(text(formatter.format(dateOfBirth)));

        tableRows.get(5).$$("td").get(1).shouldHave(text("English"));
        tableRows.get(6).$$("td").get(1).shouldHave(text("Sports, Reading, Music"));
        tableRows.get(7).$$("td").get(1).shouldHave(text("1.jpg"));
        tableRows.get(8).$$("td").get(1).shouldHave(text(address));
        tableRows.get(9).$$("td").get(1).shouldHave(text("NCR Delhi"));
    }
}
