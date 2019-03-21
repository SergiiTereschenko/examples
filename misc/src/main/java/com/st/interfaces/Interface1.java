package com.st.interfaces;

public interface Interface1 {

    void method1();

    default void log(String str){
        System.out.println("I1 logging::"+str);
    }

    default void print(String str) {
        if (!isNull(str))
            System.out.println("MyData Print::" + str);
    }

    static boolean isNull(String str) {
        System.out.println("Interface Null Check");

        return str == null ? true : "".equals(str) ? true : false;
    }
}
