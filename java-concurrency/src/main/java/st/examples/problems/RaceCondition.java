package st.examples.problems;

import org.junit.Assert;

public class RaceCondition {
    // The simplest kind of race condition is where two threads are updating some shared data
    // https://i.stack.imgur.com/m7HYo.png

    public static int value = 0;

    public static void main(String[] args) throws InterruptedException {
        MyCounter counter = new MyCounter();

        Thread thread1 = new Thread(new CounterIncRunnable(counter));
        thread1.setName("add thread");
        thread1.start();

        Thread thread2 = new Thread(new CounterIncRunnable(counter));
        thread2.setName("add thread2");
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Result should be 20000 but because of 2 thread race condition is less: " + counter.value());
        Assert.assertTrue(counter.value() < 20000);
    }
}


class CounterIncRunnable implements Runnable {
    private MyCounter counter;

    public CounterIncRunnable(MyCounter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}

class MyCounter {
    volatile int c = 0;

    //     synchronized void increment() {     // FIX
    void increment() {
        c++;
    }

    int value() {
        return c;
    }
}