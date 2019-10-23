package com.st.uniLecs;

import org.apache.commons.lang3.StringUtils;


/**For this problem, your goal is to sort an array of 0, 1 and 2's but you must do this in place,
        in linear time and without any extra space (such as creating an extra array).
        This is called the Dutch national flag sorting problem.
        For example, if the input array is [2,0,0,1,2,1]
        then your program should output [0,0,1,1,2,2]
        and the algorithm should run in O(n) time.
*/

public class DutchNationalFlagProblem {

    public static void main(String[] args) {


        System.out.println("UniLecs");

        // sort by ascending
        int[] arr = new int[]{0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        dnfSort(arr);
        System.out.println("Answer = " + StringUtils.join(arr, ", "));

        // sort by descending
//        arr = new int[]{0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
//        dnfSortByDescending(arr);
//        System.out.println("Answer = " + StringUtils.join(arr, ", "));
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void dnfSort(int[] arr) {
        int start = 0, mid = 0, end = arr.length - 1;
        int point = 1; // опорная точка

        while (mid <= end) {
            if (arr[mid] < point) // элемент равен нулю
            {
                swap(arr, start, mid);
                start++;
                mid++;
            } else if (arr[mid] > point) // элемент равен 2
            {
                swap(arr, mid, end);
                end--;
            } else // элемент равен 1
            {
                mid++;
            }
        }
    }

//    private static void dnfSortByDescending(int[] arr) {
//        int start = 0, mid = 0, end = arr.length - 1;
//        int point = 1; // опорная точка
//
//        while (mid <= end) {
//            if (arr[mid] < point) // элемент равен нулю
//            {
//                swap(arr, mid, end);
//                end--;
//            } else if (arr[mid] > point) // элемент равен 2
//            {
//                swap(arr, start, mid);
//                start++;
//                mid++;
//            } else // элемент равен 1
//            {
//                mid++;
//            }
//        }
//    }
}
