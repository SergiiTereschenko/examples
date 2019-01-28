package com.st.codility;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FibonachiiExapmle {
    @Test
    public void fib() {

        int[] fibs = { 0, 1 };
        Stream<Integer> fibonacci = Stream.generate(() -> {
            int result = fibs[1];
            int fib3 = fibs[0] + fibs[1];
            fibs[0] = fibs[1];
            fibs[1] = fib3;
            return result;
        });


        List<Integer> fibonacci5 = fibonacci.limit(5)
                .collect(Collectors.toList());

        System.out.println(fibonacci5);
    }

}
