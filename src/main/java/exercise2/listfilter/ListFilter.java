package exercise2.listfilter;

import java.util.List;
import java.util.stream.Collectors;

public class ListFilter<T> {
    /*
     *1.Specification is a functional interface having isTrue() method,
     *      isTrue() method accepts one parameter of type integer and returns boolean value.
     * 2.This function ensure that user can define custom filter.
     * */
    public List<T> custom(List<T> list, Specification<T> specification) {
        return list.stream().filter(specification::isTrue).collect(Collectors.toList());
    }
}
