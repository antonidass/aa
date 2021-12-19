import algos.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {


    public static void main(String[] args) throws IOException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("aa", "1fadf");
        hashMap.put("bb", "1fads");
        hashMap.put("ac", "3ad");
        hashMap.put("d", "4ada");
        hashMap.put("abcd", "2ad");
        hashMap.put("acd", "3ad");
        hashMap.put("acc", "3ad");
        hashMap.put("c", "3cxzxzcvcxzv");
        hashMap.put("cv", "6adadsfadsf");
        hashMap.put("cd", "6zdfsasdf");

        System.out.println("Result brute = " + Brute.searchBrute("cv", hashMap));
        System.out.println("Result binary = " + Binary.searchBinary("cv", hashMap));
        System.out.println("Result frequency = " + Frequency.combinedSearch("cv", Frequency.searchFrequency(hashMap)));
    }

}