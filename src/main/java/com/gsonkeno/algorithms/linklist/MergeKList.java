package com.gsonkeno.algorithms.linklist;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 合并k个有序链表
 * @author gaosong
 * @since 2019-03-01
 * https://blog.csdn.net/whdAlive/article/details/80467493
 */
public class MergeKList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b)->a.val-b.val);
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null){
                queue.add(lists[i]);
            }
        }
        if(queue.isEmpty()){
            return null;
        }
        ListNode temp = new ListNode(queue.peek().val);
        ListNode head = temp;
        while(!queue.isEmpty()){
            ListNode p = queue.poll();
            temp.next = p;
            temp = temp.next;
            if(p.next!=null){
                queue.offer(p.next);
            }
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(5);

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);

        ListNode node = mergeKLists(new ListNode[]{node1, node2, node3});
    }
}
