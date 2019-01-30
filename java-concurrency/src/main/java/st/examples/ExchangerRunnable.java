package st.examples;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerRunnable implements Runnable {

    Exchanger exchanger = null;
    Object object    = null;

    public ExchangerRunnable(Exchanger exchanger, Object object) {
        this.exchanger = exchanger;
        this.object = object;
    }

    public void run() {
        try {
            Object previous = this.object;
            System.out.println("---Thread: " + Thread.currentThread().getName());
            this.object = this.exchanger.exchange(this.object);

            System.out.println(
                    Thread.currentThread().getName() +
                            " exchanged " + previous + " for " + this.object
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Exchanger exchanger = new Exchanger();

        ExchangerRunnable exchangerRunnable1 =
                new ExchangerRunnable(exchanger, "A");

        ExchangerRunnable exchangerRunnable2 =
                new ExchangerRunnable(exchanger, "B");

        new Thread(exchangerRunnable1).start();
        System.out.println("---Thread 1 run---");
        TimeUnit.SECONDS.sleep(10);
        new Thread(exchangerRunnable2).start();
        System.out.println("---Thread 1 run---");

    }

}