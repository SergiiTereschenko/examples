package com.st.javavagent;


import java.lang.instrument.Instrumentation;

public class AgentCounter2 {

    public static void premain(String agentArgument, Instrumentation instrumentation) {
        System.out.println("-=Agent Counter premain()");
        instrumentation.addTransformer(new ClassTransformer());
    }
}

