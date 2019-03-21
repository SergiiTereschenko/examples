package com.st.codility;

import org.junit.Assert;
import org.junit.Test;

public class CodilityArrayShiftTest {

    @Test
    public void shift1() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {5, 1, 2, 3, 4};
        int k = 1;
        shift_2(arr, k);
        Assert.assertArrayEquals(arr, expected);
    }

    @Test
    public void shift3() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] expected = {5, 6, 1, 2, 3, 4};
        int k = 2;
        shift_2(arr, k);
        Assert.assertArrayEquals(arr, expected);
    }

    @Test
    public void shift4() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] expected = {5, 6, 1, 2, 3, 4};
        int k = 14;
//        shift(arr, k);
        shift_2(arr, k);
        Assert.assertArrayEquals(arr, expected);
    }

    private void shift(int[] arr, int k) {
        for (int i = 0; i < k; i++) {
            int current = arr[arr.length - 1];
            int next = 0;
            for (int j = 0; j < arr.length; j++) {
                next = arr[j];
                arr[j] = current;
                current = next;
            }
        }
    }

    private void shift_2(int[] arr, int k) {
        int shiftInd = arr.length < k ? k % arr.length : k;
        int[] tmp = new int[arr.length];

        System.arraycopy(arr, 0, tmp, 0, arr.length);
        System.arraycopy(tmp, arr.length - shiftInd, arr, 0, shiftInd);
        System.arraycopy(tmp, 0, arr, shiftInd, arr.length - shiftInd);
    }
}
