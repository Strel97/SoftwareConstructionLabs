package com.strel.lab.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by DemonSlayer on 01.10.2015.
 */
public class TextProcessor {
    public static ArrayList<String> process(String input) {
        Map<String, Integer> words = new HashMap<>();
        String[] arr = input.split("( +|(!|\\?|,|\\.) )");

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
            if (count == 2)
                result.add(word);
        });

        return result;
    }
}
