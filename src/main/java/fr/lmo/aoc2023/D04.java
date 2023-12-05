package fr.lmo.aoc2023;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;


public class D04 extends AoCHelper {

    public void run() {

        // 1st part
        System.out.println("Test : " + findWinningPoints(stream(getTestInputPath())));
        System.out.println("Real file : " + findWinningPoints(stream(getInputPath())));

        // 2d part TODO
        // System.out.println("Test : " + findWinningPoints(stream(getTestInputPath())));
        //    System.out.println("Real file : " + findWinningPoints(stream(getInputPath())));
    }

    private int findWinningPoints(Stream<String> stream) {
        return  stream.map(Card::new)
                .mapToInt(Card::computePoints)
                .sum();
    }

    record Card(int id, Set<Integer> winning, List<Integer> draw) {
        // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        public Card(String cardLine) {
            this(
                    Integer.parseInt(cardLine.substring(5, cardLine.indexOf(":")).trim()),
                    Arrays.stream(cardLine.substring(cardLine.indexOf(":") + 1).split("\\|")[0].trim().split(" "))
                            .map(String::trim)
                            .filter(not(String::isBlank))
                            .mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet()),
                    Arrays.stream(cardLine.split("\\|")[1].trim().split(" "))
                            .map(String::trim)
                            .filter(not(String::isBlank))
                            .mapToInt(Integer::parseInt).boxed()
                            .toList()
            );
        }

        public int computePoints() {
            long count = draw.stream()
                    .filter(winning::contains) // je garde juste les gagnants
                    .count();
            return (int) Math.pow(2, count - 1);
        }
    }
}
