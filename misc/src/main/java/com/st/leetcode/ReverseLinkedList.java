package com.st.leetcode;

public class ReverseLinkedList {

    // Input:  1->2->3->NULL
    // Output: 3->2->1->NULL


    public static void main(String[] args) {
        ListNode n5 = new ListNode(5, null);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode reversedList = reverseList_3(n1);
    }

    public static ListNode reverseList_1(ListNode head) {
        if (head != null && head.next != null) {
            ListNode last = reverseList_1(head.next);
            ListNode newNode = head.next;
            newNode.next = head;
            head.next = null;
            return last;
        }
        return head;
    }

    public static ListNode reverseList_2(ListNode head) {
        if(head == null) return head;
        ListNode prev = null;
        ListNode current = head;
        ListNode next = current.next;

        while (next != null) {
            ListNode nextNext = next.next;
            next.next = current;
            current.next = prev;
            prev = current;
            current = next;
            next = nextNext;
        }
        return current;
    }

    static ListNode reverseList_3(ListNode head) {
        ListNode cur = null;
        ListNode next = head;

        while (next != null) {
            ListNode t = next.next;
            next.next = cur;
            cur = next;
            next = t;
        }

        return cur;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }
}

