package st.examples.producerconsumer;


import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class PCBlockingQueue {
    private static Random random = new Random();
    private static volatile boolean sharedNonStopTrigger = true;

    static int random() {
        return random.nextInt(2000);
    }

    static int random2() {
        return random.nextInt(3000);
    }

    public static void main(String args[]) {
        BlockingDeque<Integer> sharedQueue = new LinkedBlockingDeque<Integer>(10);

        Thread commandLineReaderThread = new Thread(new CommandLineReader(), "Command-Line-Reader");
        Thread prodThread = new Thread(new Producer(sharedQueue), "PRODUCER");
        Thread consThread = new Thread(new Consumer(sharedQueue), "CONSUMER");

        commandLineReaderThread.start();
        prodThread.start();
        consThread.start();
    }


    static class Producer implements Runnable {
        private final BlockingDeque<Integer> sharedQueue;

        Producer(BlockingDeque<Integer> sharedQueue) {
            this.sharedQueue = sharedQueue;
        }

        public void run() {
            while (sharedNonStopTrigger) {
                try {
                    Thread.sleep(random());

                    Integer last = sharedQueue.peekLast();
                    int in = last == null ? 1 : ++last;
                    System.out.println("Produced: " + in + " , sharedNonStopTrigger = " + sharedNonStopTrigger);
                    sharedQueue.putLast(in);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Stop called Producer");
        }
    }


    static class Consumer implements Runnable {
        private final BlockingDeque<Integer> sharedQueue;

        Consumer(BlockingDeque<Integer> sharedQueue) {
            this.sharedQueue = sharedQueue;
        }

        public void run() {
            while (sharedNonStopTrigger) {
                try {
                    Thread.sleep(random2());

                    Integer last = sharedQueue.pollLast();
                    System.out.println("Consumed: " + last + " , sharedNonStopTrigger = " + sharedNonStopTrigger);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Stop called Consumer");
        }
    }

    static class CommandLineReader implements Runnable {
        Scanner scan = new Scanner(System.in);

        public void run() {
            while (true) {
                System.out.println(" - CommandLineReader ready to read - ");
                String s = scan.nextLine();
                if ("f".equals(s)) {
                    sharedNonStopTrigger = false;
                    return;
                }
                try {
                    Thread.sleep(3);
                    System.out.println(" - CommandLineReader after read- ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
