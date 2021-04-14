package com.st.misc;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

public class AtomicReferenceExample {

    private static AtomicReference<Double> sum = new AtomicReference<>();
    private static Double sumDouble;

    @Test
    public void withAR() throws InterruptedException {
        for (int k = 0; k < 9; k++) {
            sum.set(0d);
            ExecutorService es = Executors.newFixedThreadPool(50);
            for (int i = 1; i <= 50; i++) {
                int finalI = i;
                es.execute(() -> {
                    double pow = Math.pow(1.5, finalI);
                    sum.accumulateAndGet(pow,
                        (d1, d2) -> d1 + d2);
                });
            }
            es.shutdown();
            es.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println(sum.get());
        }
    }

    @Test
    public void withoutAR() throws InterruptedException {
        for (int k = 0; k < 9; k++) {
            sumDouble = 0d;
            ExecutorService es = Executors.newFixedThreadPool(50);
            for (int i = 1; i <= 50; i++) {
                int finalI = i;
                es.execute(() -> {
                    sumDouble+=Math.pow(1.5, finalI);
                });
            }
            es.shutdown();
            es.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println(sumDouble);
        }
    }

}
