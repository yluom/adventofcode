package fr.lmo.aoc2021;

import fr.lmo.utils.AoCHelper;

import java.util.*;
import java.util.stream.IntStream;

public class D04 extends AoCHelper {
    @Override
    public void run() {
        Game test = new Game(list(getTestInputPath()));
        /*Board testWinner = test.getFirstWinner();
        System.out.println("Test Score part 1 : "+testWinner.score());
        Board lastTestWinner = test.getLastWinner();
        System.out.println("Test Score part 2 : "+lastTestWinner.score());

        Game real = new Game(list(getInputPath()));
        Board realWinner = real.getFirstWinner();
        System.out.println("Real Score part 1 : "+realWinner.score());
        Board lastWinner = real.getLastWinner();
        System.out.println("Real Score part 2 : "+lastWinner.score());*/
    }

    /**
     *
     */
    public class Game {

        private final String numbers;
        private final List<Grid> grids;

        public Game(List<String> list) {
            numbers = list.get(0);
            grids = parseGrids(list.subList(2, list.size())); // enleve les 2 premiers lignes
            // Récup les input
        }

        private List<Grid> parseGrids(List<String> list) {
            List<List<String>> grids = IntStream.range(0, list.size() - 1)
                    .mapToObj(i -> list.subList(i, i + 5)).toList();
            return grids.stream().map(Grid::new).toList();
        }


        public Grid getFirstWinner() {
            // tirer les nombres l'un après l'autre
            // marquer les nombres dans les grid
           return null;
        }
    }

    public class Grid {

        private final NumberAndMark[][] grid = new NumberAndMark[5][5];

        public Grid(List<String> lines) {
          //  lines.stream().forEach(line -> line.split(" "));
            int rowI=0;
            for (NumberAndMark[] row : grid) {
                String[] fiveNumbers = lines.get(rowI).split(" ");
                rowI++;
                for (NumberAndMark column : row) {

                }
            }
        }

    }

    public record NumberAndMark() {
        static Integer number;
        static Boolean marked;
    }

}
