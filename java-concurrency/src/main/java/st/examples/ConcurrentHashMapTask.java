package st.examples;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTask {


    public static void main(String[] args) {

        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("1", "ONE");
        map.put("2", "TWO");
        map.get("1");
    }
}
