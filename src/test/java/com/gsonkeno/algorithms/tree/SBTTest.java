package com.gsonkeno.algorithms.tree;

import org.junit.Before;
import org.junit.Test;

public class SBTTest {

    private  BST<String, Integer> bst;

    @Before
    public void  init(){
        bst = new BST<>();
        bst.put("E",2);
        bst.put("C",1);
        bst.put("G",3);
        bst.put("F",2);
        bst.put("D",1);
//               E
//         C            G
//             D      F

    }

    @Test
    public void testSelect(){
        System.out.println(bst.select(1));
    }
}
