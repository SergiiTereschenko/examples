package com.st.javavagent;

/**
 *
 * https://habrahabr.ru/post/230239/
 * https://www.lektorium.tv/lecture/24350
 *
 * Must implement method
 * public static void premain(String args);
 * or
 * public static void premain(String args, Instrumentation inst);
 */

public class AgentSimple1 {
    public static void premain(String args) {
        System.out.println("Hello! I`m java-agent :> he-he");
    }
}
