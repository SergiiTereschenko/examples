package st.examples.problems;

import java.util.concurrent.*;

public class ExecutorLock {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Account2 a = new Account2();
        Account2 b = new Account2();


        executor.submit(() -> {
            try {
                System.out.println("task " + Thread.currentThread().getName());
                transfer(a, b, 30);
            }
            catch (InterruptedException e) {
                System.err.println("task ");
            }
        });
        executor.submit(() -> {
            try {
                System.out.println("task " + Thread.currentThread().getName());
                transfer(b, a, 40);
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        });

//        stop(executor);
        executor.shutdown();

    }

    static void transfer(Account2 acc1, Account2 acc2, int sum) throws InterruptedException {
        synchronized (acc1) {
            TimeUnit.SECONDS.sleep(1);
            synchronized (acc2) {
                acc1.amount = acc1.amount + sum;
                acc2.amount = acc2.amount - sum;
            }
        }
    }

    static void stop(ExecutorService executor) {
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(15, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("termination interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }

    static class Account2 {
        int amount = 100;
    }

}
