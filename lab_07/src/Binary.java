package algos;

import java.util.*;

public class Binary {
    public static String searchBinary(String key, Map<String, String> dict) {
        ArrayList<String> keys = new ArrayList<>(dict.keySet());
        Collections.sort(keys);

        int l = 0, r = keys.size() - 1;
        int m;
        while (l <= r) {
            m = (l  + (r - l) / 2);

            if (key.equals(keys.get(m))) {
                return dict.get(key);
            }
            else if (key.compareTo(keys.get(m)) > 0) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return null;
    }
}
