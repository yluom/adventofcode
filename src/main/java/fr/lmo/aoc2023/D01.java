package fr.lmo.aoc2023;

import java.util.List;
import java.util.Map;

public class D01 extends AoCHelper {

    Map<String, String> map = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    public void run() {
        System.out.println("Test : " + decypherNumbersAndAdd(list(getTestInputPath())));
        //System.out.println("Real : " + decypherNumbersAndAdd(list(getInputPath())));
    }

    private Integer decypherNumbersAndAdd(List<String> l) {
        return l.stream() // pour chaque ligne
                .peek(lin -> System.out.println("line=" + lin))
                .map(this::replaceTextWithDigits) // remplace le texte par les chiffres
                .peek(lin -> System.out.println("transform=" + lin))
                .map(line -> line.replaceAll("[a-zA-Z]", "")) // enlève les lettres restantes
                .peek(lin -> System.out.println("nochars=" + lin))
                .mapToInt(numbers -> Integer.parseInt(numbers.charAt(0) + numbers.substring(numbers.length() - 1))) // récup le premier chiffre et le dernier et je les ajoute
                .peek(System.out::println)
                .sum();
    }

    /**
     * TODO this doesn't work for words like "eightwo" that should be "8wo" but actually renders "eigh2"
     *
     * @param s
     * @return
     */
    private String replaceTextWithDigits(String s) {
        for (var stringIntegerEntry : map.entrySet()) {
            s = s.replaceAll(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
        }
        return s;
    }
}
