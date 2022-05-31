package Implementations;

import Identifiable.java.Identifiable;
import NetworkAlgorithm.DijkstraNetworkAlgorithm;
import NetworkAlgorithm.SuccessRateNetworkAlgorithm;


import java.util.*;

public class Network {
    private List<Node> nodes;
    //… constructors, getters, setters


    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Network() {
        this.nodes = new ArrayList<>();
    }

    public Network(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public boolean removeNode(Node other) {
        return nodes.remove(other);
    }

    public void printIdentifiable() {
        List<Node> identifiables = new ArrayList<>();
        for (Node iterable : nodes) {
            if (iterable instanceof Identifiable)
                identifiables.add(iterable);
        }
        identifiables.sort(Node::compareTo);
        System.out.println("Identifiable:\n");
        System.out.println(identifiables);

    }

    private List<Node> getIdentifiable()
    {
        List<Node> identifiables = new ArrayList<>();
        for (Node iterable : nodes) {
            if (iterable instanceof Identifiable)
                identifiables.add(iterable);
        }
        identifiables.sort(Node::compareTo);
        return identifiables;
    }
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (Node tempNode : nodes) {
            temp.append("-> ");
            temp.append(tempNode.toString());
            temp.append("\n\n");
        }
        return temp.toString();
    }

    public void printIdentifiableRoutes() {
       DijkstraNetworkAlgorithm solve = new DijkstraNetworkAlgorithm(nodes);
       solve.solveAllNodes();
       solve.listIdentifiableSolutions();
    }
    public void printSuccessRateFromAtoB(Node A, Node B)
    {
        if(this.nodes.contains(A) && this.nodes.contains(B))
        {
            float response = SuccessRateNetworkAlgorithm.computeSuccessRate(this.nodes,A,B);
         StringBuilder temp = new StringBuilder();
         temp.append("From node:\n");
         temp.append(A);
         temp.append(" \n To \n");
         temp.append(B);
         temp.append("\n is ");
         temp.append(response*100+ "%");
            System.out.println(temp);
        }
        else
            System.out.println("One of this nodes not found in network:" + A + B);
    }





}
