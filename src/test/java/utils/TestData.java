package utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class TestData {
    private static TestData INSTANCE;
    private final Faker faker = new Faker(new Locale("en-GB"));

    private TestData() {
    }

    public static TestData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestData();
        }

        return INSTANCE;
    }

    public String getRandomName() {

        return faker.name().firstName();

    }

    public String getRandomLastName() {

        return faker.name().lastName();

    }

    public String getRandomEmail() {

        return faker.internet().emailAddress();

    }

    public String getRandomMobile() {

        return faker.phoneNumber().subscriberNumber(10);

    }

    public String getRandomGender() {

        return faker.options().option("Male", "Female", "Other");

    }

    public String getRandomSubject() {

        return faker.options().option("English", "Maths", "Physics", "Computer Science", "Chemistry", "Commerce", "Accounting", "Civics", "Biology");

    }

    public String getRandomAddress() {

        return faker.address().streetAddress();

    }

    public String getRandomPicture() {

        return faker.options().option("ireland.jpg", "maldives.jpg", "panda.jpg", "toscana.jpg");

    }

    public String getRandomState() {

        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");

    }

    public Date getRandomBirthday() {

        return faker.date().birthday();

    }

    public String getRandomDay(Date birthday) {

        SimpleDateFormat sdfDay = new SimpleDateFormat("dd", Locale.ENGLISH);

        return sdfDay.format(birthday); // format: 2 digits, example - "01";

    }

    public String getRandomMonth(Date birthday) {

        SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM", Locale.ENGLISH);

        return sdfMonth.format(birthday);

    }

    public String getRandomYear(Date birthday) {

        SimpleDateFormat sdfYear = new SimpleDateFormat("YYYY", Locale.ENGLISH);

        return sdfYear.format(birthday);

    }

    public ArrayList<String> getRandomHobbies() {

        return faker.options().option(new ArrayList<>(Arrays.asList("Sports", "Reading", "Music")),
                new ArrayList<>(Arrays.asList("Reading", "Music")),
                new ArrayList<>(Arrays.asList("Sports", "Music")),
                new ArrayList<>(Arrays.asList("Sports", "Reading")),
                new ArrayList<>(Arrays.asList("Music")),
                new ArrayList<>(Arrays.asList("Reading")),
                new ArrayList<>(Arrays.asList("Sports")));
    }

    public String prepareHobbiesForCheck(ArrayList<String> hobbies) {
        String checkHobbies = "";
        for (int i = 0; i < hobbies.size(); i++) {
            checkHobbies = checkHobbies + hobbies.get(i) + ", ";
        }

        if (hobbies.size() > 0) {
            checkHobbies = checkHobbies.substring(0, checkHobbies.length() - 2);
        }

        return checkHobbies;
    }

    public String getRandomCity(String state) {
        String city;
        switch (state) {
            case "NCR":
                city = new Faker().options().option("Delhi", "Gurgaon", "Noida");
                break;
            case "Uttar Pradesh":
                city = new Faker().options().option("Agra", "Lucknow", "Merrut");
                break;
            case "Haryana":
                city = new Faker().options().option("Karnal", "Panipat");
                break;
            case "Rajasthan":
                city = new Faker().options().option("Jaipur", "Jaiselmer");
                break;
            default:
                return null;
        }

        return city;
    }

}
