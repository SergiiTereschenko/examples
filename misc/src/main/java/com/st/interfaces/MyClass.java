package com.st.interfaces;

public class MyClass implements Interface1, Interface2 {

    @Override
    public void method2() {
        log("method2");
    }

    @Override
    public void method1() {
        log("method1");
    }

//    @Override
//    public void log(String str){
//        System.out.println("MyClass logging::"+str);
////        Interface1.log("MyClass log");
//    }

    public static void main(String[] args) {
        Interface2 obj = new MyClass();
        obj.method1();
    }
}

