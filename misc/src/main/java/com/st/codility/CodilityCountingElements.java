package com.st.codility;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CodilityCountingElements {

    @Test
    public void permutationCheck() {
//        int[] a = {5,1,3,2};
        int[] a = {5,1,3,2,4,6,9,8};

        int arrLenght = 0;
        for(int i : a) {
            if (arrLenght < i) arrLenght = i;
        }

        int[] tmpA = new int [arrLenght + 1];
        for (int ai : a) {
            tmpA[ai] = ai;
        }

        for (int i = 1; i < tmpA.length; i++) {
            if (tmpA[i] == 0) {
                System.out.println("No permutation : " + 0);
                return;
            }
        }
        System.out.println("Permutation: " + 1);
    }


    @Test
    public void findEarliestTimeFrogCanJumpToTheOtherSideOfRiver() {
        int[] a = {1,3,1,4,2,3,5,4};
        int x = 5;
        // create [0,1,2,3,4,5]
//        int[] leafsInEachIndexes = new int [x];
//        Arrays.setAll(leafsInEachIndexes, i -> i);
        int[] leafsInEachIndexes = IntStream.range(0, x + 1).toArray();

        for (int i = 0; i < a.length; i++) {
            int ai = a[i];
            if (leafsInEachIndexes.length >= ai) {
                leafsInEachIndexes[ai] = 0;
                if (Arrays.stream(leafsInEachIndexes).sum() == 0) {
                    System.out.printf("Frog can reach opposite bank on %d seconds \n", i);
                    return;
                }
            }
        }
        System.out.println("Frog can't reach opposite river bank -1");
    }

    @Test
    //finds-smallest-positive-integer-not-present-in-an-array
    public void missingInteger() {
        int[] a = {-1,3,-4,4,1,5,4,2};
        Set<Integer> ints = IntStream.of(a).boxed().collect(Collectors.toSet());

        int smallestInt = 1;
        while (ints.contains(smallestInt)) {
            smallestInt++;
        }

        System.out.println("Smallest positive integer is: " + smallestInt);
    }

    @Test
    public void maxCounter() {
        int[] a = {3,4,4,6,1,4,4};
        int sizeN = 5;
        int [] counters = new int[sizeN];
        Set<Integer> ints = IntStream.of(a).boxed().collect(Collectors.toSet());



    }




}
