package com.gsonkeno.algorithms.array;
/**
 * 搜索旋转排序数组
 * @author gaosong
 * @since 2019-03-02
 */
public class RotatedSortedArray {
    public int search(int[] nums, int target) {
        //寻找出旋转长度；
        int lo=0,hi=nums.length-1;
        while(lo<hi){
            int mid = (lo+hi)/2;
            if(nums[mid]>nums[hi]){
                lo = mid+1;
                System.out.println("lo为" + lo);
            }
            else{
                hi = mid;  //这里不能有减一操作，因为nums[mid] 有可能是最小值；
                System.out.println("hi为" + hi);
            }
        }
        System.out.println(lo);
        int rot = lo;
        lo=0;
        hi=nums.length-1;
        //对mid也进行旋转来直接使用二分查找
        while(lo<=hi){
            int mid =(lo+hi)/2;
            int realMid = (mid+rot)%nums.length; // 对mid进行逻辑上的旋转
            if(nums[realMid] == target) return realMid;
            else if(nums[realMid]<target)
                lo = mid+1;  //注意最后赋值是mid,而非旋转后的realMId
            else
                hi = mid-1;

        }
        return -1;
    }

    /*
     * https://leetcode.windliang.cc/leetCode-33-Search-in-Rotated-Sorted-Array.html
     */
    public int search1(int[] A, int target) {
        int lo = 0;
        int hi = A.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (A[mid] == target) return mid;

            if (A[lo] <= A[mid]) {
                if (target >= A[lo] && target < A[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (target > A[mid] && target <= A[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return A[lo] == target ? lo : -1;
    }

    public int search2 (int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        //找出最小值的数组下标
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        int bias = start;
//        //找出最大值的数组下标
//        while (start < end) {
//            int mid = Math.round(((float)start + end) / 2);
//            if (nums[mid] < nums[start]) {
//                end = mid - 1;
//            } else {
//                start = mid;
//            }
//
//        }
//        int n = nums.length;
//        int bias = (start + n)  - (n - 1); //得到偏移
        start = 0;
        end = nums.length - 1;
        // start，end可以一直看做是原来有序数组(0,1,2,4,5,6,7)A的下标, mid_change可以认为是原有序数组A的下标为mid的元素
        // 在新数组(4,5,6,7,0,1,2)中的下标
        while (start <= end) {
            int mid = (start + end) / 2;//中间的位置
            int mid_change = (mid + bias) % nums.length;//原来有序数组(假设是 0,1,2,4,5,6,7)的中间的位置对应的参数数组(传入的4,5,6,7,0,1,2)下标
            int value = nums[mid_change];//中间位置的值
            if (target == value) {
                return mid_change;
            }
            if (target < value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2,};
        System.out.println(new RotatedSortedArray().search2(nums, 0));

        nums = new int[]{5, 6, 7, 0, 1, 2, 3};
        System.out.println(new RotatedSortedArray().search2(nums,3));
    }
}
