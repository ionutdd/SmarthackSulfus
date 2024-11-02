package com.example.DataTypes;

import java.util.*;

public class Node {
    private String data;
    private Set<Node> neighbors; // Directed edges
    private String type;

    public Node(String data, String type) {
        this.data = data;
        this.neighbors = new HashSet<>();
        this.type = type;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public Set<Node> getNeighbors() {
        return neighbors;
    }

    public Object getData() {
        return data;
    }

    public String getType() {
        return type;
    }
}
