package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {
    String userName = "Evgeniia";
    String lastName = "Liasheva";
    String email = "evgenia_lozovik@mail.ru";
    String gender = "Female";
    String mobile = "9110002233";
    String year = "2020";
    String month = "November";
    String day = "18"; // format: 2 digits, example - "01"
    String subject = "English";
    String checkBoxNameSport = "Sports";
    String checkBoxNameReading = "Reading";
    String checkBoxNameMusic = "Music";
    String picture = "ireland.jpg";
    String address = "Some address, 1/5";
    String state = "Haryana";
    String city = "Karnal";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true; // only for local test purposes
        //Configuration.timeout = 5000; // was changed for test purposes, default value is 4000
    }

    @AfterAll
    public static void afterAll() {
        WebDriverRunner.getWebDriver().close();
    }

    @Test
    void fillPracticeFormTest() {
        open("/automation-practice-form");
        // to hide ads
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");


        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email).click();

        // just to keep the initial solution:
        //$(By.xpath("//input[@name='gender' and @value='"+gender+"']/..//label")).scrollTo().click();

        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(mobile);

        /* just to keep the initial solution:
        SelenideElement datePickerInput = $("#dateOfBirthInput");
        datePickerInput.sendKeys(Keys.CONTROL + "a");
        datePickerInput.sendKeys(dateOfBirth.toString() + Keys.ENTER); */

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day).click(); // day format: 2 digits, example: "01"

        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byText(checkBoxNameSport)).click();
        $("#hobbiesWrapper").$(byText(checkBoxNameReading)).click();
        $("#hobbiesWrapper").$(byText(checkBoxNameMusic)).click();

        $("#uploadPicture").uploadFromClasspath(picture);
        $("#currentAddress").setValue(address);
        $("div#state input").setValue(state).pressEnter();
        $("div#city input").setValue(city).pressEnter();
        $("#submit").click();


        // assert
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(userName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(mobile));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(day + " " + month + "," + year));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subject));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subject));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(checkBoxNameSport + ", " + checkBoxNameReading + ", " + checkBoxNameMusic));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(picture));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(address));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(state + " " + city));

        /* just to keep the initial solution:
        List<SelenideElement> tableRows = $$(By.xpath("//div[@role='dialog']//tbody/tr"));
        tableRows.get(0).$$("td").get(1).shouldHave(text(userName+" "+ lastName));
        tableRows.get(1).$$("td").get(1).shouldHave(text(email));
        tableRows.get(2).$$("td").get(1).shouldHave(text(gender));
        tableRows.get(3).$$("td").get(1).shouldHave(text(mobile));
        tableRows.get(4).$$("td").get(1).shouldHave(text(day+" "+month+","+year));
        tableRows.get(5).$$("td").get(1).shouldHave(text(subject));
        tableRows.get(6).$$("td").get(1).shouldHave(text(checkBoxNameSport+", "+checkBoxNameReading+", "+checkBoxNameMusic));
        tableRows.get(7).$$("td").get(1).shouldHave(text(picture));
        tableRows.get(8).$$("td").get(1).shouldHave(text(address));
        tableRows.get(9).$$("td").get(1).shouldHave(text(state + " " + city)); */
    }
}
