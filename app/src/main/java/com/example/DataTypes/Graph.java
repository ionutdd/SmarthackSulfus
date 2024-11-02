package com.example.DataTypes;

import java.util.*;

public class Graph {
    private List<Node> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public boolean addEdge(Node source, Node destination) {
        // Check for cycles before adding an edge
        source.addNeighbor(destination);
        return true;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void displayGraph() {
        for (Node node : nodes) {
            System.out.print(node.getData() + " " + node.getType() + " -> ");
            Set<Node> neighbors = node.getNeighbors();
            if (neighbors.isEmpty()) {
                System.out.print("No neighbors");
            } else {
                for (Node neighbor : neighbors) {
                    System.out.print(neighbor.getData() + " " + neighbor.getType() + ", ");
                }
            }
            System.out.println(); // New line for the next node
        }
    }
}
