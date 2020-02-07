package com.st.misc;

public class StrangeInterfacePrivateMethod9java {

    public static void main(String[] args) {
        I1 i1 = null;
        I2 i2 = null;

        i1.test(); // compiled
//        i2.test(); // not compiled
    }

    interface I1 {
        private void test() {}
    }

    interface I2 extends I1 {}
}
