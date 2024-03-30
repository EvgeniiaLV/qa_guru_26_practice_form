package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import utils.TestData;

import java.util.ArrayList;
import java.util.Date;

public class PracticeFormTests extends TestBase {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    Date birthday = TestData.getRandomBirthday();
    String day = TestData.getRandomDay(birthday),
            month = TestData.getRandomMonth(birthday),
            year = TestData.getRandomYear(birthday),
            userName = TestData.getRandomName(),
            lastName = TestData.getRandomLastName(),
            email = TestData.getRandomEmail(),
            mobile = TestData.getRandomMobile(),
            gender = TestData.getRandomGender(),
            subject = TestData.getRandomSubject(),
            picture = TestData.getRandomPicture(),
            address = TestData.getRandomAddress(),
            state = TestData.getRandomState(),
            city = TestData.getRandomCity(state);

    ArrayList<String> hobbies = TestData.getRandomHobbies();
    String checkHobbies = TestData.prepareHobbiesForCheck(hobbies);


    @Test
    void successfulRegistrationTestFilledAllFields() {

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
}
