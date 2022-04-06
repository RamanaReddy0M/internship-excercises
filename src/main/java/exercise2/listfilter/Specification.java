package exercise2.listfilter;

@FunctionalInterface
public interface Specification<T>{
    boolean isTrue(T t);
}
