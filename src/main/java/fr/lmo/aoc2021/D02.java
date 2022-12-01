package fr.lmo.aoc2021;

import fr.lmo.aoc2022.AoCHelper;

import java.util.stream.Stream;

public class D02 extends AoCHelper {

    public void run() {
        System.out.println("First star test : " + firstStar(stream(getTestInputPath())));
        System.out.println("First star input : " + firstStar((stream(getInputPath()))));

        System.out.println("Second star test : " + secondStar(stream(getTestInputPath())));
        System.out.println("Second star input : " + secondStar((stream(getInputPath()))));

    }

    public record Move(String direction, int number) {
    }

    private static class Position {
        public int aim;
        public int x;
        public int y;

        Position(int x, int y, int aim) {
            x = x;
            y = y;
            aim = aim;
        }

    }

    private int firstStar(Stream<String> stream) {
        Stream<Move> moves = stream.map(line -> new Move(line.split(" ")[0], Integer.parseInt(line.split(" ")[1])));

        final Position p = new Position(0, 0, 0);
        moves.forEach(m ->
        {
            if (m.direction.equals("forward")) {
                p.x += m.number;
            } else if (m.direction.equals("up")) {
                p.y -= m.number;
            } else if (m.direction.equals("down")) {
                p.y += m.number;
            }
        });

        return p.x * p.y;
    }

    private int secondStar(Stream<String> stream) {
        Stream<Move> moves = stream.map(line -> new Move(line.split(" ")[0], Integer.parseInt(line.split(" ")[1])));

        final Position p = new Position(0, 0, 0);
        moves.forEach(m ->
        {
            if (m.direction.equals("forward")) {
                p.x += m.number;
                p.y += p.aim*m.number;
            } else if (m.direction.equals("up")) {
                p.aim -= m.number;
            } else if (m.direction.equals("down")) {
                p.aim += m.number;
            }
        });

        return p.x * p.y;
    }
}
