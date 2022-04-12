package exercise2.listfilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

//predicate chaining types
enum PredicateChainType {
    AND, OR
}

public abstract class ListFilter<T> {
    protected Map<String, Predicate<T>> predicateMap = new HashMap<>();
    protected Map<String, BiPredicate<T, T>> biPredicateMap = new HashMap<>();

    abstract List<T> withAllCommands(List<T> list, List<String> commands);

    abstract List<T> withAnyCommand(List<T> list, List<String> commands);

    abstract void registerPredicate(String command, Predicate<T> predicate);

    abstract void registerBiPredicate(String command, BiPredicate<T, T> biPredicate);

    abstract Predicate<T> constructPredicate(List<String> commands, PredicateChainType chainType);

    abstract boolean isPredicate(String command);

    public Map<String, Predicate<T>> getPredicateMap(){
        return predicateMap;
    }

    public Map<String, BiPredicate<T, T>> getBiPredicateMap(){
        return biPredicateMap;
    }
}

