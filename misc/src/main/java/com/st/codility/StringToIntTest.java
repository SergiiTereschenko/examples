package com.st.codility;

import org.junit.Test;

public class StringToIntTest {

    @Test
    public void test() {
        parseInt("12ADDF645DG%^%3");
    }

    // char 48 = 0, .... char 57 = 9

    int parseInt(String target) {

        char[] chars = target.toCharArray();
        int result = 0;
        for (char aChar : chars) {
            int intChar = (int) aChar;
            if (intChar < 48 || intChar > 57) continue;
            result *= 10;
            result += intChar - 48;
        }

        System.out.println(result);

        return 1;
    }

    @Test
    public void testGetAllChars() {
        for (int c = 0; c < 128; c++) {
            System.out.println(c + ": " + (char) c);
        }
    }
}
