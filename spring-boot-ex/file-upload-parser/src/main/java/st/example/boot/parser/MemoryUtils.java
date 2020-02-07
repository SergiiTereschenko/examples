package st.example.boot.parser;

public class MemoryUtils {

    private static void logMemory(String... when) {
        if(when != null) System.out.println(when[0]);
        System.out.println("Max Memory: " + Runtime.getRuntime().maxMemory() / 1048576);
        System.out.println("Total Memory: " + Runtime.getRuntime().totalMemory() / 1048576);
        System.out.println("Free Memory: " + Runtime.getRuntime().freeMemory() / 1048576);
    }

}
