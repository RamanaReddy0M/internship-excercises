package excercise2.better_approach;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

enum FilterType {
    ALL_MATCH,
    ANY_MATCH,
    NONE_MATCH
}

interface Command<T> extends Predicate<T> {
}

public class ListFilter<T> {

    public List<T> filter(List<T> list, List<Command<T>> commands, FilterType filterType) {
        if(commands.size() < 1) return list;
        return list.stream().filter(compose(commands, filterType)).collect(Collectors.toList());
    }

    private Predicate<T> compose(List<Command<T>> commands, FilterType filterType) {
        Predicate<T> command = commands.get(0);
        for (Command<T> c : commands.stream().skip(1L).toList())
            if (filterType == FilterType.ALL_MATCH)
                command = command.and(c);
            else if (filterType == FilterType.ANY_MATCH)
                command = command.or(c);
            else if (filterType == FilterType.NONE_MATCH)
                command = command.negate().and(c.negate());
        return command;
    }
}
