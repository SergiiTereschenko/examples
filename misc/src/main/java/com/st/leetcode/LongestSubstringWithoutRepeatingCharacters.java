package com.st.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {

        lengthOfLongestSubstring3("abcccccbcbb");
//        lengthOfLongestSubstring3("ab");

    }

    public static int lengthOfLongestSubstring_Set(String s) {
        if (s == null || s.length() == 0) return 0;
        int max = 1;
        int a1 = 0;
        int b2 = 1;
        Set set = new HashSet();
        set.add(s.charAt(a1));

        while (b2 < s.length()) {
            char charAtB2 = s.charAt(b2);
            if (!set.contains(charAtB2)) {
                set.add(charAtB2);
                b2++;
                max = Math.max(set.size(), max);
            } else {
                set.remove(s.charAt(a1));
                a1++;
            }
        }
        return max;
    }

    public static int lengthOfLongestSubstring_Array(String s) {
        if (s == null || s.length() == 0) return 0;
        int max = 1;
        int currentSize = 1;
        int a1 = 0;
        int b2 = 1;
        int[] set = new int[256];
        set[s.charAt(a1)]++;

        while (b2 < s.length()) {
            char charAtB2 = s.charAt(b2);
            if (set[charAtB2] == 0) {
                set[charAtB2]++;
                b2++;
                currentSize++;
                max = Math.max(max, currentSize);
            } else {
                set[s.charAt(a1)] = 0;
                currentSize--;
                a1++;
            }
        }
        return max;
    }

    public static int lengthOfLongestSubstring3(String s) {

        /*
        Approach: Sliding window approach.
                  Have a map with each character and its last found index.
                  Keep expanding the end index of the window.
                  If the char at end window index is found in charsLastIndexMap, move the start of the window at next index
                  of the previously found char index. Using Math.max() make sure that we are not moving start window backwards.
                  Also, every time keep updating maxLen.

        Complexity analysis: Time O(n), Space: O(n)
        */

        if (s.length() <= 1) return s.length();

        HashMap<Character, Integer> charsLastIndexMap = new HashMap<>();

        int windowStartIndex = 0;
        int windowEndIndex = 0;
        int maxLen = 0;

        for (windowEndIndex = 0; windowEndIndex < s.length(); windowEndIndex++) {

            char currChar = s.charAt(windowEndIndex);

            if (charsLastIndexMap.containsKey(currChar)) {
                windowStartIndex = Math.max(windowStartIndex, charsLastIndexMap.get(currChar) + 1); //Move start of window
            }

            maxLen = Math.max(maxLen, windowEndIndex - windowStartIndex + 1);                       //Update maxLen
            charsLastIndexMap.put(currChar, windowEndIndex);
        }

        return maxLen;
    }

}
