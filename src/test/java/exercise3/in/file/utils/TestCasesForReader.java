package exercise3.in.file.utils;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestCasesForReader {

    @Test
    public void testForReadFromFile() {
        Path tempSource = Paths.get("tempDataZ.txt");
        //Content of file where key will be searched.
        String fileContent = "<xmlVersionxml@#xml^^^XMl> </xmlVersion&Xml>\nolder VMs the conversion";
        if (!FileWriter.write(fileContent, tempSource.toString())) fail();

        //test 1
        List<String> expectedLines = Arrays.stream(fileContent.split("\n")).toList();
        List<String> actualLines = Reader.readFromFile(tempSource.toString());
        assertArrayEquals(expectedLines.toArray(), actualLines.toArray());

        //test 2
        //Here, it prints(not throws) exception if it triggers any IOException.
        actualLines = Reader.readFromFile("/data.txt");
        assertTrue(actualLines.isEmpty());

        //deleting created temporary source
        try {
            Files.deleteIfExists(tempSource);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

}
