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
        System.out.println(bst.select(2));
        System.out.println(bst.select(3));
    }

    @Test
    public void testRank(){
        System.out.println(bst.rank("D"));
        System.out.println(bst.rank("E"));
        System.out.println(bst.rank("F"));
    }

    @Test
    /**二叉查找树的键范围查找**/
    public void testKeys(){
        System.out.println(bst.keys());
    }
}
