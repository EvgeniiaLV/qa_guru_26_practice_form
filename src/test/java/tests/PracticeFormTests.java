package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.Owner;
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

        step("Open Practice form", () -> {
            practiceFormPage.openPage();
        });

        step("Fill in first name: " + userName, () -> {
            practiceFormPage.setFirstName(userName);});

        step("Fill in last name: " + lastName, () -> {
            practiceFormPage.setLastName(lastName);});

        step("Fill in email: " + email, () -> {
            practiceFormPage.setUserEmail(email);});

        step("Select gender: " + gender, () -> {
            practiceFormPage.setGender(gender);});

        step("Fill in mobile number: " + mobile, () -> {
            practiceFormPage.setMobileNumber(mobile);});

        step("Select birthday: " + birthday, () -> {
            practiceFormPage.setDateOfBirth(year, month, day);});

        step("Fill in subject: " + lastName, () -> {
            practiceFormPage.setSubject(subject);});

        step("Select hobbies: " + hobbies, () -> {
            practiceFormPage.setHobbies(hobbies);});

        step("Upload picture: " + picture, () -> {
            practiceFormPage.uploadPicture(picture);});

        step("Fill in address: " + address, () -> {
            practiceFormPage.setAddress(address);});

        step("Choose state: " + lastName, () -> {
            practiceFormPage.setState(state);});

        step("Choose city: " + lastName, () -> {
            practiceFormPage.setCity(city);
        });

        step("Submit", () -> {
            practiceFormPage.clickSubmit();
        });

        step("Verify student name: "+ userName + " " + lastName, () -> {
            practiceFormPage.checkResult("Student Name", userName + " " + lastName);});

        step("Verify email: "+ email, () -> {
            practiceFormPage.checkResult("Student Email", email);});

        step("Verify gender: "+ gender, () -> {
            practiceFormPage.checkResult("Gender", gender);});

        step("Verify mobile: "+ mobile, () -> {
            practiceFormPage.checkResult("Mobile", mobile);});

        step("Verify birthday: "+ birthday, () -> {
            practiceFormPage.checkResult("Date of Birth", day + " " + month + "," + year);});

        step("Verify subject: "+ subject, () -> {
            practiceFormPage.checkResult("Subjects", subject);});

        step("Verify hobbies: "+ checkHobbies, () -> {
            practiceFormPage.checkResult("Hobbies", checkHobbies);});

        step("Verify picture: "+ picture, () -> {
            practiceFormPage.checkResult("Picture", picture);});

        step("Verify address: "+ address, () -> {
            practiceFormPage.checkResult("Address", address);});

        step("Verify state and city: "+ state + " " + city, () -> {
            practiceFormPage.checkResult("State and City", state + " " + city);
        });

        Attach.addVideo();
    }

    @Test
    @Owner("Evgeniia Liasheva")
    @DisplayName("Positive check: mandatory fields are filled with correct data")
    void successfulRegistrationTestFilledMandatoryFields() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Practice form", () -> {
            practiceFormPage.openPage();
        });

        step("Fill in first name: " + userName, () -> {
            practiceFormPage.setFirstName(userName);});

        step("Fill in last name: " + lastName, () -> {
            practiceFormPage.setLastName(lastName);});

        step("Select gender: " + gender, () -> {
            practiceFormPage.setGender(gender);});

        step("Fill in mobile number: " + mobile, () -> {
            practiceFormPage.setMobileNumber(mobile);});

        step("Select birthday: " + birthday, () -> {
            practiceFormPage.setDateOfBirth(year, month, day);});

        step("Submit", () -> {
            practiceFormPage.clickSubmit();
        });

        step("Verify student name: "+ userName + " " + lastName, () -> {
            practiceFormPage.checkResult("Student Name", userName + " " + lastName);});

        step("Verify gender: "+ gender, () -> {
            practiceFormPage.checkResult("Gender", gender);});

        step("Verify mobile: "+ mobile, () -> {
            practiceFormPage.checkResult("Mobile", mobile);});

        step("Verify birthday: "+ birthday, () -> {
            practiceFormPage.checkResult("Date of Birth", day + " " + month + "," + year);});

        Attach.addVideo();
    }

    @Test
    @DisplayName("Negative check with empty mobile number")
    void negativeRegistrationTestMissingMobileNumber() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Practice form", () -> {
            practiceFormPage.openPage();
        });

        step("Fill in first name: " + userName, () -> {
            practiceFormPage.setFirstName(userName);});

        step("Fill in last name: " + lastName, () -> {
            practiceFormPage.setLastName(lastName);});

        step("Select gender: " + gender, () -> {
            practiceFormPage.setGender(gender);});

        step("Select birthday: " + birthday, () -> {
            practiceFormPage.setDateOfBirth(year, month, day);});

        step("Submit", () -> {
            practiceFormPage.clickSubmit();
        });

        step("Check that mobile number is empty", () -> {
            practiceFormPage.checkEmptyMobileNumber();
        });

        Attach.addVideo();
    }

    @Test
    @Owner("Evgeniia Liasheva")
    @Disabled("The test is out of scope for now...")
    void negativeRegistrationTestMissingGender() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Practice form", () -> {
            practiceFormPage.openPage();
        });

        step("Fill in first name: " + userName, () -> {
            practiceFormPage.setFirstName(userName);});

        step("Fill in last name: " + lastName, () -> {
            practiceFormPage.setLastName(lastName);});

        step("Fill in mobile number: " + mobile, () -> {
            practiceFormPage.setMobileNumber(mobile);});

        step("Select birthday: " + birthday, () -> {
            practiceFormPage.setDateOfBirth(year, month, day);});

        step("Submit", () -> {
            practiceFormPage.clickSubmit();
        });

        step("Check that gender is not selected", () -> {
            practiceFormPage.checkEmptyGender();
        });

        Attach.addVideo();
    }
}
