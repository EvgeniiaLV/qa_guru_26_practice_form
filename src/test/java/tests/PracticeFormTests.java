package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import java.util.ArrayList;
import java.util.Date;

@Tag("simple")
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
    void successfulRegistrationTestFilledAllFields() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        practiceFormPage.openPage().
                setFirstName(userName).
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
                setCity(city).
                clickSubmit();

        practiceFormPage.checkResult("Student Name", userName + " " + lastName).
                checkResult("Student Email", email).
                checkResult("Gender", gender).
                checkResult("Mobile", mobile).
                checkResult("Date of Birth", day + " " + month + "," + year).
                checkResult("Subjects", subject).
                checkResult("Hobbies", checkHobbies).
                checkResult("Picture", picture).
                checkResult("Address", address).
                checkResult("State and City", state + " " + city);
    }

    @Test
    void successfulRegistrationTestFilledMandatoryFields() {

        practiceFormPage.openPage().
                setFirstName(userName).
                setLastName(lastName).
                setGender(gender).
                setMobileNumber(mobile).
                setDateOfBirth(year, month, day).
                clickSubmit();

        practiceFormPage.checkResult("Student Name", userName + " " + lastName).
                checkResult("Gender", gender).
                checkResult("Mobile", mobile).
                checkResult("Date of Birth", day + " " + month + "," + year);
    }

    @Test
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
    @Disabled("The test is out of scope for now...")
    void negativeRegistrationTestMissingGender() {

        practiceFormPage.openPage().
                setFirstName(userName).
                setLastName(lastName).
                setMobileNumber(mobile).
                setDateOfBirth(year, month, day).
                clickSubmit();

        practiceFormPage.checkEmptyMobileNumber();
    }
}
