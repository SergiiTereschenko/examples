package st.more.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.function.*;

/**
 * Demo of chaining computation
 */
final class CompletableFutureMain {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                sleep(500);
                return "Slow string";
            }
        }).handle(new BiFunction<String, Throwable, String>() {
            @Override
            public String apply(String s, Throwable e) {
                if (e != null) {
                    System.out.println("Kaboom: " + e);
                    return "";
                } else {
                    return s;
                }
            }
        }).thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                sleep(500);
                System.out.println("Slow length is: " + s.length());
            }
        }).thenAccept(new Consumer<Void>() {
            @Override
            public void accept(Void v) {
                latch.countDown();
            }
        });

        latch.await();
    }

    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted!");
        }
    }
}
