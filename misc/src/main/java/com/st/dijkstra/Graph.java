package com.st.dijkstra;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }


    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

//        while (unsettledNodes.size() != 0) {
//            Node currentNode = getLowestDistanceNode(unsettledNodes);
//            unsettledNodes.remove(currentNode);
//            for (Entry < Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
//                Node adjacentNode = adjacencyPair.getKey();
//                Integer edgeWeight = adjacencyPair.getValue();
//                if (!settledNodes.contains(adjacentNode)) {
//                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
//                    unsettledNodes.add(adjacentNode);
//                }
//            }
//            settledNodes.add(currentNode);
//        }
        return graph;
    }


}
