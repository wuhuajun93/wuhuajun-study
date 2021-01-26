package com.wuhj.code;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhj
 * @date 2021/1/26 16:38
 */
public class TwoSumDemo {
    
    public static void main(String[] args) {
        
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        //int[] myIndex = twoSum1(nums, target);
    
        int[] myIndex = twoSum2(nums, target);
        for (int a : myIndex){
            System.out.println(a);
        }
    
    
    }
    
    //暴力解法
    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length ; j++) {
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    
    //hash
    public static int[] twoSum2(int[] nums, int target){
    
        Map<Integer, Integer> map = new HashMap<>();
        //map  K值  V下标
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                return new int[]{map.get(temp), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
    
    
}

