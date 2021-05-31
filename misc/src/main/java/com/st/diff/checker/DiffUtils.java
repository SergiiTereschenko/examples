package com.st.diff.checker;


public class DiffUtils {


    public static void main(String[] args) {
        diffChecker(3,4,5,2);
    }

    static boolean diffChecker(int... arr) {
//        Arrays.sort(arr);
        arr = bubbleSort(arr);

        for (int i = 1; i < arr.length; i++) {
            if (i + 1 == arr.length) {
                return true;
            }
            int diff1 = arr[i + 1] - arr[i];
            int diff2 = arr[i] - arr[i - 1];
            if (diff1 != diff2) {
                return false;
            }
        }
        return true;
    }


    static int[] bubbleSort(int... array) {
        boolean swapped = true;
        int j = 0;
        int tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < array.length - j; i++) {
                if (array[i] > array[i + 1]) {
                    tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                    swapped = true;
                }
            }
        }
        return array;
    }
}
