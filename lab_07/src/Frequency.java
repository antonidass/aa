package algos;

import java.util.*;

public class Frequency {
    public static ArrayList<HashMap<String, Object>> searchFrequency(Map<String, String> dict) {
        HashMap<Character, Integer> frequencyDict = new HashMap<>();

        for (String dict_key : dict.keySet()) {
            if (frequencyDict.keySet().contains(dict_key.charAt(0))) {
                frequencyDict.put(dict_key.charAt(0), frequencyDict.get(dict_key.charAt(0)) + 1);
            } else {
                frequencyDict.put(dict_key.charAt(0), 1);
            }
        }

        ArrayList<HashMap<String, Object>> result = new ArrayList<>();

        for (Character l : frequencyDict.keySet()) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("letter", l);
            item.put("count", frequencyDict.get(l));
            item.put("dict", new HashMap<String, String>());

            for (String dict_key : dict.keySet()) {
                if (dict_key.charAt(0) == l) {
                    ((HashMap<String, String>) item.get("dict")).put(dict_key, dict.get(dict_key));
                }
            }

            List list = new ArrayList(((HashMap<String, String>) item.get("dict")).entrySet());
            Collections.sort(list, (Comparator<Map.Entry<String, String>>) (a, b) -> a.getValue().compareTo(b.getValue()));
            LinkedHashMap<String, String> sortedDict = new LinkedHashMap<>();
            for (Object entry : list) {
                sortedDict.put(((Map.Entry) entry).getKey().toString(), ((Map.Entry) entry).getValue().toString());
            }
            item.put("dict", sortedDict);

            result.add(item);
        }
        return result;
    }


    public static String combinedSearch(String key, ArrayList<HashMap<String, Object>> segmentList) {
        HashMap<String, String> dict = new HashMap<>();

        for (int i = 0; i < segmentList.size(); i++) {
            if (segmentList.get(i).get("letter").equals(key.charAt(0))) {
                dict = (HashMap<String, String>) segmentList.get(i).get("dict");
            }
        }

        if (dict.size() == 0) {
            return null;
        }

        return Binary.searchBinary(key, dict);
    }
}
