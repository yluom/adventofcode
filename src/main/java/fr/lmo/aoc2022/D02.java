package fr.lmo.aoc2022;

import java.util.Arrays;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static fr.lmo.aoc2022.D02.Outcome.*;

public class D02 extends AoCHelper {
    Map<Shape, Integer> shapeScore = Map.of(Shape.ROCK, 1, Shape.PAPER, 2, Shape.SCISSORS, 3);
    Map<Outcome, Integer> outcomeScore = Map.of(WIN, 6,
            DRAW, 3,
            LOSE, 0);

    Map<Shape, Shape> winShape = Map.of(
            Shape.ROCK, Shape.SCISSORS,
            Shape.PAPER, Shape.ROCK,
            Shape.SCISSORS, Shape.PAPER);

    public void run() {
        System.out.println("Test : " + findTotalScore(stream(getTestInputPath()), 1));
        System.out.println("Real file : " + findTotalScore(stream(getInputPath()), 1));

        System.out.println("Part 2: ");
        System.out.println("Test : " + findTotalScore(stream(getTestInputPath()), 3));
        System.out.println("Real : " + findTotalScore(stream(getInputPath()), 3));
    }

    private Integer findTotalScore(Stream<String> s, int topOfMaxes) {
        ToIntFunction<String> roundToScore = round -> {
            String[] shapes = round.split(" ");
            Shape opponent = Shape.of(shapes[0]);
            Shape mine = Shape.of(shapes[1]);

            Integer score = shapeScore.get(mine);
            if (opponent == mine) {
                score += outcomeScore.get(DRAW);
            } else if (winShape.get(mine) == opponent) {
                score += outcomeScore.get(WIN);
            } else {
                score += outcomeScore.get(LOSE);
            }
            return score;
        };
        return s.mapToInt(roundToScore).sum();
    }

    public enum Outcome {
        WIN, DRAW, LOSE
    }

    // Do we need this ?
    public enum Shape {
        ROCK("AX"), PAPER("BY"), SCISSORS("CZ");
        private final String str;

        Shape(String str) {
            this.str = str;
        }

        public static Shape of(String s) {
            return Arrays.stream(Shape.values()).filter(shape -> shape.str.contains(s)).findFirst().orElseThrow();
        }
    }
}
