package exercise2.listfilter;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegerListFilterTestCases {
    @Test
    public void testForIntegerListFilterWithAllCommands() {
        //input and expected results
        List<Integer> testList1 = new ArrayList<>(Arrays.asList(1, 2, 3, 32, 7, 97, 5, 93, 2));
        List<String> testConditions1 = new ArrayList<>(Arrays.asList("even", "prime"));
        List<Integer> exceptedResult1 = new ArrayList<>(Arrays.asList(2, 2));

        List<Integer> testList2 = new ArrayList<>(Arrays.asList(200, 2, 2022, 1997, 54, 989, 1, 10, 15, 20, 97));
        List<String> testConditions2 = new ArrayList<>(Arrays.asList("odd prime", "greater than 100"));
        List<Integer> exceptedResult2 = new ArrayList<>(List.of(1997));

        List<Integer> testList3 = new ArrayList<>(Arrays.asList(33, 43, 99, 1008, 19, 18, 3, 6, 90, 18, 60, 128, 306));
        List<String> testConditions3 = new ArrayList<>(Arrays.asList("multiple of 3", "multiple of 6"));
        List<Integer> exceptedResult3 = new ArrayList<>(Arrays.asList(1008, 18, 6, 90, 18, 60, 306));

        IntegerListFilter listFilter = new IntegerListFilter();

        //actual results
        Assert.assertArrayEquals(exceptedResult1.toArray(), listFilter.withAllCommands(testList1, testConditions1).toArray());
        Assert.assertArrayEquals(exceptedResult2.toArray(), listFilter.withAllCommands(testList2, testConditions2).toArray());
        Assert.assertArrayEquals(exceptedResult3.toArray(), listFilter.withAllCommands(testList3, testConditions3).toArray());
    }

    @Test
    public void testForIntegerListFilterWithAnyCommand() {
        //input and expected results
        List<Integer> testList1 = new ArrayList<>(Arrays.asList(1, 2, 3, 32, 7, 97, 5, 93, 2));
        List<String> testConditions1 = new ArrayList<>(Arrays.asList("even", "prime"));
        List<Integer> exceptedResult1 = new ArrayList<>(Arrays.asList(2, 3, 32, 7, 97, 5, 2));

        List<Integer> testList2 = new ArrayList<>(Arrays.asList(200, 2, 2022, 1997, 54, 989, 1, 10, 15, 20, 97));
        List<String> testConditions2 = new ArrayList<>(Arrays.asList("odd prime", "greater than 2000"));
        List<Integer> exceptedResult2 = new ArrayList<>(List.of(2022, 1997, 97));

        IntegerListFilter listFilter = new IntegerListFilter();
        //test case for plugin less than command
        listFilter.registerBiPredicate("less than", (number, less) -> number < less);

        List<Integer> testList3 = new ArrayList<>(Arrays.asList(1, 2, 3, 32, 7, 97, 5, 93, 2));
        List<String> testConditions3 = new ArrayList<>(Arrays.asList("prime", "less than 50"));
        List<Integer> exceptedResult3 = new ArrayList<>(Arrays.asList(1, 2, 3, 32, 7, 97, 5, 2));
        //actual results
        Assert.assertArrayEquals(exceptedResult1.toArray(), listFilter.withAnyCommand(testList1, testConditions1).toArray());
        Assert.assertArrayEquals(exceptedResult2.toArray(), listFilter.withAnyCommand(testList2, testConditions2).toArray());
        Assert.assertArrayEquals(exceptedResult3.toArray(), listFilter.withAnyCommand(testList3, testConditions3).toArray());
    }
}
