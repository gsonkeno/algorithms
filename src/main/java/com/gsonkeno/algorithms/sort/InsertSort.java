package com.gsonkeno.algorithms.sort;
/**
 * 插入排序
 * <p>首先，我们将数组中的数据分为两个区间，<strong>已排序区间</strong>和<strong>未排序区间</strong>。
 * 初始已排序区间只有一个元素，就是数组的第一个元素。插入算法的核心思想是取未排序区间中的元素，
 * 在已排序区间中找到合适的插入位置将其插入，并保证已排序区间数据一直有序。
 *
 * 重复这个过程，直到未排序区间中元素为空，算法结束。</p>
 * @author gaosong
 * @since 2019/2/15
 */
public class InsertSort {

    public static  void insertSort(int[] nums){
        int len = nums.length;

        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[i-1]){
                //temp比全部排好序的前面元素的最后一个值小，则升起
                int temp = nums[i];
                int j = i-1;
                for (; j >=0 && nums[j]>temp ; j--) {
                    nums[j+1] = nums[j];
                }
                nums[j+1] = temp;
            }
        }

    }
}
