package com.st;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class App 
{

    private List synchedList;

    public App() {
        // create a new synchronized list to be used
        synchedList = Collections.synchronizedList(new LinkedList());
    }

    // method used to remove an element from the list
    public String removeElement() throws InterruptedException {
        System.out.println("removeElement() called...");
        synchronized (synchedList) {

            // while the list is empty, wait
            while (synchedList.isEmpty()) {
                System.out.println("Before wait() call...");
                synchedList.wait();
                System.out.print(Thread.currentThread().getName() + "  ");
                System.out.println("After wait()...");
            }
            String element = (String) synchedList.remove(0);

            return element;
        }
    }

    // method to add an element in the list
    public void addElement(String element) {
        System.out.println("addElement() called...");
        synchronized (synchedList) {

            // add an element and notify all that an element exists
            synchedList.add(element);
            System.out.println("New Element:'" + element + "'");

            synchedList.notifyAll();
            System.out.println("notifyAll called!");
        }
        System.out.println("addElement() end");
    }

    public static void main(String[] args) {
        final App demo = new App();

        Runnable runARemove = new Runnable() {

            public void run() {
                try {
                    String item = demo.removeElement();
                    System.out.println("" + item);
                } catch (InterruptedException ix) {
                    System.out.println("Interrupted Exception!");
                } catch (Exception x) {
                    System.out.println("Exception thrown.");
                }
            }
        };

        Runnable runBAdd = new Runnable() {

            // run adds an element in the list and starts the loop
            public void run() {
                demo.addElement("Hello!");
            }
        };

        try {
            Thread threadA1Remove = new Thread(runARemove, "A1_Rem");
            threadA1Remove.start();

            Thread.sleep(3000);

            Thread threadA2REmove = new Thread(runARemove, "A2_Rem");
            threadA2REmove.start();

            Thread.sleep(3000);

            Thread threadBAdd = new Thread(runBAdd, "B_Add");
            threadBAdd.start();

            Thread.sleep(10000);

            threadA1Remove.interrupt();
            threadA2REmove.interrupt();
        } catch (InterruptedException x) {
        }
    }
}
