package exercise1.oddevenprime;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Stream;

public class RangeTest {

    @Test
    public void testToPrintNumberOddOrEvenOrPrime(){
        //expected results
        String[] exceptedTestResult1 = {"Odd", "2", "3", "Even", "5", "Even", "7", "Even", "Odd", "Even"};
        String[] expectedTestResult2 = {"Odd", "Even", "2147483629", "Even", "Odd", "Even", "Odd", "Even", "Odd", "Even"};
        //actual results
        Stream<String> testResult1 = Range.of(1, 11);
        Stream<String> testResult2 = Range.of(2147483627, 2147483637);

        Assert.assertArrayEquals(exceptedTestResult1, testResult1.toArray());
        Assert.assertArrayEquals(expectedTestResult2, testResult2.toArray());
    }
}
