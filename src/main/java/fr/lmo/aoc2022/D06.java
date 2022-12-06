package fr.lmo.aoc2022;

import java.util.stream.Stream;

public class D06 extends AoCHelper {

    public void run() {
        System.out.println("Test : " + findMarker(stream(getTestInputPath()), 4));
        System.out.println("Real file : " + findMarker(stream(getInputPath()), 4));

        System.out.println("Part 2: ");
        System.out.println("Test : " + findMarker(stream(getTestInputPath()), 14));
        System.out.println("Real : " + findMarker(stream(getInputPath()), 14));
    }

    private Integer findMarker(Stream<String> s, int windowSize) {
        String line = s.findFirst().orElseThrow();
        for (int i = 1; i < line.length(); i++) {
            int minRange = Math.max(i - windowSize, 0);
            String window = line.substring(minRange, i);
            if (window.chars().distinct().count() == windowSize) {
                return i;
            }
        }
        return -1;
    }
}
