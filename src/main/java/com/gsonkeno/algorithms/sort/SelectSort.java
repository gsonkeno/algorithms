package com.gsonkeno.algorithms.sort;
/**
 * 选择排序
 * 选择排序算法的实现思路有点类似插入排序，也分已排序区间和未排序区间。
 * 但是选择排序每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾。
 *
 * 时间复杂度O(n^2)   空间复杂度O(1)  不稳定排序
 * 选择排序是不稳定的排序方法（比如序列[5， 5， 3]第一次就将第一个[5]与[3]交换，导致第一个5挪动到第二个5后面）。
 * @author gaosong
 * @since 2019/2/15
 */
public class SelectSort {
    public static  int[]  selectSort(int[] nums){

//        for (int i = 0; i < nums.length-1; i++) {
//            for (int j = i+1; j <nums.length ; j++) {
//                if (nums[i] > nums[j]){
//                    float temp = nums[j];
//                    nums[j] = nums[i];
//                    nums[i] = temp;
//                }
//            }
//
//        }
        //上面是不良示例，此示例下每次两元素比较时，若前者比后者大，则进行交换；
        //优良的设计是记录最小值元素的下标，若有其他值比最小值更小，则更换最小值的下标
        for (int i = 0; i < nums.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j <nums.length ; j++) {
                if (nums[j] < nums[minIndex]) minIndex = j;
            }

            //即此次循环的第一个元素不是最小值
            if (minIndex != i){
                int temp = nums[minIndex];
                nums[minIndex] = nums[i];
                nums[i] = temp;
            }
        }
        return  nums;
    }
}
