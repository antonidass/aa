package algos;

import java.util.Map;
import java.util.Objects;

public class Brute {
    public static String searchBrute(String key, Map<String, String> dict) {
        for (String dictKey : dict.keySet()) {
            if (Objects.equals(key, dictKey)) {
                return dict.get(key);
            }
        }
        return null;
    }
}
