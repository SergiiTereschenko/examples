package com.st.leetcode;

public class AddTwoNumbersLinkedList {

    public static void main(String[] args) {
//        ListNode n13 = new ListNode(3);
//        ListNode n14 = new ListNode(4);
//        n14.next = n13;
//        ListNode n1 = new ListNode(2);
//        n1.next = n14;
//
//        ListNode n24 = new ListNode(4);
//        ListNode n26 = new ListNode(6);
//        n26.next = n24;
//        ListNode n2 = new ListNode(5);
//        n2.next = n26;

        ListNode n1 = new ListNode(5);
        ListNode n2 = new ListNode(5);

        ListNode result = addTwoNumbers(n1, n2);
        System.out.println(result);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return null;

        ListNode l3 = new ListNode(0);
        add(l1, l2, l3, 0);
        return l3;
    }

    static void add(ListNode l1, ListNode l2, ListNode l3, int carry) {
        if(l1 == null && l2 == null && carry == 0) return;

        int v1 = (l1 == null) ? 0 : l1.val;
        int v2 = (l2 == null) ? 0 : l2.val;

        int newVal = v1 + v2 + carry;
        carry = newVal / 10;
        newVal = newVal % 10;
        l3.val = newVal;

        ListNode next1 = l1 != null ? l1.next : null;
        ListNode next2 = l2 != null ? l2.next : null;

        if(next1 != null || next2 != null || carry > 0) {
            l3.next = new ListNode(0);
            add(next1, next2, l3.next, carry);
        }
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
}

