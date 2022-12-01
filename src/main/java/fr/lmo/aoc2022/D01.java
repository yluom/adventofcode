package fr.lmo.aoc2022;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class D01 extends AoCHelper {

    public void run() {
        System.out.println("Test calories : " + findMostCalories(list(getTestInputPath()), 1));
        System.out.println("Real file calories : " + findMostCalories(list(getInputPath()), 1));

        System.out.println("Test sum(top3) calories : " + findMostCalories(list(getTestInputPath()), 3));
        System.out.println("Real file sum(top3) calories : " + findMostCalories(list(getInputPath()), 3));
    }

    private Integer findMostCalories(List<String> l, int topOfMaxes) {

        int current = 0;
        List<Integer> maxes = new ArrayList<>();
        // Pas trouvé de jolie solution afaik pour découper par rêne chaque liste de calories
        // Parsing de chacun des max des rênes
        for (String s : l) {
            if (s.isEmpty()) {
                maxes.add(current);
                current = 0;
            } else {
                current += Integer.parseInt(s);
            }
        }
        // Trie les max par ordre décroissant, limite au topX et retourne la somme
        return maxes.stream().sorted(Comparator.reverseOrder()).limit(topOfMaxes).reduce(0, Integer::sum);
    }
}
