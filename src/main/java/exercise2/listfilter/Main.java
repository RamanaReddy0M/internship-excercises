package exercise2.listfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static IntegerListFilter listFilter = new IntegerListFilter();

    static {
        //even, odd, prime and odd requires single param to implement
        listFilter.registerCommandAndPredicate("even", integer -> integer % 2 == 0);
        listFilter.registerCommandAndPredicate("odd", listFilter.getPredicateMap().get("even").negate());
        listFilter.registerCommandAndPredicate("prime", num -> {
            if (num <= 1) return false;
            if (num == 2) return true;
            for (int i = 2; i <= num / 2; i++)
                if (num % i == 0) return false;
            return true;
        });
        listFilter.registerCommandAndPredicate("odd prime", listFilter.getPredicateMap().get("odd").and(listFilter.getPredicateMap().get("prime")));
        //multiple of, greater than and less than requires 2 params to implement
        listFilter.registerCommandAndPredicate("greater than", (num, greater) -> num > greater);
        listFilter.registerCommandAndPredicate("multiple of", (num, multiple) -> num % multiple == 0);
    }

    public static void main(String[] args) {
        //plugin less than command
        listFilter.registerCommandAndPredicate("less than", (integer, less) -> integer < less);
        //-------------------------------
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input: ");
        System.out.print("Specify numbers separated by ',', ex: 1, 2, 3,.." + "\nlist: ");
        List<Integer> list = new ArrayList<>();
        //constructing list from data
        String[] data = scanner.nextLine().split(",");
        for (String s : data) list.add(Integer.parseInt(s.trim()));
        //letting user know what commands to be used.
        List<String> keywords = Arrays.asList("even", "odd", "prime", "odd prime", "greater than <number>", "multiple of <number>");
        System.out.println("Command List: " + keywords);
        System.out.print("Specify commands, ex: even, greater than 5, multiple of 10,.." + "\nCommands: ");
        //commands extraction
        String[] commands = scanner.nextLine().split(",");
        //output
        System.out.println("Output: ");
        listFilter.withAnyCommand(list, Arrays.asList(commands)).forEach(System.out::println);
    }
}
