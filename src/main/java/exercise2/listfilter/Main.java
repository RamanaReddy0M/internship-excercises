package exercise2.listfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //plugin less than command
        IntegerListFilter listFilter = new IntegerListFilter();
        listFilter.registerBiPredicate("less than", (integer, less) -> integer < less);
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
