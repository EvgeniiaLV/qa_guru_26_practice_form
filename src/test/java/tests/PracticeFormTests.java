package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import java.util.ArrayList;
import java.util.Date;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Tag("practiceForm")
public class PracticeFormTests extends TestBase {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    Date birthday = testData.getRandomBirthday();
    String day = testData.getRandomDay(birthday),
            month = testData.getRandomMonth(birthday),
            year = testData.getRandomYear(birthday),
            userName = testData.getRandomName(),
            lastName = testData.getRandomLastName(),
            email = testData.getRandomEmail(),
            mobile = testData.getRandomMobile(),
            gender = testData.getRandomGender(),
            subject = testData.getRandomSubject(),
            picture = testData.getRandomPicture(),
            address = testData.getRandomAddress(),
            state = testData.getRandomState(),
            city = testData.getRandomCity(state);

    ArrayList<String> hobbies = testData.getRandomHobbies();
    String checkHobbies = testData.prepareHobbiesForCheck(hobbies);


    @Test
    @Owner("Evgeniia Liasheva")
    @DisplayName("Positive check: all fields are filled with correct data")
    void successfulRegistrationTestFilledAllFields() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open the form", () -> {
                practiceFormPage.openPage();});

        step("Fill in the form", () -> {
                practiceFormPage.setFirstName(userName).
                setLastName(lastName).
                setUserEmail(email).
                setGender(gender).
                setMobileNumber(mobile).
                setDateOfBirth(year, month, day).
                setSubject(subject).
                setHobbies(hobbies).
                uploadPicture(picture).
                setAddress(address).
                setState(state).
                setCity(city); ;});

        step("submit", () -> {
            practiceFormPage.clickSubmit();});

        step("Verify the result", () -> {
        practiceFormPage.checkResult("Student Name", userName + " " + lastName).
                checkResult("Student Email", email).
                checkResult("Gender", gender).
                checkResult("Mobile", mobile).
                checkResult("Date of Birth", day + " " + month + "," + year).
                checkResult("Subjects", subject).
                checkResult("Hobbies", checkHobbies).
                checkResult("Picture", picture).
                checkResult("Address", address).
                checkResult("State and City", state + " " + city);});

        Attach.addVideo();
    }

    @Test
    @Owner("Evgeniia Liasheva")
    @DisplayName("Positive check: mandatory fields are filled with correct data")
    void successfulRegistrationTestFilledMandatoryFields() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open the form", () -> {
            practiceFormPage.openPage();});

        step("Fill in the form", () -> {
            practiceFormPage.setFirstName(userName).
                setLastName(lastName).
                setGender(gender).
                setMobileNumber(mobile).
                setDateOfBirth(year, month, day).
                clickSubmit();});


        step("Verify the result", () -> {
            practiceFormPage.checkResult("Student Name", userName + " " + lastName).
                checkResult("Gender", gender).
                checkResult("Mobile", mobile).
                checkResult("Date of Birth", day + " " + month + "," + year);});
        Attach.addVideo();
    }

    @Test
    @DisplayName("Negative check with empty mobile number")
    void negativeRegistrationTestMissingMobileNumber() {

        practiceFormPage.openPage().
                setFirstName(userName).
                setLastName(lastName).
                setGender(gender).
                setDateOfBirth(year, month, day).
                clickSubmit();

        practiceFormPage.checkEmptyMobileNumber();
    }

    @Test
    @Owner("Evgeniia Liasheva")
    @Disabled("The test is out of scope for now...")
    void negativeRegistrationTestMissingGender() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open the form", () -> {
            practiceFormPage.openPage();});

        step("Fill in the form", () -> {
        practiceFormPage.setFirstName(userName).
                setLastName(lastName).
                setMobileNumber(mobile).
                setDateOfBirth(year, month, day);});

        step("Submit", () -> {
                practiceFormPage.clickSubmit();});

        step("Fill in the form", () -> {
        practiceFormPage.checkEmptyMobileNumber();});
    }
}
