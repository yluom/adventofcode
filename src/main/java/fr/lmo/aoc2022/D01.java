package fr.lmo.aoc2022;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class D01 extends AoCHelper {

    public void run() {
        System.out.println("Test calories : " + findMostCalories(stream(getTestInputPath(), String::valueOf), 1));
        System.out.println("Real file calories : " + findMostCalories(stream(getInputPath(), String::valueOf), 1));

        System.out.println("Test calories : " + findMostCalories(stream(getTestInputPath(), String::valueOf), 3));
        System.out.println("Real file calories : " + findMostCalories(stream(getInputPath(), String::valueOf), 3));
    }

    private Integer findMostCalories(Stream<String> stream, int topOfMaxes) {

        int current = 0;
        List<String> l = stream.toList();
        List<Integer> maxes= new ArrayList<>();
        for (String s : l) {
            if (s.isEmpty()) {
                maxes.add(current);
                current = 0;
            } else {
                current += Integer.parseInt(s);
            }
        }
        return maxes.stream().sorted(Comparator.reverseOrder()).limit(topOfMaxes).reduce(0, Integer::sum);
    }
}
