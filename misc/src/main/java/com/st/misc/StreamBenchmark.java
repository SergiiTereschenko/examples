package com.st.misc;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@Fork(2)
@State(Scope.Thread)
@Warmup(iterations = 10, time = 1, batchSize = 100)
@Measurement(iterations = 10, time = 1, batchSize = 100)
public class StreamBenchmark {

//    private final List<String> userList = Arrays.asList("1","2","3","4","5","6","7","8","9","10");

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

//    @Fork(value = 1, warmups = 1)
    @Benchmark
//    @BenchmarkMode(Mode.Throughput)
    public void benchParallel() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 ,16 ,17 ,18 ,19, 20);
        int computedAges = numbers.parallelStream().reduce(0, accumulator(), combiner());
//        System.out.println(computedAges);

    }

    @Benchmark
//    @BenchmarkMode(Mode.Throughput)
    public void benchSerial() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 ,16 ,17 ,18 ,19, 20);
        int computedAges = numbers.stream().reduce(0, accumulator(), combiner());
//        System.out.println(computedAges);
    }

//    @Test
    public void testReduceParallelStream() {

        List<Integer> ages = Arrays.asList(1, 2, 3, 4, 5);
        int computedAges = ages.parallelStream().reduce(0, accumulator(), combiner());

    }

    private BinaryOperator<Integer> combiner() {
        return (a, b) -> {
//            System.out.println("2 + combiner: " + Thread.currentThread().getName() + "+++     a2 = " + a + ", b2 = " + b);
            return Integer.sum(a, b);
        };
    }

    private BiFunction<Integer, Integer, Integer> accumulator() {
        return (a, b) -> {
//            System.out.println("1 - accumulator: " + Thread.currentThread().getName() + ";;     a = " + a + ", b = " + b);
            return a + b;
        };
    }

}
