package utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class TestData {

    private static final Faker faker = new Faker(new Locale("en-GB"));

    public static String getRandomName() {

        return faker.name().firstName();

    }

    public static String getRandomLastName() {

        return faker.name().lastName();

    }

    public static String getRandomEmail() {

        return faker.internet().emailAddress();

    }

    public static String getRandomMobile() {

        return faker.phoneNumber().subscriberNumber(10);

    }

    public static String getRandomGender() {

        return faker.options().option("Male", "Female", "Other");

    }

    public static String getRandomSubject() {

        return faker.options().option("English", "Maths", "Physics", "Computer Science", "Chemistry", "Commerce", "Accounting", "Civics", "Biology");

    }

    public static String getRandomAddress() {

        return faker.address().streetAddress();

    }

    public static String getRandomPicture() {

        return faker.options().option("ireland.jpg", "maldives.jpg", "panda.jpg", "toscana.jpg");

    }

    public static String getRandomState() {

        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");

    }

    public static Date getRandomBirthday() {

        return faker.date().birthday();

    }

    public static String getRandomDay(Date birthday) {

        SimpleDateFormat sdfDay = new SimpleDateFormat("dd", Locale.ENGLISH);

        return sdfDay.format(birthday); // format: 2 digits, example - "01";

    }

    public static String getRandomMonth(Date birthday) {

        SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM", Locale.ENGLISH);

        return sdfMonth.format(birthday);

    }

    public static String getRandomYear(Date birthday) {

        SimpleDateFormat sdfYear = new SimpleDateFormat("YYYY", Locale.ENGLISH);

        return sdfYear.format(birthday);

    }

    public static ArrayList<String> getRandomHobbies() {

        return faker.options().option(new ArrayList<>(),
                new ArrayList<>(Arrays.asList("Sports", "Reading", "Music")),
                new ArrayList<>(Arrays.asList("Reading", "Music")),
                new ArrayList<>(Arrays.asList("Sports", "Music")),
                new ArrayList<>(Arrays.asList("Sports", "Reading")),
                new ArrayList<>(Arrays.asList("Music")),
                new ArrayList<>(Arrays.asList("Reading")),
                new ArrayList<>(Arrays.asList("Sports")));

    }

    public static String prepareHobbiesForCheck(ArrayList<String> hobbies) {
        String checkHobbies = "";
        for (int i = 0; i < hobbies.size(); i++) {
            checkHobbies = checkHobbies + hobbies.get(i) + ", ";
        }

        if (hobbies.size() > 0) {
            checkHobbies = checkHobbies.substring(0, checkHobbies.length() - 2);
        }

        return checkHobbies;
    }

    public static String getRandomCity(String state) {
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
