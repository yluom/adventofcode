package fr.lmo.aoc2023;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;


public class D05 extends AoCHelper {

    public void run() {

        // 1st part
        System.out.println("Test : " + findWinningPoints(stream(getTestInputPath())));
        System.out.println("Real file : " + findWinningPoints(stream(getInputPath())));

        // 2d part TODO
        // System.out.println("Test : " + findWinningPoints(stream(getTestInputPath())));
        //    System.out.println("Real file : " + findWinningPoints(stream(getInputPath())));
    }

    private int findWinningPoints(Stream<String> stream) {
        return  1;
    }

}
