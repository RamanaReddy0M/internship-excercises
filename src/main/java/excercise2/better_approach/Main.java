package excercise2.better_approach;

import java.util.Arrays;
import java.util.List;

class Even implements Command<Integer> {
    @Override
    public boolean test(Integer integer) {
        return integer % 2 == 0;
    }
}

class LessThanX implements Command<Integer> {
    public int number;

    public LessThanX(int x) {
        number = x;
    }

    @Override
    public boolean test(Integer integer) {
        return integer < number;
    }
}

class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 11, 22, 32, 2323, 101, 93, 97);
        ListFilter<Integer> listFilter = new ListFilter<>();
        List<Command<Integer>> commands = Arrays.asList(new Even(), new LessThanX(101));

        System.out.println(
                listFilter.filter(list, commands, FilterType.NONE_MATCH)
        );
        System.out.println(
                listFilter.filter(list, commands, FilterType.ANY_MATCH)
        );
        System.out.println(
                listFilter.filter(list, commands, FilterType.ALL_MATCH)
        );
    }
}
