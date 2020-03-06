package com.st.leetcode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://www.youtube.com/watch?v=XKu_SEDAykw

public class SumOfTwoElementInArray {


    public static void main(String[] args) {
//        int[] nums = {2, 7, 11, 15, 18, 4, 12, 1, 0};
        int[] nums = {17,17,3,1,1,3};
        int target = 6;
        int[] res = twoSum2(nums, target);
        System.out.println(Arrays.toString(res));
    }


    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexValue = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indexValue.put(i, nums[i]);
        }

//        Map<Integer, Integer> indexValue = IntStream.range(0, nums.length - 1)
//            .boxed()
//            .collect(Collectors.toMap(i -> nums[i], i -> i));

        Arrays.sort(nums);
        int i1 = 0;
        int i2 = nums.length - 1;
        while (i1 < i2) {
            int sum = nums[i1] + nums[i2];
            if (sum == target) {
                return new int[]{popValueByKey(indexValue, nums[i1]), popValueByKey(indexValue, nums[i2])};
            }
            if (sum > target) i2--;
            if (sum < target) i1++;
        }
        return null;
    }

    static int popValueByKey(Map<Integer, Integer> map, Integer val) {
        for (Map.Entry<Integer, Integer> el : map.entrySet()) {
            if (el.getValue().equals(val)) {
                Object key = el.getKey();
                map.remove(key);
                return (int) key;
            }
        }
        return 0;
    }

    //1
    static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


}
