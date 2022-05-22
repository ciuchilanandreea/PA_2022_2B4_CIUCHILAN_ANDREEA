package com.networkrouting;

import java.util.HashMap;
import java.util.Map;

public abstract class Node implements Comparable <Node>{
    private String name;
    private Map<Node, Integer> cost = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void setCost(Node node, int value) {
        cost.put(node, value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Node, Integer> getCost() {
        return cost;
    }

    public abstract String getAddress();

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.name.compareTo(o.name);
        }
}

