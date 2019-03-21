package com.st.interfaces;

public interface Interface2 extends Interface1 {

    void method2();

    default void log(String str){
        System.out.println("I2 logging::"+str);
    }

}
