package exercise3.in.file.utils;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestCasesForFileSearch {

    FileSearch fileSearch = new FileSearch();

    @Test
    public void testToSearchFileWithKey() {
        fileSearch.setOutputForFile(true);

        //Creating temporary file
        Path tempSource = Paths.get("tempDataZ.txt");
        //Content of file where key will be searched.
        String fileContent = "<xmlVersionxml@#xml^^^XMl> </xmlVersion&Xml>\nolder VMs the conversion";
        if (!FileWriter.write(fileContent, tempSource.toString())) fail();

        /*
         * Story 1 - Ability to search for a string in a file.
         * */

        //test 1 - case-sensitive
        String key = "xMl";
        String expectedResult = "";
        String actualResult = fileSearch.search(key, tempSource);
        assertEquals(expectedResult, actualResult);

        //test 2 - case-Insensitive
        fileSearch.setCaseSensitive(false);

        key = "xMl";
        expectedResult = "<xmlVersionxml@#xml^^^XMl> </xmlVersion&Xml>";
        actualResult = fileSearch.search(key, tempSource);
        assertEquals(expectedResult, actualResult);

        //test 3 - case-sensitive && after 1 line
        fileSearch.setCaseSensitive(true);
        fileSearch.setAfter(1);

        key = "Version";
        expectedResult = "<xmlVersionxml@#xml^^^XMl> </xmlVersion&Xml>\nolder VMs the conversion";
        actualResult = fileSearch.search(key, tempSource);
        assertEquals(expectedResult, actualResult);

        //deleting created temporary source
        try {
            Files.deleteIfExists(tempSource);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    @Test
    public void abilityToSearchFromSTDIN() {
        fileSearch.setOutputForFile(true);
        InputStream originalState = System.in;

        /*
         * Story 2 - Ability to search for a string from standard input.
         * */
        String key = "foo";
        System.setIn(new ByteArrayInputStream("bar\nbarbazfoo\nFoobar\nfood".getBytes()));
        List<String> input = Reader.readFromSTDIN();

        //restoring System.in back to its original state
        System.setIn(originalState);

        String expectedResults = "barbazfoo\nfood";
        String actualResults = fileSearch.search(key, input);

        assertEquals(expectedResults, actualResults);
    }

    @Test
    public void testCaseForSearchingFromDirectory() {

        /*
         * Story 4 - Ability to search for a string recursively in any of the files in a given directory.
         * */
        List<String> expectedLines = Reader.readFromFile("src/main/directorySearchResults.txt");

        List<String> actualLines = new ArrayList<>();
        Map<String, String> matchesInfile = fileSearch.searchFromDirectory("version", "src/main/resources");
        matchesInfile.forEach((path, matchesOfFile) -> {
            Collections.addAll(actualLines, (path + matchesOfFile).split("\n"));
        });

        assertEquals(expectedLines, actualLines);
    }

}
