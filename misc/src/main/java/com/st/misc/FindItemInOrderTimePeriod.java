package com.st.misc;

public class FindItemInOrderTimePeriod {

    public static void main(String[] args) {

        long startTs = 1579651200000L;
        long endTs = 1580256000000L;
//        long currentTs = 1579780435056L;
        long currentTs = 1579789464889L;
//        long durationTs = 86400000L;   //day
        long durationTs = 600000L;   //10m
//        long durationTs = 1800000L;  //30m
        findIndexOfCurrentInPeriod(startTs, endTs, durationTs, 3, currentTs);
    }

    static long findIndexOfCurrentInPeriod(long start, long end, long durationSize, long itemsCount, long current) {
        if (end < current) return 0;
        if (start > current) return 0;

        long wholeTimePeriod = end - start;
        long numberOfPeriods = (wholeTimePeriod + durationSize - 1) / durationSize;   //round up integer division
        System.out.println("numberOfPeriods = " + numberOfPeriods);
        long currentPeriodIndex = ((current - start) + durationSize - 1) / durationSize;
        long currentIndexWithinCount = currentPeriodIndex % itemsCount == 0 ? itemsCount : currentPeriodIndex % itemsCount;

        System.out.println("Current time is equals to itemsCount index = " + currentIndexWithinCount);

        return currentIndexWithinCount;
    }

}
