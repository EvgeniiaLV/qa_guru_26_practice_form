package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomItemFromArray(String[] array) {
        int index = getRandomInt(0, array.length - 1);

        return array[index];
    }

    public static String getRandomItemFromList(ArrayList<String> list) {
        int index = getRandomInt(0, list.size() - 1);

        return list.get(index);
    }

    public static ArrayList<String> getRandomItemFromTwoDimensionalList(ArrayList<ArrayList<String>> list) {
        int index = getRandomInt(0, list.size() - 1);

        return list.get(index);
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        return getRandomItemFromArray(genders);
    }

    public static String getRandomSubject() {
        String[] subjects = {"English", "Maths", "Physics", "Computer Science", "Chemistry", "Commerce", "Accounting", "Civics", "Biology"};

        return getRandomItemFromArray(subjects);
    }

    public static ArrayList<String> getRandomHobbies() {
        ArrayList<ArrayList<String>> hobbies = new ArrayList<>();
        hobbies.add(new ArrayList<>());
        hobbies.add(new ArrayList<>(Arrays.asList("Sports", "Reading", "Music")));
        hobbies.add(new ArrayList<>(Arrays.asList("Reading", "Music")));
        hobbies.add(new ArrayList<>(Arrays.asList("Sports", "Music")));
        hobbies.add(new ArrayList<>(Arrays.asList("Sports", "Reading")));
        hobbies.add(new ArrayList<>(Arrays.asList("Music")));
        hobbies.add(new ArrayList<>(Arrays.asList("Reading")));
        hobbies.add(new ArrayList<>(Arrays.asList("Sports")));

        return getRandomItemFromTwoDimensionalList(hobbies);
    }

    public static String getRandomPicture() {
        String[] states = {"ireland.jpg", "maldives.jpg", "panda.jpg", "toscana.jpg"};

        return getRandomItemFromArray(states);
    }

    public static String getRandomState() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        return getRandomItemFromArray(states);
    }

    public static String getRandomCity(String state) {
        ArrayList<String> cities = new ArrayList<>();
        switch (state) {
            case "NCR":
                cities.addAll(Arrays.asList("Delhi", "Gurgaon", "Noida"));
                break;
            case "Uttar Pradesh":
                cities.addAll(Arrays.asList("Agra", "Lucknow", "Merrut"));
                break;
            case "Haryana":
                cities.addAll(Arrays.asList("Karnal", "Panipat"));
                break;
            case "Rajasthan":
                cities.addAll(Arrays.asList("Jaipur", "Jaiselmer"));
                break;
            default:
                return null;
        }

        return getRandomItemFromList(cities);
    }


}
