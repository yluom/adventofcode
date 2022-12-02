package fr.lmo.aoc2022;

import java.util.Arrays;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import static fr.lmo.aoc2022.D02.Outcome.*;

public class D02 extends AoCHelper {
    // Maps shape -> shape score
    Map<Shape, Integer> shapeScore = Map.of(Shape.ROCK, 1, Shape.PAPER, 2, Shape.SCISSORS, 3);

    // Map outcome -> outcome score
    Map<Outcome, Integer> outcomeScore = Map.of(
            WIN, 6,
            DRAW, 3,
            LOSE, 0);

    // Indicate that a key wins over a value
    Map<Shape, Shape> winShape = Map.of(
            Shape.ROCK, Shape.SCISSORS,
            Shape.PAPER, Shape.ROCK,
            Shape.SCISSORS, Shape.PAPER);

    public void run() {
        System.out.println("Test : " + findTotalScore1(stream(getTestInputPath())));
        System.out.println("Real file : " + findTotalScore1(stream(getInputPath())));

        System.out.println("Part 2: ");
        System.out.println("Test : " + findTotalScore2(stream(getTestInputPath())));
        System.out.println("Real : " + findTotalScore2(stream(getInputPath())));
    }

    private Integer findTotalScore1(Stream<String> s) {
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

    private Integer findTotalScore2(Stream<String> s) {
        // I want to re-use the 1st star function so
        // I'm going to translate the inputs with 2d star strat guide and pass it to 1st star func.
        // Not optimal but actually easier to write without rewriting everything?
        Stream<String> stream = s.map(r -> {
            String[] shapes = r.split(" ");
            Shape opponent = Shape.of(shapes[0]);
            Outcome strategy = Outcome.of(shapes[1]);
            String myShape = null;
            if (strategy == LOSE) {
                myShape = winShape.get(opponent).toString();
            } else if (strategy == DRAW) {
                myShape = shapes[0]; // same as opponent
            } else {
                // Over-engineered map value->key inversion
                myShape = winShape
                        .entrySet()
                        .stream()
                        .filter(entry -> opponent.equals(entry.getValue()))
                        .map(Map.Entry::getKey).findFirst().orElseThrow().toString();
            }
            return shapes[0] + " " + myShape;
        });
        return findTotalScore1(stream);
    }

    public enum Outcome {
        WIN("Z"), DRAW("Y"), LOSE("X");

        private final String str;

        Outcome(String str) {
            this.str = str;
        }

        public static Outcome of(String s) {
            return Arrays.stream(Outcome.values()).filter(o -> o.str.contains(s)).findFirst().orElseThrow();
        }
    }

    public enum Shape {
        ROCK("AX"), PAPER("BY"), SCISSORS("CZ");
        private final String str;

        Shape(String str) {
            this.str = str;
        }

        public static Shape of(String s) {
            return Arrays.stream(Shape.values()).filter(shape -> shape.str.contains(s)).findFirst().orElseThrow();
        }

        public String toString() {
            return str;
        }
    }

}
