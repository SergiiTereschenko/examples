package com.st.javavagent;


public class MainClassAgentTester {
//    public static void main(String[] args) {
//        System.out.print("Hello! I`m agent tester. It's my main() method -");
//        System.out.println("JavaAgent should been injected code before my main() method has performed...");
//    }

//}
        public static void main(String[] args) {
            A a = new A();
            B b = new B();
            C c = null;
        }
    }

    class A {};
    class B {};
    class C {};


