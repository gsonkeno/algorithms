package com.gsonkeno.algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数字和
 * https://leetcode-cn.com/problems/3sum/
 * @author gaosong
 * @since 2019-03-02
 */
public class ThreeNumSum {
    public List<List<Integer>> threeSum(int[] nums) {
        //数组先排序
        Arrays.sort(nums);
        List<List<Integer>> ls = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {  // 跳过可能重复的答案

                //第一次, i =0, l=1, r=5, nums[0]=-4, sum=4
                // nums[i]作为三个数的第一个数, nums[l]作为满足条件的第二个数
                // nums[r]作为满足条件的第三个数, 则需要nums[l] + nums[r] = 0-nums[i]
                // 那如何找后面的两个数呢？
                // 第二个数初始化为第一个数的后面一个数，第三个数初始化为数组的最后一个数
                // 如果nums[l] + nums[r] < sum，则说明第二数nums[l]小了，l需要往后移动一位
                // 如果nums[l] + nums[r] > sum，则说明第三数nums[r]大了，r需要往前移动一位
                int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];

                while (l < r) {
                    if (nums[l] + nums[r] == sum) {
                        ls.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    } else if (nums[l] + nums[r] < sum) {
                        while (l < r && nums[l] == nums[l + 1]) l++;   // 跳过重复值
                        l++;
                    } else {
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        r--;
                    }
                }
            }
        }
        return ls;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = new ThreeNumSum().threeSum(nums);
        System.out.println(lists);
    }

}
