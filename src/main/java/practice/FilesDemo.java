package practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FilesDemo {

    public static void main(String[] args) throws IOException {
        //create directory
        Files.createDirectory(Paths.get("data"));
        Files.deleteIfExists(Paths.get("data"));
        // create directory with intermediate directory
        Files.createDirectories(Paths.get("sub1","sub2", "sub3", "myFile.txt"));

        //delete them all
        boolean deleted = Files.deleteIfExists(Paths.get("sub1/sub2/sub3", "myFile.txt"));
        deleted = Files.deleteIfExists(Paths.get("sub1", "sub2", "sub3"));
        deleted = Files.deleteIfExists(Paths.get("sub1", "sub2"));
        deleted = Files.deleteIfExists(Paths.get("sub1"));

        //Access a file, read it into a collection and print
        Path sourceDir = Paths.get("src", "main", "resources");
        Path dataFile = sourceDir.resolve("data.txt");
        System.out.println(Files.lines(dataFile).toList());

        //Copy file to new location
        Path destination = sourceDir.resolve("data_copy.txt");
        Files.copy(dataFile, destination);

        //move file
        Path other = sourceDir.resolve("data_move.txt");
        Files.move(destination, other);

        Files.deleteIfExists(other);

        //view all the directories
        try(Stream<Path> entries = Files.walk(Paths.get("."))){
            entries.forEach(System.out::println);
        }

    }
}
