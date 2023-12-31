package fr.lmo.aoc2023;

import java.nio.file.Path;
import java.util.List;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public class D07 extends AoCHelper {

    public void run() {
        System.out.println("Test : " + findWinningWays(parseHands(getTestInputPath())));
        System.out.println("Real file : " + findWinningWays(parseHands(getInputPath())));
    }

    private long findWinningWays(List<Hand> hands) {
        return 1;
    }

    record Hand() {}


    public static void main(String[] args) {
        AoCHelper.main(args);
    }

    /**
     * CopyPasted from @elacazed, parsing input strings is not my cup of tea (and isn't the purpose of AoC...  I guess :p)
     */
    List<Hand> parseHands(Path path) {
        List<String> lines = list(path);
        String[] times = lines.get(0).substring("Time:".length()).trim().split("\s+");
        String[] distances = lines.get(1).substring("Distance:".length()).trim().split("\s+");
        return IntStream.range(0, times.length).mapToObj(i -> new Hand()).toList();
    }
}
