package com.st.prodcons;

public class Dropbox {

    private int number;
    private boolean empty = true;
    private boolean evenNumber = false;

    public synchronized int take(final boolean even) {
        while (empty || evenNumber != even) {
            try {
                System.out.format("%s is waiting ... %n", even ? "Even" : "Odd");
                wait();
            } catch (InterruptedException e) { }
        }
        System.out.format("%s took %d.%n", even ? "Even" : "Odd", number);
        empty = true;
        notifyAll();

        return number;
    }

    public synchronized void put(int number) {
        while (!empty) {
            try {
                System.out.println("Producer is waiting ...");
                wait();
            } catch (InterruptedException e) { }
        }
        this.number = number;
        evenNumber = number % 2 == 0;
        System.out.format("Producer put %d.%n", number);
        empty = false;
        notifyAll();
    }
}