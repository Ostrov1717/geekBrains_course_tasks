package org.example.lesson5;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Map<String, Integer> countWordOccurrences(String[] words) {
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }
        return wordCountMap;
    }

    public static void main(String[] args) {
        String[] wordsArray = {"apple", "banana", "apple", "orange", "banana", "kiwi", "grape", "kiwi", "melon", "peach",
                "plum", "pear", "apple", "banana", "kiwi", null, "peach", "plum", "pear", "apple", "banana", "kiwi", "melon", "peach", null};
        System.out.println(countWordOccurrences(wordsArray));

    }
}
