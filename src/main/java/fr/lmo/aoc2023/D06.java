package fr.lmo.aoc2023;

import java.util.stream.Stream;


public class D06 extends AoCHelper {

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
