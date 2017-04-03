package st.examples.producerconsumer;


import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

public class PCBlockingQueue {
    private static Random random = new Random();
    private static boolean sharedNonStop = true;
    final static ReentrantLock lock = new ReentrantLock();

    static int random() {
        return random.nextInt(3000);
    }

    public static void main(String args[]) {
        BlockingDeque<Integer> sharedQueue = new LinkedBlockingDeque<Integer>(5);

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
            while (sharedNonStop) {
                try {
                    Thread.sleep(random());

                    lock.lock();

                    Integer last = sharedQueue.peekLast();
                    int in = last == null ? 1 : ++last;
                    System.out.println("Produced: " + in + " , sharedNonStop = " + sharedNonStop);
                    sharedQueue.putLast(in);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
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
            while (sharedNonStop) {
                try {
                    Thread.sleep(random());

                    lock.lock();
                    System.out.println("Consumed: " + sharedQueue.takeLast() + " , sharedNonStop = " + sharedNonStop);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } finally {
                    lock.unlock();
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
                    sharedNonStop = false;
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
