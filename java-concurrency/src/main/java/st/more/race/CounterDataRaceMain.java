package st.more.race;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Demo of two threads updating the same memory without synchronization.
 */
final class CounterDataRaceMain {

    private static final int[] counter_w_race_condition = new int[]{0};
    private static transient int counter_w_race_condition2 = 0;
    private static final AtomicIntegerArray COUNTER_WO_RACE_CONDITION = new AtomicIntegerArray(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        int numThreads = 10; //10;
        CountDownLatch latch = new CountDownLatch(numThreads);

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            futures.add(executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());

//                waitUntilReady(latch);

                System.out.println("Goooo!");
//                for (int j = 0; j < 1_000_000; j++) {
                for (int j = 0; j < 10_000; j++) {
                    // data race here
                    counter_w_race_condition[0]++;
                    COUNTER_WO_RACE_CONDITION.incrementAndGet(0);
                    counter_w_race_condition2++;
                }
            }));
        }

        for (Future<?> future : futures) {
            future.get();
        }

        executorService.shutdown();

        System.out.println(counter_w_race_condition[0]);
        System.out.println(COUNTER_WO_RACE_CONDITION.get(0));
        System.out.println(counter_w_race_condition2);
    }

    private static void waitUntilReady(CountDownLatch latch) {
        latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Not gonna happen", e);
        }
    }
}
