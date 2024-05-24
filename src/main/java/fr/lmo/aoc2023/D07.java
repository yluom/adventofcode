package fr.lmo.aoc2023;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


public class D07 extends AoCHelper {

    public void run() {
        System.out.println("Test : " + findWinnings(parseHands(getTestInputPath())));
        System.out.println("Real file : " + findWinnings(parseHands(getInputPath())));
    }

    private long findWinnings(List<Hand> hands) {
        // calculate type
        // sort by rank
        // sum(rank*bid)
        return
        hands.stream()
                .filter(x-> true)

                .peek(System.err::println).count();
        //return 1;
    }

    enum Type {
        FIVE,
        FOUR,
        FULL,
        THREE,
        TWOPAIR,
        PAIR,
        HIGH
    }
    record Hand(String cards, int bid, Type type) {
        Hand(String cards, int bid) {
            this(cards, bid, calcType(cards));
        }

//        AAAAA -> FIVE
        private static Type calcType(String cards) {
            Map<String, Long> result = Arrays.stream(cards.split(""))
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(s -> s,
                            LinkedHashMap::new,
                            Collectors.counting()));
            //result.s
            return Type.FIVE;
        }
    }


    public static void main(String[] args) {
        AoCHelper.main(args);
    }

    List<Hand> parseHands(Path path) {
        return stream(path).map(line ->
                        new Hand(
                                line.split(" ")[0],
                                Integer.parseInt(line.split(" ")[1]))
                ).toList();
    }
}
