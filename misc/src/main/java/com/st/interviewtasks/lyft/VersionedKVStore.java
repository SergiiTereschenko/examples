package com.st.interviewtasks.lyft;



//первый алгоритм был paginated рест клиент с кешом
//второй написать ин-мемори кеш с версионированием по вставке

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class VersionedKVStore {

    private AtomicInteger storeVersion = new AtomicInteger(0);
    private Map<String, List<Value>> store = new HashMap<>();


    @Test
    public void test() {
        VersionedKVStore kvStore = new VersionedKVStore();

        String k1 = "key1";
        String k2 = "key2";
        int val1 = 100;
        int val2 = 200;
        int val3 = 300;
        int ver1 = 1;
        int ver2 = 2;

        System.out.println("Put k1, ver = " + kvStore.put(k1, val1));
        System.out.println("Put k11, ver = " + kvStore.put(k1, val3));
        System.out.println("Put k2, ver = " + kvStore.put(k2, val2));

        System.out.println("Get k1, val = " + kvStore.get(k1));
        System.out.println("Get k2, val = " + kvStore.get(k2));

        System.out.println("GetVer k1v1, val = " + kvStore.get(k1, ver1));
        System.out.println("GetVer k1v2, val = " + kvStore.get(k1, ver2));

    }

    public Integer put(String key, Integer value) {
        Integer newVersion = storeVersion.incrementAndGet();

        Value newValue = new Value(value, newVersion);

        store.computeIfAbsent(key, k -> new LinkedList<>());

        List<Value> values = store.get(key);
        values.add(newValue);

        return newVersion;
    }

    public Integer get(String key) {
        List<Value> values = store.get(key);
        if (values != null && values.size() > 0) {
            int size = values.size();

            Integer result = values.get(size - 1).value;
            System.out.println(result);
            return result;
        }
        System.out.println("null");
        return null;
    }

    public Integer get(String key, Integer version) {
        if(version > storeVersion.get()){
            System.out.println("null");
            return null;
        }
        List<Value> values = store.get(key);
        if (values != null && values.size() > 0) {
            int size = values.size();
            Integer lastVersion =  values.get(size - 1).kvVersion;
            if(version >=lastVersion){
                Integer result = values.get(size - 1).value;
                System.out.println(result);
                return result;
            }
            else {
                int i = size - 1;
                while (i>=0){
                    Integer maxVersion = values.get(i).kvVersion;
                    if(version == maxVersion){
                        Integer result = values.get(i).value;
                        System.out.println(result);
                        return result;
                    }
                    if(maxVersion > version){
                        i--;
                    }
                    else {
                        break;
                    }
                }
                if(i<0){
                    System.out.println("null");
                    return null;
                }
                Integer result = values.get(i).value;
                System.out.println(result);
                return result;
            }
        }
        System.out.println("null");
        return null;
    }
}


class Value {

    Integer value;
    Integer kvVersion;

    Value(Integer value, Integer kvVersion) {
        this.value = value;
        this.kvVersion = kvVersion;
    }

}
