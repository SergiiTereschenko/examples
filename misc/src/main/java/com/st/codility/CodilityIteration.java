package com.st.codility;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CodilityIteration {

    @Test
    public void printAsterisks1() {
        int n = 9;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void printAsterisks2() {
        int n = 9;
        for (int i = n; i >= 1; i = i - 2) {
            int gap = n - i;
            System.out.print(StringUtils.repeat(" ", gap));
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void binaryGap() {
        int n = 1041;
//        String binary = Integer.toBinaryString(n);
        String binary = "001110001010000100000";
        char[] chars = binary.toCharArray();
        boolean gapsBegin = false;
        int maxZeros = 0;
        int currentZeros = 0;
        for (char cr : chars) {
            int chrInt = cr - '0';
            if (chrInt == 1) {
                if (gapsBegin) {
                    maxZeros = maxZeros > currentZeros ? maxZeros : currentZeros;
                    currentZeros = 0;
                }
                gapsBegin = true;
            } else if (gapsBegin) {
                currentZeros++;
            }
        }

        System.out.println("Maximum binary gap is: " + maxZeros);
    }

    @Test
    public void oddOccurrencesInArray() {
        int[] arr = {1, 2, 3, 1, 5, 2, 3, 5, 6, 500000, 6, 125, 500000};
        Set<Integer> set = new HashSet<Integer>();

        for (int el : arr) {
            if (!set.contains(el)) {
                set.add(el);
            } else {
                set.remove(el);
            }
        }
        if (!set.isEmpty()) System.out.println(" Odd element: " + set.iterator().next());
    }

    @Test
    public void cyclicRotation() {
        int[] arr = {1, 2, 3, 4, 5};
        int times = 3;
        int lastElement;
        int formerElem;
        int shifterElem;
        for (int num = 1; num <= times; num++) {
            lastElement = arr[arr.length - 1];
            shifterElem = arr[0];
            for (int i = 1; i < arr.length; i++) {
                formerElem = arr[i];
                arr[i] = shifterElem;
                shifterElem = formerElem;
            }
            arr[0] = lastElement;
        }
        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void stringSortToInt() {
        String[] arr = {"3","7","6","1","2","3","9","4"};
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        String collect = Arrays.stream(arr)
                .map(Objects::toString).collect(Collectors.joining());
        System.out.println(Integer.parseInt(collect));

    }

//    static void rotate() {
//        int x = arr[arr.length - 1], i;
//        for (i = arr.length - 1; i > 0; i--)
//            arr[i] = arr[i - 1];
//        arr[0] = x;
//    }


    //-----------------------------
    @Test
    public void duplicatesArray() {
        int[] arr = {1, 2, 3, 1, 2, 3};
        int K = 3;
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<Integer>());
                map.get(key).add(i);
            } else {
                List<Integer> positions = map.get(key);
                for (Integer pos : positions) {
                    if (Math.abs(pos - i) <= K) {
                        System.out.printf("Bingo! There is element %d , in position %d and %d, \n", key, i, pos);
                        break;
                    }
                }
                positions.add(i);
            }
        }
        System.out.println("End.");
    }

    @Test
    public void findOneArrayIntoAnother() {
        int[] a = {7,65,456,67,23,98,565,77,56,4,45,67,45,547,8,855,9,3,44,3,7,79,5,8,890,56,4,5,6,7,8,9,848,11,158,16,19,98,54,54,65,59,54,1541,541,5,1,560,37,246,23,35,23,23,23,2,4,57567,45,5,5456,35346,456,456,5,75,};
        int[] b = {855, 848, 98,6,2,3,4,5,8,44};

        int[] tmpA = new int[100000];

        for (int ia : a) {
            tmpA[ia] = ia;
        }
//        Set<Integer> set2 = new HashSet<>();
//        Collections.addAll(set2, a);
//        Set<Integer> set = new HashSet<Integer>(Arrays.<Integer>asList(a));

        for (int ib : b) {
            try {
                if (tmpA[ib] == 0) {
                    System.out.println("Array b doesn't included into array a");
                    return;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Array b doesn't included into array a");
                return;
            }
        }
        System.out.println("Bingo! Array b included into array a");
    }

    @Test
    public void compareAndSwapTest() {

        AtomicInteger ai = new AtomicInteger();
        ai.incrementAndGet();
        ai.compareAndSet(1, 2);

    }

}
