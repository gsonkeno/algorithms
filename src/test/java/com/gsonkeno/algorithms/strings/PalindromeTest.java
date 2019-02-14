package com.gsonkeno.algorithms.strings;

import com.gsonkeno.algorithms.strings.Palindrome.ListNode;
import org.junit.Test;

public class PalindromeTest {

    /**
     * 奇数个元素判断是否是回文字符串
     */
    @Test
    public void test1(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        Palindrome solution = new Palindrome();
        System.out.println(solution.isPalindrome(node1));
    }

    /**
     * \偶数个元素判断是否是回文字符串
     */
    @Test
    public void test2(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4= new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        Palindrome solution = new Palindrome();
        System.out.println(solution.isPalindrome(node1));
    }
}
