package com.st.java8;

import org.junit.Test;

public class DefaultTest {

    interface First {
        void doing1();

        default void execute() {
            System.out.println("First");
        }
    }

    interface Second extends First {
        void doing2();

        default void execute() {
            System.out.println("Second");
        }
    }

    static class Impl implements Second {
        public void doing1() {
            execute();
        }
        public void doing2() {
            execute();
        }
    }

    @Test
    public void test() {
        Impl impl = new Impl();
        impl.doing1();
        impl.doing1();
    }

}
