package com.st;


public class ParentMain {

    public static void main(String[] args) {
        Parent son = new Son();
    }

}

class Parent {

    public Parent() {
        print();
    }

    public void print() {
        System.out.println("Pi");
    }
}

class Son extends Parent{
    private double pi = Math.PI;

    public Son() {
        print();
    }

    @Override
    public void print() {
        System.out.println(pi);
    }
}