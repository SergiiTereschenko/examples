package com.st.codility;

import org.junit.Test;

import java.util.Arrays;

public class ArrayContainsTest {

    static Integer[] firstArray = prepareArray(100000);
    static Integer[] secondArray = prepareArray(100000);

    @Test
    public void test() {
//        System.out.println(args.length);
        long current = System.currentTimeMillis();
        //1
//        for (int i = 0; i <= firstArray.length - 1; i++) {
//            boolean result = false;
//            for (int j = 0; j <= secondArray.length - 1; j++) {
//                if (firstArray[i].intValue() == secondArray[j].intValue()) {
//                    result = true;
//                    break;
//                }
//            }
//            if (result == false) {
//                System.out.println("Second array does not contains first");
//            }
//        }


        //2
//        Set<Integer> set1 = new HashSet<>(Arrays.asList(firstArray));
//        Set<Integer> set2 = new HashSet<>(Arrays.asList(secondArray));
//        System.out.println(set1.containsAll(set2));

        //3
        int[] tmpA = new int[100000];  // or need find max val from firstArray
        Arrays.fill(tmpA, -1);

        for (int ia : firstArray) {
            tmpA[ia] = ia;
        }

        for (int ib : secondArray) {
            try {
                if (tmpA[ib] == -1) {
                    System.out.println("Array b doesn't included into array a");
                    return;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Array b doesn't included into array a");
                return;
            }
        }


        System.out.println("Time = " + String.valueOf(System.currentTimeMillis() - current));
    }

    public static Integer[] prepareArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i <= array.length - 1; i++) {
            array[i] = i;
        }
        return array;
    }

}
