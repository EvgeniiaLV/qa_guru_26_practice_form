package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

public class PracticeFormTests extends TestBase {
    private final String userName = "Evgeniia",
            lastName = "Liasheva",
            email = "evlv@mail.ru",
            gender = "Female",
            mobile = "9110002233",
            year = "2020",
            month = "November",
            day = "18", // format: 2 digits, example - "01"
            subject = "English",
            checkBoxNameSport = "Sports",
            checkBoxNameReading = "Reading",
            checkBoxNameMusic = "Music",
            picture = "ireland.jpg",
            address = "Some address, 1/5",
            state = "Haryana",
            city = "Karnal";

    PracticeFormPage practiceFormPage = new PracticeFormPage();

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
                setHobby(checkBoxNameSport).
                setHobby(checkBoxNameReading).
                setHobby(checkBoxNameMusic).
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
                checkResult("Hobbies", checkBoxNameSport + ", " + checkBoxNameReading + ", " + checkBoxNameMusic).
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

        practiceFormPage.checkResult("Student Name", userName + " " + lastName).
                checkResult("Student Email", email).
                checkResult("Gender", gender).
                checkResult("Date of Birth", day + " " + month + "," + year);

    }

}
