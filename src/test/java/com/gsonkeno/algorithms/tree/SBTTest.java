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

    @Test
    /** 中序遍历**/
    public void testMidOrderTravel(){
        bst.midOrderTravel();
    }

    @Test
    /** 中序遍历(非递归)**/
    public void testMidOrderTravelByStack(){
        bst.midOrderTravelByStack();
    }

    @Test
    /**测试先序遍历**/
    public void testPreOrderTravel(){
        bst.preOrderTravel();
    }

    @Test
    /**测试先序遍历(非递归，利用栈)**/
    public void testPreOrderTravelByStack(){
        bst.preOrderTravelByStack();
    }

    @Test
    /**测试后序遍历(递归)**/
    public void testPosOrderTravel(){
        bst.posOrderTravel();
    }

    @Test
    /**测试后序遍历(非递归)**/
    public void testPosOrderTravelByStack(){
        bst.posOrderTravelByStack();
    }

    @Test
    /**测试获取树的深度**/
    public void testGetDepth(){
        System.out.println(bst.getDepth());
    }

    @Test
    /**测试获取树的宽度**/
    public void testGetWidth(){
        System.out.println(bst.getWidth());
    }
}
