package fr.lmo.aoc2022;

import fr.lmo.utils.AoCHelper;

import java.util.stream.Stream;

public class D01 extends AoCHelper {

    public void run() {
        System.out.println("Test file increases : " + findIncreases(stream(getTestInputPath(), Integer::valueOf), 1));
        System.out.println("Real file increases : " + findIncreases(stream(getInputPath(), Integer::valueOf), 1));

        System.out.println("Test file increases window : " + findIncreases(stream(getTestInputPath(), Integer::valueOf), 3));
        System.out.println("Real file increases window : " + findIncreases(stream(getInputPath(), Integer::valueOf), 3));
    }

    private String findIncreases(Stream<Integer> stream, int pas) {
        String increases = "7";
        final int[] incrCount = {0};
        final int[] previousValue = {Integer.MAX_VALUE};
       // stream.reduce(0, (increasesCount, element) -> )
        stream.forEach(i -> {
            if(previousValue[0] < i) {
                incrCount[0]++;
            }
            previousValue[0] = i;
        });
        return String.valueOf(incrCount[0]);
    }
}
