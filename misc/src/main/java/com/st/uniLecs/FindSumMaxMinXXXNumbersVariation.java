package com.st.uniLecs;

import java.util.ArrayList;
import java.util.List;

public class FindSumMaxMinXXXNumbersVariation {

    public static void main(String[] args) {
        displaySpecialSum(509); // 1459
        System.out.println();
        displaySpecialSum(610); // 716
    }

    static void displaySpecialSum(int N)
    {
        int[] digits = getDigitArray(N);
//        int[] sortedByAscending =  digits.OrderBy(d => d).ToArray();
//        int[] sortedByDescending = digits.OrderByDescending(d => d).ToArray();

//        if (sortedByAscending[0] == 0)
//        {
//            int nonZeroIndex = Arrays.FindIndex(sortedByAscending, d => d > 0);
//            sortedByAscending[0] = sortedByAscending[nonZeroIndex];
//            sortedByAscending[nonZeroIndex] = 0;
//        }
//
//        var minNumber = int.Parse(string.Join("", sortedByAscending));
//        var maxNumber = int.Parse(string.Join("", sortedByDescending));
//        Console.WriteLine(string.Format("{0} ({1} + {2})", minNumber + maxNumber, minNumber, maxNumber));
    }

    static int[] getDigitArray(int number) {
        List<Integer> digits = new ArrayList<>();
        while (number > 0) {
            digits.add(number % 10);
            number /= 10;
        }
        return digits.stream().mapToInt(i -> i).toArray();
    }

}
