package com.st.codility;

import org.junit.Test;

public class TimeComplexity {

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
    public void permMissingElement() {

        

    }

}
