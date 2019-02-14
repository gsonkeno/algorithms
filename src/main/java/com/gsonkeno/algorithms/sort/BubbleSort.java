package com.gsonkeno.algorithms.sort;

/**
 * 冒泡排序
 * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。
 * 如果不满足就让它俩互换。一次冒泡会让至少一个元素移动到它应该在的位置，重复 n 次，就完成了 n 个数据的排序工作。
 *
 * 时间复杂度O(n^2)   空间复杂度O(1)  稳定排序
 * @author gaosong
 * @since 2019-02-15
 */
public class BubbleSort {

    public static  void bubbleSort(int[] nums){
        int len = nums.length;

        //每次循环将最小值推到最前面
        //第一次循环将len个元素中的最小值排到了首位
        //第二次循环经len-1个元素中的最小值排到了len-1元素中的首位，总第二位
        //依次类推
        for (int i = 0; i <len; i++) {
            boolean swap = false;
            for (int j = len-1; j > i ; j--) {
                if (nums[j] < nums[j-1]){
                    int temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                    swap = true; //此次循环仍是乱序
                }
            }

            if (!swap) break;
        }
    }
}
