package fr.lmo.aoc2022;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class D03 extends AoCHelper {

    public void run() {
        System.out.println("Test : " + findPriorities(stream(getTestInputPath()), 4));
        System.out.println("Real file : " + findPriorities(stream(getInputPath()), 4));

        System.out.println("Part 2: ");
        System.out.println("Test : " + findPriorities(stream(getTestInputPath()), 14));
        System.out.println("Real : " + findPriorities(stream(getInputPath()), 14));
    }

    private Integer findPriorities(Stream<String> s, int smtg) {
        return s.mapToInt(line -> {
            String firstHalf = line.substring(0, line.length() / 2);
            String secondHalf = line.substring(line.length() / 2);
            char mostCommon = mostCommonChar(firstHalf, secondHalf);

            return mostCommon - "a".codePointAt(0);
        }).sum();
    }

    private char mostCommonChar(String firstHalf, String secondHalf) {
        // Create a map to store the frequencies of each character in the two strings
        Map<Character, Integer> charFrequencies = new HashMap<>();

        // Iterate over each character in the first string and add its frequency to the map
        for (int i = 0; i < firstHalf.length(); i++) {
            char ch = firstHalf.charAt(i);
            charFrequencies.put(ch, charFrequencies.getOrDefault(ch, 0) + 1);
        }

        // Iterate over each character in the second string and add its frequency to the map
        for (int i = 0; i < secondHalf.length(); i++) {
            char ch = secondHalf.charAt(i);
            charFrequencies.put(ch, charFrequencies.getOrDefault(ch, 0) + 1);
        }

        // Iterate over the map and find the character with the highest frequency
        char mostCommonChar = ' ';
        int maxFrequency = 0;
        for (Map.Entry<Character, Integer> entry : charFrequencies.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostCommonChar = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        return mostCommonChar;
    }
}
