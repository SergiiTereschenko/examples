package com.st.leetcode;

public class LongestPalindromicSubstring2 {

    public static void main(String[] args) {
//        String str = "scarac";
        String str = "ccc";

        String lps = longestPalindrome(str);
        System.out.println(lps);

    }

    static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        int halfLength = s.length() / 2;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
            if(i >= halfLength && len > halfLength) break;
        }
        return s.substring(start, end + 1);
    }

    static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

}
