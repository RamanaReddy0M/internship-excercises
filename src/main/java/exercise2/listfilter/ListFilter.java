package exercise2.listfilter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//predicate chaining types
enum PredicateChainType {
    AND,
    OR
}

public class ListFilter {
    public List<Integer> withAllCommands(List<Integer> list, List<String> commands) {
        //initializing predicate
        Predicate<Integer> predicate = constructPredicate(commands, PredicateChainType.AND);
        if (predicate == null) return list;
        return filter(list, predicate);
    }

    public List<Integer> withAnyCommand(List<Integer> list, List<String> commands) {
        //initializing predicate
        Predicate<Integer> predicate = constructPredicate(commands, PredicateChainType.OR);
        if (predicate == null) return list;
        return filter(list, predicate);
    }

    public List<Integer> filter(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public Predicate<Integer> even() {
        return integer -> integer % 2 == 0;
    }

    public Predicate<Integer> odd() {
        return integer -> integer % 2 == 1;
    }

    public Predicate<Integer> prime() {
        return num -> {
            if (num <= 1) return false;
            if (num == 2) return true;
            for (int i = 2; i <= num / 2; i++)
                if (num % i == 0) return false;
            return true;
        };
    }

    public Predicate<Integer> oddPrime() {
        return odd().and(prime());
    }

    public Predicate<Integer> multiple(int multiple) {
        return integer -> integer % multiple == 0;
    }

    public Predicate<Integer> greaterThan(int greater) {
        return integer -> integer > greater;
    }

    private Predicate<Integer> constructPredicate(List<String> commands, PredicateChainType chainType) {
        if (commands.size() < 1) return null;
        Predicate<Integer> predicate = getPredicate(commands.get(0));
        if (predicate == null) return null;
        //constructing predicate
        for (int i = 1; i < commands.size(); i++) {
            Predicate<Integer> currentPredicate = getPredicate(commands.get(i));
            if (currentPredicate != null)
                if (chainType == PredicateChainType.AND)
                    predicate = predicate.and(currentPredicate);
                else if (chainType == PredicateChainType.OR)
                    predicate = predicate.or(currentPredicate);

        }
        return predicate;
    }

    private Predicate<Integer> getPredicate(String type) {
        int number = 1;
        String option;
        String[] condition = type.trim().split(" ");
        //logic for extracting command and number from; greater than <number> and multiple of <number>
        if (condition.length == 3) {
            option = condition[0].trim() + " " + condition[1].trim();
            number = Integer.parseInt(condition[2].trim());
        } else option = type.trim();
        switch (option) {
            case "even":
                return even();
            case "odd":
                return odd();
            case "prime":
                return prime();
            case "odd prime":
                return oddPrime();
            case "multiple of":
                return multiple(number);
            case "greater than":
                return greaterThan(number);
        }
        return null;
    }
}