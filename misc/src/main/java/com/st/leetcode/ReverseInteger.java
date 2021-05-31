package com.st.leetcode;

import org.junit.Test;

public class ReverseInteger {

    @Test
    public void test() {
        int reverse = reverse(-2147483648);
        System.out.println(reverse);
    }

    public int reverse(int x) {
        int currentNum = x;
        long reversed = 0;
        int rest;
        while(currentNum != 0) {
            rest = currentNum % 10;
            currentNum = currentNum / 10;
            reversed = reversed * 10 + rest;
        }

        if (reversed < Integer.MIN_VALUE || reversed > Integer.MAX_VALUE) return 0;
        return (int) reversed;
    }
}
