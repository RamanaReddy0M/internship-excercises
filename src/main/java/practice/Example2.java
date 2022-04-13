package practice;

import exercise2.listfilter.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Example2 {
    public static void main(String[] args) {

        /*//pythagoreanTriples
                IntStream.rangeClosed(1, 100)
                        .boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> Arrays.asList(a, b,(int) Math.sqrt(a * a + b * b)))
                        ).forEach(System.out::println);*/

        /*Stream.iterate(0, n -> n + 2)
                        .limit(1000)
                .forEach(System.out::print);*/
        //fibonacci series
        /*Stream.iterate(new int[]{0, 1}, new UnaryOperator<int[]>() {
            @Override
            public int[] apply(int[] t) {
                return new int[]{t[1], t[0]+t[1]};
            }
        }).limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);*/
        IntSupplier intSupplier = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        IntStream.generate(intSupplier).limit(10).forEach(System.out::println);

    }
}
