package exercise2.listfilter;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IntegerListFilter extends ListFilter<Integer> {

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

    //following two methods are responsible for providing generic name for registering command
    // and predicate(or BiPredicate).
    public void registerCommandAndPredicate(String command, Predicate<Integer> predicate) {
        registerPredicate(command, predicate);
    }

    //overloading
    public void registerCommandAndPredicate(String command, BiPredicate<Integer, Integer> predicate) {
        registerBiPredicate(command, predicate);
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
