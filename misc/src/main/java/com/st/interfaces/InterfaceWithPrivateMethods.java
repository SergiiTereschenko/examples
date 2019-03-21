package com.st.interfaces;

public interface InterfaceWithPrivateMethods {

    static String staticPrivate() {
        return "static private";
    }

    default String instancePrivate() {
        return "sdsdsd";
    }

    default void check() {
        String result = staticPrivate();
        InterfaceWithPrivateMethods pvt = new InterfaceWithPrivateMethods() {
            // anonymous class
        };
        result = pvt.instancePrivate();
    }
}