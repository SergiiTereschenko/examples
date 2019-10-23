package com.st.shebang;

import java.util.Arrays;

public class HelloJavaScripts {

    public static void main(String[] args) {
        // The JVM compiles the source file into memory and then runs the first public main() method it finds.
        System.out.println("Hello, Java scripts! Only possible for java 11");
        System.out.println("args:" + Arrays.toString(args));
    }
}
