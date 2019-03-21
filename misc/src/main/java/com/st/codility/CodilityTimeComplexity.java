package com.st.codility;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CodilityTimeComplexity {

    @Test
    public void frogJump() {
        int startPosition = 10;
        int destination = 120;
        int jumpDist = 30;
        int distance = destination - startPosition;
        int remainderOfDivision = distance % jumpDist;
        int numberOfJumps = distance / jumpDist;
        numberOfJumps = remainderOfDivision == 0 ? numberOfJumps : numberOfJumps + 1;

        System.out.println(" Number of jumps: " + numberOfJumps);
    }

    @Test
    public void findMissingElement() {
        int[] a = {2,3,1,5};
        // #1
//        Arrays.sort(a);
//        for (int i = a.length - 1; i > 1; i--) {
//            if (a[i] - a[i - 1] != 1) {
//                System.out.println("Missing element is: " + (a[i] - 1));
//            }
//        }
        // #2
        int[] tmpA = new int [a.length + 2];
        Arrays.stream(a).forEach(i -> tmpA[i] = i);

        for (int i = tmpA.length - 1; i > 1; i--) {
            if (tmpA[i] - tmpA[i - 1] != 1) {
                System.out.println("Missing element is: " + (tmpA[i] - 1));
                return;
            }
        }

    }

    @Test
    public void tapeEquilibrium() {
        int[] a = {3,1,2,4,3};
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < a.length; i++) {
            int sum1 = IntStream.range(0, i+1).map(iii -> a[iii]).sum();
            int sum2 = IntStream.range(i+1, a.length).map(iii -> a[iii]).sum();
            int diff = Math.abs(sum2 - sum1);
            if (diff < min) min = diff;
        }
        System.out.println(min);
    }
}
