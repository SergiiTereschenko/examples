package com.st.misc;

import org.junit.Test;

public class Singleton  {
    private Singleton() {}

    private static class SingletonHolder {
        public static final Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }

    //-----------------------------------------------

    public static <T> T[] unsafe(T... elements) {
        return elements; // unsafe! don't ever return a parameterized varargs array
    }

    public static <T> T[] broken(T seed) {
        T[] plant = unsafe(seed, seed, seed); // broken! This will be an Object[] no matter what T is
        return plant;
    }

    @SafeVarargs
    public final static <T> T[] safe(T... toAdd) {
        for (T item : toAdd) {
            System.out.println(item.getClass().getSimpleName());
        }
        System.out.println(toAdd.getClass().getTypeName());
        return toAdd;
    }

//    public static void plant() {
//        String[] plants = broken("seed"); // ClassCastException
//    }
}