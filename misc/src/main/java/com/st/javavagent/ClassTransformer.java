package com.st.javavagent;


import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassTransformer implements ClassFileTransformer {

    private static int count = 0;

    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {

        System.out.println("--=== Load class: " + className.replaceAll("/", "."));
        System.out.println(String.format("--=== Loaded [%s] classes", ++count));
        return classfileBuffer;
    }
}
