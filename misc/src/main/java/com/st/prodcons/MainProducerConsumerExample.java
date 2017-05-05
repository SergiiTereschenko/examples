package com.st.prodcons;

public class MainProducerConsumerExample
{

    private static boolean Even = true;
    private static boolean Odd = false;

    public static void main(String[] args) {
        Dropbox dropbox = new Dropbox();
        (new Thread(new AConsumer(Even, dropbox))).start();
        (new Thread(new AConsumer(Odd, dropbox))).start();
        (new Thread(new AProducer(dropbox))).start();
    }
}