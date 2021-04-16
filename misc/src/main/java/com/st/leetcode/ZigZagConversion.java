package com.st.leetcode;

import org.junit.Test;

public class ZigZagConversion {

    @Test
    public void test() {
        String str = "AB";
        String zigZag = convert(str, 5);
        System.out.println(zigZag);
    }

    public String convert(String s, int numRows) {
        if (s == null || s.isEmpty() || numRows <= 0) return "";
        if (numRows == 1 || s.length() == 1) return s;
        String[] arr = new String[Math.min(numRows, s.length())];
        int arrIndex = 0;
        int increment = 1;
        int arrIndCounter = -1;
        for (int i = 0; i < s.length(); i++) {
            String prevVal = arr[arrIndex] != null ? arr[arrIndex] : "";
            arr[arrIndex] = prevVal + s.charAt(i);

            arrIndCounter++;
            if (arrIndCounter == numRows - 1) {
                arrIndCounter = 0;
                increment = -increment;
            }
            arrIndex = arrIndex + increment;
        }

        return String.join("", arr);
    }
}
