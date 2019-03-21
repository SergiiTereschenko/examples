package com.st.codility;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class NodeWithChildrenTest {


    @Test
    public void revertListWithOneLinkedNodes() {
        NodeWithChildren node6 = new NodeWithChildren("6", null);
        NodeWithChildren node5 = new NodeWithChildren("5", null);
        NodeWithChildren node4 = new NodeWithChildren("4", null);
        NodeWithChildren node3 = new NodeWithChildren("3", Arrays.asList(node6, node5));
        NodeWithChildren node2 = new NodeWithChildren("2", Arrays.asList(node4));
        NodeWithChildren head = new NodeWithChildren("head", Arrays.asList(node2, node3));

        System.out.println(head);

        printNodeValues(head);

    }

    private void printNodeValues(NodeWithChildren node) {
        if (node != null) {
            System.out.println(node.value);
            if (node.children != null) {
                node.children.forEach(this::printNodeValues);
            }
        }
    }

    static class NodeWithChildren {
        public String value;
        public List<NodeWithChildren> children;

        public NodeWithChildren(String value, List<NodeWithChildren> children) {
            this.value = value;
            this.children = children;
        }

//        @Override
//        public String toString() {
//            String result = value;
//            if (next != null) {
//                result = result + ", " + next.toString();
//            }
//            return result;
//        }
    }


}
