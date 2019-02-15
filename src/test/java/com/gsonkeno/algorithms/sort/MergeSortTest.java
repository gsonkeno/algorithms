package com.gsonkeno.algorithms.sort;

import org.junit.Test;

public class MergeSortTest {

    @Test
    public void test(){
        int[] a = new int[]{1,8, 6,9, 2,98, 23,28, 78,76,38};
        MergeSort.mergeSort(a, 0, 1);
        for (int i = 0; i < a.length; ++i) {
            System.out.print(a[i] + "　");
        }
    }
}
