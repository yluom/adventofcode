package fr.lmo.aoc2023;

import java.nio.file.Path;
import java.util.List;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public class D06 extends AoCHelper {

    public void run() {
        System.out.println("Test : " + findWinningWays(parseRaces(getTestInputPath())));
        System.out.println("Real file : " + findWinningWays(List.of(parseLongRace(parseRaces(getInputPath())))));
    }

    private long findWinningWays(List<Race> races) {
        return races.stream()
                .mapToLong(Race::countWays)
                .reduce(Math::multiplyExact)
                .orElse(-1);
    }

    // time 7 distance 9
    record Race(long time, long distance) {
        long countWays() {
            LongPredicate distanceTraveledIsBetter = holdTime -> holdTime * (time - holdTime) > distance;
            return LongStream.range(0, time)
                    .filter(distanceTraveledIsBetter)
                    .count();
        }
    }

    public static void main(String[] args) {
        AoCHelper.main(args);
    }

    /**
     * CopyPasted from @elacazed, parsing input strings is not my cup of tea (and isn't the purpose of AoC...  I guess :p)
     */
    List<Race> parseRaces(Path path) {
        List<String> lines = list(path);
        String[] times = lines.get(0).substring("Time:".length()).trim().split("\s+");
        String[] distances = lines.get(1).substring("Distance:".length()).trim().split("\s+");
        return IntStream.range(0, times.length).mapToObj(i -> new Race(Long.parseLong(times[i]), Long.parseLong(distances[i]))).toList();
    }

    Race parseLongRace(List<Race> races) {
        long time = Long.parseLong(races.stream().map(race -> Long.toString(race.time)).collect(Collectors.joining()));
        long best = Long.parseLong(races.stream().map(race -> Long.toString(race.distance)).collect(Collectors.joining()));
        return new Race(time, best);
    }
}
