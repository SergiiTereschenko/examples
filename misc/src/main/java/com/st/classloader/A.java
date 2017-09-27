package com.st.classloader;


import java.net.URLClassLoader;
import java.util.Arrays;

public class A {

    public static void main(String[] args) throws Exception {
//        B b = new B();
        ClassLoader classLoader = A.class.getClassLoader();
        Class<?> aClass = classLoader.loadClass("com.st.classloader.B");
//        Class<?> b1 = (Class<?>) aClass.newInstance();
//        b.print();

        // #1 Show all folders/jars (resources) into class-loader
        URLClassLoader urlClassLoader = (URLClassLoader) A.class.getClassLoader();
        System.out.println(" #1");
        System.out.println(Arrays.toString(urlClassLoader.getURLs()));

        // #2 Show FULL Path to classes into class-loader
        System.out.println(" #2");
        System.out.println(
                A.class.getClassLoader().getResource(
                        B.class.getName().replace(".", "/") + ".class"));

        // #3 Show classes in jars bu *NIX commands
        //  find *.jar -exec jar -tf '{}' \; | grep MyClassName

        // -verbose :class  -  run jvm param to show full path to class
        // javap -private MyClassName

    }
}
