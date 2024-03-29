package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import utils.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class PracticeFormTests extends TestBase {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    Faker faker = new Faker(new Locale("en-GB"));

    SimpleDateFormat sdfYear = new SimpleDateFormat("YYYY");
    SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM");
    SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
    Date birthday = faker.date().birthday();
    String day = sdfDay.format(birthday), // format: 2 digits, example - "01"
            month = sdfMonth.format(birthday),
            year = sdfYear.format(birthday);
    String userName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            mobile = faker.phoneNumber().subscriberNumber(10),
            gender = faker.options().option("Male", "Female", "Other"),
            subject = faker.options().option("English", "Maths", "Physics", "Computer Science", "Chemistry", "Commerce", "Accounting", "Civics", "Biology"),
            picture = faker.options().option("ireland.jpg", "maldives.jpg", "panda.jpg", "toscana.jpg"),
            address = faker.address().streetAddress(),
            state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            city = RandomUtils.getRandomCity(state);

    ArrayList<String> hobbies = faker.options().option(new ArrayList<>(),
            new ArrayList<>(Arrays.asList("Sports", "Reading", "Music")),
            new ArrayList<>(Arrays.asList("Reading", "Music")),
            new ArrayList<>(Arrays.asList("Sports", "Music")),
            new ArrayList<>(Arrays.asList("Sports", "Reading")),
            new ArrayList<>(Arrays.asList("Music")),
            new ArrayList<>(Arrays.asList("Reading")),
            new ArrayList<>(Arrays.asList("Sports")));
    String checkHobbies = RandomUtils.prepareHobbiesForCheck(hobbies);


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
