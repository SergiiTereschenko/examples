package com.st.classloader;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class B {
    static List<? super Number> mainList = new ArrayList<>();

    void print() {
        System.out.println("- B.print called -");
    }

    public static void main(String[] args) throws Exception {

        List<Object> listObj = new ArrayList();
        List<?> listWild = new ArrayList();

        listObj.add(new AtomicInteger());
        listObj.add(new Object());
        listObj.add("fdfdd");

//        listWild.add("sdsdsd");
//        listWild.add(new Object());

        mainList.add(1);
        mainList.add(2.0);
//        mainList.add(new Object());

    }

    static List<? extends Number> getList() {
        List<Integer> listObj = new ArrayList();
        listObj.add(1);
        listObj.add(2);
        listObj.add(3);
        return listObj;
    }

    static void setList(List<? extends Number> list) {
        mainList.addAll(list);
    }

}
