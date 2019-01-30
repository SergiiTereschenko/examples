package st.examples.executor;

import org.junit.Test;

public class TestMy {

    @Test
    public void test1() {

        int i1 = 33 >> 1;
        System.out.println("i1 = " + i1);

        Integer workSize = 2 << 22;
        System.out.println("workSize = " + workSize);

    }

    @Test
    public void test2() {

        Integer i1 = 1271;
        Integer i2 = 1271;
        Integer i22 = i2;

        String s1 = "DDDDD1";
        String s2 = "DDDDD1";

        System.out.println(i1 == i2);
        System.out.println(s1 == s2);
    }

}
