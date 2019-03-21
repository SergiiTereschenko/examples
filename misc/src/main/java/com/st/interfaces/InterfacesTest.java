package com.st.interfaces;

import org.junit.Test;

public class InterfacesTest {
    @Test
    public void testA1B1Class1() {

        Impl1 impl = new Impl1();
        impl.print(); //Class1
    }

    class Impl1 extends Class1 implements A1, B1 {
        @Override
        public void print() {
//        A1.super.print();
            super.print();
        }
    }

    interface A1 {
        default void print() {
            System.out.println("A1");
        }
    }

    interface B1 {
        default void print() {
            System.out.println("B1");
        }
    }

    class Class1 {
        void print() {
            System.out.println("Class1");
        }
    }

    //////////////////
    @Test
    public void testA2B2() {
        Impl2 impl = new Impl2();
        impl.print(); //B2
    }

    class Impl2 implements A2, B2 {
//        @Override
//        public void print() {
//        }
    }

    interface A2 {
        default void print() {
            System.out.println("A2");
        }
    }

    interface B2 extends A2 {
        default void print() {
            A2.super.print();
            System.out.println("B2");
        }
    }


    //////////////////
    @Test
    public void testA3B31B32_Diamond() {
        Impl3 impl = new Impl3();
        impl.print();
    }

    class Impl3 implements B31, B32 {
//        @Override
//        public void print() {
//        }
    }

    interface A3 {
        default void print() {
            System.out.println("A3");
        }
    }

    interface B31 extends A3 {
//        default void print() {
//            System.out.println("B31");
//        }
    }

    interface B32 extends A3 {
        default void print() {
            System.out.println("B32");
        }
    }
}
