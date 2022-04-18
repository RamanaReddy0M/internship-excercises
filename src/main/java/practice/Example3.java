package practice;

import java.io.File;
import java.util.Arrays;

public class Example3 {
    public static void main(String[] args) {
        System.out.println(System.getProperty("file.separator"));
        System.out.println(System.getProperty("path.separator"));
        //listing roots
        File[] roots = File.listRoots();
        if(roots.length > 0)
             Arrays.stream(roots).forEach(System.out::println);
    }
}
