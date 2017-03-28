package st.examples.waitnotify;


public class TestWaitNotify {

    private static final Object monitor = new Object();

    void doWithWait() {
        System.out.println("WAIT: Before Monitor: " + Thread.currentThread().getId() + " : " + Thread.currentThread().getState());
        synchronized (monitor) {

            try {
                System.out.println("-WAIT: Call SLEEP 4000: " + Thread.currentThread().getId() + " : " + Thread.currentThread().getState());
                Thread.sleep(4000);
                System.out.println("-WAIT: Call WAIT: " + Thread.currentThread().getId() + " : " + Thread.currentThread().getState());
                monitor.wait();
                System.out.println("-WAIT: After Call WAIT: " + Thread.currentThread().getId() + " : " + Thread.currentThread().getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void doWithNotify() throws InterruptedException {
        System.out.println("NOTIFY: Before Monitor: " + Thread.currentThread().getId() + " : " + Thread.currentThread().getState());
        synchronized (monitor) {
            System.out.println("-NOTIFY: After Monitor  Call NOTIFY: " + Thread.currentThread().getId() + " : " + Thread.currentThread().getState());
            monitor.notify();
            Thread.sleep(2000);
            System.out.println("-NOTIFY: After NOTIFY: " + Thread.currentThread().getId() + " : " + Thread.currentThread().getState());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        TestWaitNotify testWaitNotify = new TestWaitNotify();

        Thread threadWait1 = new Thread(new WaitRunnable(testWaitNotify));
        Thread threadWait2 = new Thread(new WaitRunnable(testWaitNotify));
        Thread threadNotify1 = new Thread(new NotifyRunnable(testWaitNotify));
        Thread threadNotify2 = new Thread(new NotifyRunnable(testWaitNotify));

        threadWait1.start();
        Thread.sleep(1000);
        threadWait2.start();
        Thread.sleep(8000);
        threadNotify1.start();
        Thread.sleep(3000);
        threadNotify2.start();
        Thread.sleep(1000);
        threadWait1.join();
        threadWait2.join();
        threadNotify1.join();
        threadNotify2.join();

        System.out.println("---END---" + Thread.currentThread().getId() + " : " + Thread.currentThread().getState());
    }

    static class WaitRunnable implements Runnable {
        TestWaitNotify target;

        WaitRunnable(TestWaitNotify target) {
            this.target = target;
        }

        public void run() {
            target.doWithWait();
        }
    }

    static class NotifyRunnable implements Runnable {
        TestWaitNotify target;

        public NotifyRunnable(TestWaitNotify target) {
            this.target = target;
        }

        public void run() {
            try {
                target.doWithNotify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}




