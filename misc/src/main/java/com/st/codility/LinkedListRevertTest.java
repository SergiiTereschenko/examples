package com.st.codility;

import org.junit.Test;

public class LinkedListRevertTest {


    @Test
    public void revertListWithOneLinkedNodes() {
        OneLinkedNode node4 = new OneLinkedNode("4", null);
        OneLinkedNode node3 = new OneLinkedNode("3", node4);
        OneLinkedNode node2 = new OneLinkedNode("2", node3);
        OneLinkedNode head = new OneLinkedNode("1", node2);

        System.out.println(head);
        //1
//        OneLinkedNode currentOld = head;
//        OneLinkedNode nextOld = head.next;
//        head.next = null;
//        while(true) {
//            if (nextOld == null) {
//                break;
//            }
//            OneLinkedNode currentNext = nextOld;
//            currentNext.next = currentOld;
//            currentOld = currentNext;
//            nextOld = currentOld.next;
//        }
//        System.out.println(currentOld);

        //2
//        OneLinkedNode reversedPart = null;
//        OneLinkedNode current = head;
//        while (current != null) {
//            OneLinkedNode next = current.next;
//            current.next = reversedPart;
//            reversedPart = current;
//            current = next;
//        }
//
//        System.out.println(reversedPart);

        //3
        OneLinkedNode currentNode = head;
        OneLinkedNode previousNode = null;
        OneLinkedNode nextNode = null;

        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        System.out.println(previousNode);
    }

    static class OneLinkedNode {
        public String value;
        public OneLinkedNode next;

        public OneLinkedNode(String value, OneLinkedNode next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            String result = value;
            if (next != null) {
                result = result + ", " + next.toString();
            }
            return result;
        }
    }


}
