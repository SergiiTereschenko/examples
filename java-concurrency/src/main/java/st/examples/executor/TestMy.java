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

}
