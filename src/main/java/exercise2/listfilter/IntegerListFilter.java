package exercise2.listfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IntegerListFilter extends ListFilter<Integer> {

    public IntegerListFilter() {
        //even, odd, prime and odd requires single param to implement
        registerPredicate("even", integer -> integer % 2 == 0);
        registerPredicate("odd", predicateMap.get("even").negate());
        registerPredicate("prime", num -> {
            if (num <= 1) return false;
            if (num == 2) return true;
            for (int i = 2; i <= num / 2; i++)
                if (num % i == 0) return false;
            return true;
        });
        registerPredicate("odd prime", predicateMap.get("odd").and(predicateMap.get("prime")));
        //multiple of, greater than and less than requires 2 params to implement
        registerBiPredicate("greater than", (num, greater) -> num > greater);
        registerBiPredicate("multiple of", (num, multiple) -> num % multiple == 0);
    }

    public static void main(String[] args) {
        List<Integer> testList1 = new ArrayList<>(Arrays.asList(1, 2, 3, 32, 7, 97, 5, 93, 2));
        IntegerListFilter listFilter = new IntegerListFilter();
        listFilter.withAllCommands(testList1, Arrays.asList("even", "greater than 2")).forEach(System.out::println);
    }

    @Override
    List<Integer> withAllCommands(List<Integer> list, List<String> commands) {
        return filter(list, constructPredicate(commands, PredicateChainType.AND));
    }

    @Override
    List<Integer> withAnyCommand(List<Integer> list, List<String> commands) {
        return filter(list, constructPredicate(commands, PredicateChainType.OR));
    }

    @Override
    void registerPredicate(String command, Predicate<Integer> predicate) {
        predicateMap.put(command.trim(), predicate);
    }

    @Override
    void registerBiPredicate(String command, BiPredicate<Integer, Integer> biPredicate) {
        String[] condition = command.trim().split(" ");
        biPredicateMap.put(condition[0].trim() + " " + condition[1].trim(), biPredicate);
    }

    @Override
    Predicate<Integer> constructPredicate(List<String> commands, PredicateChainType chainType) {
        Predicate<Integer> predicate = null;
        for (String command : commands) {
            //ensuring command existence
            if (predicateMap.get(command) == null && biPredicateMap.get(getBiPredicateCommand(command)) == null)
                continue;
            if (predicate == null) predicate = getPredicate(command);
            else if (chainType == PredicateChainType.AND) predicate = predicate.and(getPredicate(command));
            else if (chainType == PredicateChainType.OR) predicate = predicate.or(getPredicate(command));
        }
        return predicate;
    }


    @Override
    boolean isPredicate(String command) {
        return command.split(" ").length < 3;
    }

    public List<Integer> filter(List<Integer> list, Predicate<Integer> predicate) {
        if (predicate == null) return list;
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    private Predicate<Integer> getPredicate(String command) {
        if (isPredicate(command)) return predicateMap.get(command);
        return convertBiPredicateToPredicate(command);
    }

    private Predicate<Integer> convertBiPredicateToPredicate(String command) {
        BiPredicate<Integer, Integer> biPredicate = biPredicateMap.get(getBiPredicateCommand(command));
        if (biPredicate == null) return null;
        return integer -> biPredicate.test(integer, getBiPredicateNumber(command));
    }

    private Integer getBiPredicateNumber(String command) {
        return Integer.parseInt(command.trim().split(" ")[2]);
    }

    private String getBiPredicateCommand(String command) {
        String[] condition = command.trim().split(" ");
        return condition[0].trim() + " " + condition[1].trim();
    }
}
