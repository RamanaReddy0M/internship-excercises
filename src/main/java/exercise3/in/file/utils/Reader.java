package exercise3.in.file.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class Reader {

    public static List<String> readFromFile(String fileName) {
        File source = new File(fileName);
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(source.toPath());
        } catch (IOException e) {
            System.out.println(Color.ANSI_RED + exceptionParser(e.toString()));
        }
        return lines;
    }

    public static List<String> readFromSTDIN() {
        List<String> input = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) input.add(scanner.nextLine());
        } catch (NoSuchElementException e) {
            return input;
        }
    }
    /*
     * example: java.nio.file.NoSuchFileException:
     * This function will remove java.nio.file and returns NoSuchFileException:...
     * */
    private static String exceptionParser(String ex) {
        return Arrays.stream(ex.split("\\.")).skip(3).reduce("", (a, b) -> a + " " + b);
    }
}
