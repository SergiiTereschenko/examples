package st.examples.deadlocks;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Shows difference in thread-dump  (Run main, then, Ctrl + Break to see)    logs vs different locks.
 * You will get "waiting to lock" in the thread dump when using intrinsic locks (synchronized)
 * and "parking to wait for" when using locks from java.util.concurrent (ReentrantLock).
 */
public class LockTest {


    final Lock lock = new ReentrantLock(true);

    synchronized void intrinsicLock() {
        Thread th = new Thread(new Runnable() {
            public void run() {
                intrinsicLock();
            }
        }, "-My Intrinsic thread-");
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
        }
    }

    void reentrantLock() {
        lock.lock();
        Thread th = new Thread(new Runnable() {
            public void run() {
                reentrantLock();
            }
        }, "-My ReentrantLock thread-");
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
        }
        lock.unlock();
    }


    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
//        lockTest.intrinsicLock();
        lockTest.reentrantLock();
    }
}
