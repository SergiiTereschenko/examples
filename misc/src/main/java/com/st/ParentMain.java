package com.st;


public class ParentMain {

    public static void main(String[] args) {
        Parent son = new Son();
        System.out.println(son.a);
    }

}

class Parent {
    int a = 1;
    public Parent() {
        print();
    }

    public void print() {
        System.out.println("Pi");
    }
}

class Son extends Parent{
    int a = 2;
    private double pi = Math.PI;

    public Son() {
        print();
    }

    @Override
    public void print() {
        System.out.println(pi);
    }
}