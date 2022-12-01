package fr.lmo.aoc2021;

import fr.lmo.aoc2022.AoCHelper;

import java.util.IntSummaryStatistics;
import java.util.List;

public class D03 extends AoCHelper {

    public void run() {
        List<String> testInput = list(getTestInputPath());
        List<String> input = list(getInputPath());
        System.out.println("Part One Test Result : " + solvePartOne(testInput));
        System.out.println("Part One Real Result : " + solvePartOne(input));

        /*System.out.println("Part Two Test Result : " + solvePartTwo(testInput));
        System.out.println("Part Two Real Result : " + solvePartTwo(input));*/

    }


    private String solvePartOne(List<String> testInput) {


        int length = testInput.stream().findFirst().get().length();
        System.out.println("Length is "+length);
        String mostCommonBits = "";


        for (int i = 0; i < length; i++) {
            mostCommonBits += mostCommonBit(testInput, i, i+1);
            System.out.println(mostCommonBits);
        }

        int gamma = Integer.parseInt(mostCommonBits, 2);
        System.out.println("Gamma="+gamma);

        // bit wise "NOT"
        int epsilon = Integer.parseInt(mostCommonBits.replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1"), 2);
        System.out.println("Eps="+epsilon);
        return gamma * epsilon + "";
    }

    private String mostCommonBit(List<String> testInput, int begin, int end) {
        IntSummaryStatistics column = testInput.stream().mapToInt(line -> Integer.parseInt(line.substring(begin, end))).summaryStatistics();
        if (column.getAverage() > 0.5) {
            return "1";
        } else {
            return "0";
        }
    }

    private String solvePartTwo(List<String> testInput) {
        return "";
    }
}
