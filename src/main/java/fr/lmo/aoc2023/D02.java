package fr.lmo.aoc2023;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class D02 extends AoCHelper {

    public void run() {
        // 1st part
        var bag = new CubeSet(Set.of(
                new CubeCount(new Cube("red"), 12),
                new CubeCount(new Cube("green"), 13),
                new CubeCount(new Cube("blue"), 14)));
        System.out.println("Test : " + sumPossibleGamesIndex(stream(getTestInputPath()), bag));
        System.out.println("Real file : " + sumPossibleGamesIndex(stream(getInputPath()), bag));

        // 2d part
        // Revient à trouver le bag minimum pour chaque game
        System.out.println("Test : " + addMinimalPowers(stream(getTestInputPath())));
        System.out.println("Real file : " + addMinimalPowers(stream(getInputPath())));
    }


    // 2d part
    int addMinimalPowers(Stream<String> lines) {
        return lines.map(Game::new)
                .mapToInt(gameToMinimalPower())
                .sum();
    }

    private ToIntFunction<Game> gameToMinimalPower() {
        return g -> g.sets().stream()
                .mapToInt(s -> 1)// trouver les max de chaque couleur
                .reduce(0, (a,b) -> a * b); // les multiply
    }

    // 1st part
    int sumPossibleGamesIndex(Stream<String> games, CubeSet bag) {
        return games.map(Game::new)
                .filter(gameIsPossibleWithBag(bag))
                .mapToInt(Game::index).sum();
    }

    private Predicate<Game> gameIsPossibleWithBag(CubeSet bag) {
        // Game est possible si
        return g -> g.sets.stream().allMatch(cubeSet -> cubeSet.cubeCounts().stream().allMatch( // tous les sets de la game sont valides
                gameCubeCount -> gameCubeCount.count <= bag.cubeCounts().stream().filter(bagCubeCount -> bagCubeCount.c().equals(gameCubeCount.c())).map(CubeCount::count).findAny().get() // toutes les tirages de cubes sont valides
        ));
    }


    record Game(int index, Set<CubeSet> sets) {
        // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        public Game(String s) {
            this(Integer.parseInt(s.substring(5, s.indexOf(":"))),
                    Arrays.stream(s.substring(s.indexOf(":") + 1).trim().split(";"))
                            .map(CubeSet::new)
                            .collect(Collectors.toSet()));
        }
    }

    record CubeSet(Set<CubeCount> cubeCounts) {
        // Hands separated by commas "3 blue, 4 red"
        public CubeSet(String sets) {
            this(Arrays.stream(sets.trim().split(","))
                    .map(String::trim)
                    .map(CubeCount::new)
                    .collect(Collectors.toSet()));
        }
    }

    record CubeCount(Cube c, int count) {
        // "4 red"
        public CubeCount(String hand) {
            this(new Cube(hand.split(" ")[1]),
                    Integer.parseInt(hand.split(" ")[0]));
        }
    }

    record Cube(String color) {
    }
}
