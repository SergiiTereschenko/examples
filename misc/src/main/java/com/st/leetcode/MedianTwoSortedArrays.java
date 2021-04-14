package com.st.leetcode;

import java.util.concurrent.TimeUnit;

public class MedianTwoSortedArrays {

    public static void main(String[] args) {
        int[] a1 = {2};
        int[] a2 = {1, 3, 7};

        long l = TimeUnit.HOURS.toSeconds(6);

//        double medianSortedArrays = findMedianSortedArrays(a1, a2);
        double medianSortedArrays = findMedianSortedArrays2(a1, a2);
        System.out.println(medianSortedArrays);
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int[] m = new int[length];

        int ind1 = 0;
        int ind2 = 0;
        int mainI = 0;

        while (ind1 < nums1.length && ind2 < nums2.length) {
            if (nums1[ind1] < nums2[ind2]) {
                m[mainI++] = nums1[ind1++];
            } else {
                m[mainI++] = nums2[ind2++];
            }
        }
        while (ind1 < nums1.length) {
            m[mainI++] = nums1[ind1++];
        }
        while (ind2 < nums2.length) {
            m[mainI++] = nums2[ind2++];
        }

        return length % 2 == 0
            ? (m[length / 2] + m[length / 2 - 1]) / 2.0
            : m[length / 2];
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int indexAtMedian = (totalLength / 2);
        int i = 0;
        int j = 0;

        int beforeMedian = 0;
        int medianIfOdd = 0;

        for (int pos = 0; pos <= indexAtMedian; pos++) {
            int valueAtPos = 0;
            if (i < nums1.length && (j >= nums2.length || nums1[i] <= nums2[j])) {
                valueAtPos = nums1[i];
                i++;
            } else {
                valueAtPos = nums2[j];
                j++;
            }

            if (pos == indexAtMedian - 1) {
                beforeMedian = valueAtPos;
            }
            if (pos == indexAtMedian) {
                medianIfOdd = valueAtPos;
            }
        }

        boolean isEven = totalLength % 2 == 0;

        if (isEven) {
            return ((double) beforeMedian + (double) medianIfOdd) / 2;
        } else {
            return medianIfOdd;
        }
    }

}
