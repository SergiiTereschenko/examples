package com.st.leetcode;

import java.util.concurrent.TimeUnit;

public class MedianTwoSortedArrays {

    public static void main(String[] args) {
        int[] a1 = {};
        int[] a2 = {1, 3};

        long l = TimeUnit.HOURS.toSeconds(6);

        double medianSortedArrays = findMedianSortedArrays(a1, a2);
        System.out.println(medianSortedArrays);
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int length = nums1.length + nums2.length;
        int[] m = new int[length];

        int ind1 = 0;
        int ind2 = 0;
        int mainI = 0;

        while (ind1 < l1 && ind2 < l2) {
            if (nums1[ind1] < nums2[ind2]) {
                m[mainI++] = nums1[ind1++];
            } else {
                m[mainI++] = nums2[ind2++];
            }
        }
        while (ind1 < l1) {
            m[mainI++] = nums1[ind1++];
        }
        while (ind2 < l2) {
            m[mainI++] = nums2[ind2++];
        }

        return length % 2 == 0
            ? (m[length / 2] + m[length / 2 - 1]) / 2.0
            : m[length / 2];
    }

}
