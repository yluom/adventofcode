package fr.lmo.aoc2023;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class D04 extends AoCHelper {

    public void run() {
        // 1st part
        System.out.println("Test : " + part1(stream(getTestInputPath())));
        System.out.println("Real file : " + part1(stream(getInputPath())));

        // 2d part
        System.out.println("Test : " + part1(stream(getTestInputPath())));
        System.out.println("Real file : " + part1(stream(getInputPath())));
    }

    private String part1(Stream<String> stream) {
        return "";
    }


}
