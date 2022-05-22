package com.networkrouting;

import java.util.*;

public class NetworkRouting {
    public static void main(String args[])
    {
        int index;
        List<Node> nodes = new ArrayList<>();
        Integer[][] shortestPath;
        nodes.add(new Computer("calculator A", "127.0.0.2", 512));
        nodes.add(new Router("router A", "127.0.0.3"));
        nodes.add(new Switch("Switch A"));
        nodes.add(new Switch("Switch B"));
        nodes.add(new Router("router b", "127.0.0.1"));
        nodes.add(new Computer("Calculator b", "127.0.0.4", 128));


        //v1-v2/v1-v3
        index = 0;
        nodes.get(index).setCost(nodes.get(1), 10);
        nodes.get(index).setCost(nodes.get(2), 50);

        //v2-v3/v2/-v4/v2-v5
        index = 1;
        nodes.get(index).setCost(nodes.get(2), 20);
        nodes.get(index).setCost(nodes.get(3), 20);
        nodes.get(index).setCost(nodes.get(4), 20);

        //v3-v4
        index = 2;
        nodes.get(index).setCost(nodes.get(3),10);

        //v4-v5/v4-v6
        index = 3;
        nodes.get(index).setCost(nodes.get(4),30);
        nodes.get(index).setCost(nodes.get(5),10);

        //v5-v6
        index = 4;
        nodes.get(index).setCost(nodes.get(5),20);

        Network network = new Network(nodes);

        System.out.println("Network nodes: ");
        network.print();
        System.out.println("\nIdentifiable nodes: ");
        network.printIdentifiableNodes();

        shortestPath = new Integer[nodes.size()][nodes.size()];
        shortestPath = computeShortesPath(nodes.size(), network.getNodes());
        printShortestPath(0, 4, shortestPath);
    }

    private static void printShortestPath(Integer node, Integer node1, Integer[][] shortestPath) {
        int INF = 1000000;
        if(shortestPath[node][node1] >= INF)
            System.out.println("Nu exsita comunicare intre cele 2 noduri");
        else System.out.println("Cel mai mic timp de transmitere a datelor intre cele 2 noduri este: " + shortestPath[node][node1]);
    }

    private static Integer[][] computeShortesPath(int size, List<Node> nodes) {
        Integer[][] adjacencyMatrix = new Integer[size][size];
        Map<Node, Integer> hash = new HashMap();
        Node newNode;
        int columnIndex;
        int rowIndex;

        initializeAdjacencyMatrix(adjacencyMatrix, size);
        for(Node node : nodes) {
            hash = node.getCost();
            rowIndex = nodes.indexOf(node);
            for(Map.Entry<Node, Integer> path : hash.entrySet()){
                newNode = path.getKey();
                columnIndex = nodes.indexOf(newNode);
                adjacencyMatrix[rowIndex][columnIndex] = path.getValue();
                //adjacencyMatrix[columnIndex][rowIndex] = path.getValue();
            }
        }

        FloydWarshall(adjacencyMatrix, size);
        //printMatrix(adjacencyMatrix, size);
        return adjacencyMatrix;
    }

    private static void FloydWarshall(Integer[][] adjacencyMatrix, int size) {
        int k, i, j;
        int INF = 1000000;
        for(k = 0; k < size; k++)
            for(i = 0; i < size; i++)
                for(j = 0; j < size; j++)
                    if(adjacencyMatrix[i][j] > (adjacencyMatrix[i][k] + adjacencyMatrix[k][j]) &&
                            adjacencyMatrix[i][k] != INF && adjacencyMatrix[k][j] != INF)
                        adjacencyMatrix[i][j] = adjacencyMatrix[i][k] + adjacencyMatrix[k][j];
    }

    private static void initializeAdjacencyMatrix(Integer[][] adjacencyMatrix, int size){
        int indexi, indexj;
        final int INF = 1000000;
        for(indexi = 0; indexi < size; indexi++)
            for(indexj = 0; indexj < size; indexj++)
                adjacencyMatrix[indexi][indexj] = INF;
    }

    private static void printMatrix(Integer[][] adjacencyMatrix, int size){
        int indexi, indexj;

        for(indexi = 0; indexi < size; indexi++)
        {
            for(indexj = 0; indexj < size; indexj++)
                System.out.print(adjacencyMatrix[indexi][indexj] + " ");
            System.out.println();
        }

    }

}
