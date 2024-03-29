package utils;

import com.github.javafaker.Faker;

import java.util.ArrayList;

public class RandomUtils {
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
