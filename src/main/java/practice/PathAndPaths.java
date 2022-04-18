package practice;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathAndPaths {

    public static void main(String[] args) {

        Path home = Paths.get("/");
        System.out.println(home);
        //using resolve to find nested paths
        Path docs = home.resolve("Documents");
        System.out.println(docs);
        //can resolve siblings as well
        Path downloads = docs.resolveSibling("Downloads");
        System.out.println(downloads);
        //project directory
        Path projectDir = Paths.get(".");
        System.out.println(projectDir);
        System.out.println("Absolute path: " + projectDir.toAbsolutePath());
        System.out.println("Uri: " + projectDir.toUri());
        System.out.println("Parent: " + projectDir.toAbsolutePath().getParent());
        System.out.println("Filename: " + projectDir.toAbsolutePath().getFileName());
        System.out.println("Root: " + projectDir.toAbsolutePath().getRoot());
        System.out.println("FileSystem: " + projectDir.getFileSystem());

        for (Path path: projectDir.toAbsolutePath())
            System.out.println(path);
    }
}
