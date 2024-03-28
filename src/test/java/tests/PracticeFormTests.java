package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import utils.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PracticeFormTests extends TestBase {
    PracticeFormPage practiceFormPage = new PracticeFormPage();

    Faker faker = new Faker(new Locale("en-GB"));

    SimpleDateFormat sdfYear = new SimpleDateFormat("YYYY");
    SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM");
    SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
    Date birthday = faker.date().birthday();
    String userName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            mobile = faker.phoneNumber().subscriberNumber(10),
            day = sdfDay.format(birthday), // format: 2 digits, example - "01"
            month = sdfMonth.format(birthday),
            year = sdfYear.format(birthday),
            gender = RandomUtils.getRandomGender(),
            subject = RandomUtils.getRandomSubject(),
            picture = RandomUtils.getRandomPicture(),
            address = faker.address().streetAddress(),
            state = RandomUtils.getRandomState(),
            city = RandomUtils.getRandomCity(state);

    ArrayList<String> hobbies = RandomUtils.getRandomHobbies();

    @Test
    void successfulRegistrationTestFilledAllFields() {

        practiceFormPage.openPage().
                setFirstName(userName).
                setLastName(lastName).
                setUserEmail(email).
                setGender(gender).
                setMobileNumber(mobile).
                setDateOfBirth(year, month, day).
                setSubject(subject);
        for (int i = 0; i < hobbies.size(); i++) {
            practiceFormPage.setHobby(hobbies.get(i));
        }
        practiceFormPage.uploadPicture(picture).
                setAddress(address).
                setState(state).
                setCity(city).
                clickSubmit();

        practiceFormPage.checkResult("Student Name", userName + " " + lastName).
                checkResult("Student Email", email).
                checkResult("Gender", gender).
                checkResult("Mobile", mobile).
                checkResult("Date of Birth", day + " " + month + "," + year).
                checkResult("Subjects", subject);

        if (hobbies.size() > 0) {
            String checkHobbies = "";
            for (int i = 0; i < hobbies.size(); i++) {
                checkHobbies = checkHobbies + hobbies.get(i) + ", ";
            }
            practiceFormPage.checkResult("Hobbies", checkHobbies.substring(0, checkHobbies.length() - 2));
        }

        practiceFormPage.checkResult("Picture", picture).
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
