package com.strel.lab.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by DemonSlayer on 01.10.2015.
 */
public class TextProcessor {
    public static ArrayList<String> process(String input) {
        input = input.toLowerCase();
        Map<String, Integer> words = new TreeMap<>();
        String[] arr = input.split("( +|(!|\\?|,|\\.|\\t|\\r|\\n))");



        for (String word : arr) {
            if (words.containsKey(word)) {
                words.put(word, words.get(word) + 1);
            }
            else {
                words.put(word, 1);
            }
        }

        ArrayList<String> result = new ArrayList<>();
        words.forEach((word, count) -> {
            if (count == 1)
                result.add(word);
        });

        return result;
    }
}
