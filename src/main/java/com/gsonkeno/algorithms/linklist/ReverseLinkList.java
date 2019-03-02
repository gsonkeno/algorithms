package com.gsonkeno.algorithms.linklist;

/**
 * 链表反转
 * 我们需要增加一个指向前一个节点的指针pre，用于存储每一个节点的前一个节点。
 * 此外，还需要定义一个保存当前节点的指针cur，以及下一个节点的next。定义好这三个指针后，遍历单链表，
 * 将当前节点的指针域指向前一个节点，之后将定义三个指针往后移动，直至遍历到最后一个节点停止。
 * https://blog.csdn.net/lwkrsa/article/details/82015364
 * @author gaosong
 * @since 2019-03-01
 */
public class ReverseLinkList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        //前一个节点指针
        ListNode preNode = null;
        //当前节点指针
        ListNode currNode = head;
        //下一个节点指针
        ListNode nextNode = null;

        while (currNode != null) {
            nextNode = currNode.next; //原链表的下一个节点
            currNode.next = preNode; //将当前节点next域指向前一个节点
            preNode = currNode; //preNode 指针向后移动
            currNode = nextNode; //curNode指针向后移动
        }

        return preNode;
    }

    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }

}



