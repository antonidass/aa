package algos;

import java.util.ArrayList;

public class Utils {
    public static ArrayList<Integer> createCities(int count) {
        ArrayList<Integer> cities = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            cities.add(i);
        }

        return cities;
    }
}
