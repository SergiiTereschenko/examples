package st.examples.deadlocks;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockTest {

    Object lock1 = new Object();
    Object lock2 = new Object();

    public static void main(String[] args) {

        Account a = new Account();
        Account b = new Account();

        AccTransfer tr1 = new AccTransfer(a, b, 30);
        AccTransfer tr2 = new AccTransfer(b, a, 40);

        tr1.start();
        tr2.start();

    }

//    void transfer(Account acc1, Account acc2, int sum) throws InterruptedException {
//        synchronized (acc1) {
//            Thread.sleep(1000);
//            synchronized (acc2) {
//                acc1.amount = acc1.amount + sum;
//                acc2.amount = acc2.amount - sum;
//            }
//        }
//    }

//    void task2() throws InterruptedException {
//        synchronized (lock2) {
//            Thread.sleep(1000);
//            synchronized (lock1) {
//                System.out.println("Task2 execution");
//            }
//        }
//    }
}

class AccTransfer extends Thread {
    Account a;
    Account b;
    int summ;

    public AccTransfer(Account a, Account b, int summ) {
        this.a = a;
        this.b = b;
        this.summ = summ;
    }

    @Override
    public void run() {
        transfer(a, b, summ);
    }

    void transfer(Account acc1, Account acc2, int sum)  {
        synchronized (acc1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            synchronized (acc2) {
                acc1.amount = acc1.amount + sum;
                acc2.amount = acc2.amount - sum;
            }
        }
    }

}

class Account {
    int amount = 100;
}
