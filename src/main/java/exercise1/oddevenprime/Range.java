package exercise1.oddevenprime;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Range {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int start, end;
        System.out.print("Start: ");
        start = scanner.nextInt();
        System.out.print("End: ");
        end = scanner.nextInt();
        System.out.println("-------Result---------");
        Range.of(start, end).forEach(System.out::println);
    }

    public static Stream<String> of(int start, int end) {
        List<String> result = new ArrayList<>();
        LongStream.range(start, end).forEach(i -> {
            if (isPrime(i))
                result.add("" + i);
            else if (isEven(i))
                result.add("Even");
            else
                result.add("Odd");
        });
        return new ArrayList<>(result).stream();
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
}
