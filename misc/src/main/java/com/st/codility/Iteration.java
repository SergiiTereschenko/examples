package com.st.codility;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Iteration {

    @Test
    public void printAsterisks1() {
        int n = 9;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void printAsterisks2() {
        int n = 9;
        for (int i = n; i >= 1; i = i - 2) {
            int gap = n - i;
            System.out.print(StringUtils.repeat(" ", gap));
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void binaryGap() {
        int n = 1041;
        String binary = Integer.toBinaryString(n);
//        String binary = "001110001010000100000";
        char[] chars = binary.toCharArray();
        boolean gapsBegin = false;
        int maxZeros = 0;
        int currentZeros = 0;
        for (char cr : chars) {
            int chrInt = cr - '0';
            if (chrInt == 1) {
                if (gapsBegin) {
                    maxZeros = maxZeros > currentZeros ? maxZeros : currentZeros;
                    currentZeros = 0;
                }
                gapsBegin = true;
            } else if (gapsBegin) {
                currentZeros++;
            }
        }

        System.out.println("Maximum binary gap is: " + maxZeros);
    }

    @Test
    public void oddOccurrencesInArray() {
        int[] arr = {1, 2, 3, 1, 5, 2, 3, 5, 6, 500000, 6, 125, 500000};
        Set<Integer> set = new HashSet<Integer>();

        for (int el : arr) {
            if (!set.contains(el)) {
                set.add(el);
            } else {
                set.remove(el);
            }
        }
        if (!set.isEmpty()) System.out.println(" Odd element: " + set.iterator().next());
    }

    @Test
    public void cyclicRotation() {
        int[] arr = {1, 2, 3, 4, 5};
        int times = 3;
        int lastElement;
        int formerElem;
        int shifterElem;
        for (int num = 1; num <= times; num++) {
            lastElement = arr[arr.length - 1];
            shifterElem = arr[0];
            for (int i = 1; i < arr.length; i++) {
                formerElem = arr[i];
                arr[i] = shifterElem;
                shifterElem = formerElem;
            }
            arr[0] = lastElement;
        }
        System.out.println(Arrays.toString(arr));
    }

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

    @Test
    public void stringSortToInt() {
        String[] arr = {"3","7","6","1","2","3","9","4"};
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        String collect = Arrays.stream(arr)
                .map(Objects::toString).collect(Collectors.joining());
        System.out.println(Integer.parseInt(collect));

    }

//    static void rotate() {
//        int x = arr[arr.length - 1], i;
//        for (i = arr.length - 1; i > 0; i--)
//            arr[i] = arr[i - 1];
//        arr[0] = x;
//    }


    //-----------------------------
    @Test
    public void duplicatesArray() {
        int[] arr = {1, 2, 3, 1, 2, 3};
        int K = 3;
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<Integer>());
                map.get(key).add(i);
            } else {
                List<Integer> positions = map.get(key);
                for (Integer pos : positions) {
                    if (Math.abs(pos - i) <= K) {
                        System.out.printf("Bingo! There is element %d , in position %d and %d, \n", key, i, pos);
                        break;
                    }
                }
                positions.add(i);
            }
        }
        System.out.println("End.");
    }

}
