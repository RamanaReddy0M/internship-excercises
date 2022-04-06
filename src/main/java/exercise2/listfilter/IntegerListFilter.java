package exercise2.listfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IntegerListFilter extends ListFilter<Integer> {

    public static void main(String[] args) {
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
        new IntegerListFilter().withMultipleCommands(list, Arrays.asList(commands))
                .forEach(System.out::println);
    }

    public static boolean isPrime(long num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        for (int i = 2; i <= num / 2; i++)
            if (num % i == 0)
                return false;
        return true;
    }

    public static boolean isEven(long num) {
        return num % 2 == 0;
    }

    //method for filtering with multiple conditions
    public List<Integer> withMultipleCommands(List<Integer> list, List<String> commands) {
        List<Integer> result = new ArrayList<>(list);
        int counter = 0;
        while (counter < commands.size()) {
            String[] condition = commands.get(counter).trim().split(" ");
            String option = "";
            int number = 0;
            //logic for extracting command and number from; greater than <number> and multiple of <number>
            if (condition.length == 3) {
                option = condition[0] + " " + condition[1];
                number = Integer.parseInt(condition[2]);
            } else
                option = commands.get(counter).trim();

            switch (option) {
                case "even":
                    result = even(result);
                    break;
                case "odd":
                    result = odd(result);
                    break;
                case "prime":
                    result = prime(result);
                    break;
                case "odd prime":
                    result = oddPrime(result);
                    break;
                case "greater than":
                    int finalNumber = number;
                    result = custom(result, i -> i > finalNumber);
                    break;
                case "multiple of":
                    int finalNumber1 = number;
                    result = custom(result, i -> i % finalNumber1 == 0);
            }
            counter++;
        }
        return result;
    }

    public List<Integer> even(List<Integer> list) {
        return custom(list, IntegerListFilter::isEven);
    }

    public List<Integer> odd(List<Integer> list) {
        return custom(list, i -> !isEven(i));
    }

    public List<Integer> prime(List<Integer> list) {
        return custom(list, IntegerListFilter::isPrime);
    }

    public List<Integer> oddPrime(List<Integer> list) {
        return prime(odd(list));
    }
}
