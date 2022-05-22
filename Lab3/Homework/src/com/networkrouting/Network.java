package com.networkrouting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Network {
    private List<Node> nodes = new ArrayList<>();
    private List<Node> identifiableNodes = new ArrayList<>();

    public Network(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void print() {
        for (Node node : nodes) {
            System.out.println(node);
        }
    }

    public void printIdentifiableNodes()
    {
        int count=0;
        for(Node node : nodes)
            if(node instanceof Computer || node instanceof Router) {
                count++;
                identifiableNodes.add(node);
            }
        identifiableNodes.sort((Node n1, Node n2) -> n1.getAddress().compareTo(n2.getAddress()));
        System.out.println(identifiableNodes);
    }

    @Override
    public String toString() {
        return "Network{" +
                "nodes=" + nodes +
                '}';
    }

}
