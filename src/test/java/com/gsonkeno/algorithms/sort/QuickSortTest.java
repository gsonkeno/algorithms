package com.gsonkeno.algorithms.sort;

import org.junit.Test;

/**
 * 快速排序
 * @author gaosong
 * @since 2019-02-15
 */
public class QuickSortTest {
    @Test
    public void test(){
        int[] a = new int[]{1,8, 6,9, 2,98, 23,28, 78,76,38};
        QuickSort.quickSort(a, 0, a.length -1 );
        for (int i = 0; i < a.length; ++i) {
            System.out.print(a[i] + "　");
        }
    }
}
