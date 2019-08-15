package com.st.prodCons;

import java.util.Random;

public class AProducer implements Runnable {

    private Dropbox dropbox;

    public AProducer(Dropbox dropbox) {
        this.dropbox = dropbox;
    }

    public void run() {
        Random random = new Random();
        while (true) {
            int number = random.nextInt(10);
            try {
                Thread.sleep(random.nextInt(100));
                dropbox.put(number);
            } catch (InterruptedException e) { }
        }
    }
}