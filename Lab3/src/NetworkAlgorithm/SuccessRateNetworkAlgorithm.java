package NetworkAlgorithm;

import Implementations.Node;
import Implementations.NodeStatistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuccessRateNetworkAlgorithm {
    public static float computeSuccessRate(List<Node> nodes,Node startNode, Node endNode)
    {

        Map<Node, Boolean> processedNodes = new HashMap<>();
        Map<Node,Float> nodesSuccessPercentage = new HashMap<>(); // if a node is not in this hashmap, it's percentage is considered 0
        /*
        -get the currentNode (first is the startNode);
        -mark it as processed (add in processedNodes);
        -iterate through all neighbours of current node:
            -if the neighbour is unvisited (it doesn't exist in nodesSuccessPercentage): add them in nodesSuccessPercentage with the cost of currentNode * the percentage to go from currentNode to the neighbour
            -if the neighbour is visited (it does exist in nodesCost): verify if the percentage of currentNode * the percentage to go from currentNode to the neighbour > percentage of neighbour in nodesSuccessPercentage
        -get the next node; do this for n-1 times(for every node in network)


        at final, in nodesSuccessPercentage will be all costs for the paths startNode-neighbour (if aren't the percentage is 0)
         */

        Node currentNode = startNode;
        processedNodes.put(startNode,true);
        nodesSuccessPercentage.put(startNode,1F);
        int i=0;
        for(i=0;i<nodes.size();i++) // do all below n-1 times (for every node in network)
        {
            processedNodes.put(currentNode,true);
            float percentageFromStartToCurrentNode = nodesSuccessPercentage.get(currentNode);
            for(Map.Entry<Node, NodeStatistics> currentNodeNeighbour : currentNode.getCosts().entrySet()) // Iterate through all neighbours of current node
            {


                if(!nodesSuccessPercentage.containsKey(currentNodeNeighbour.getKey())) // if there is no path from the startNode to currentNodeNeighbour then add it with the percentage of currentNode * currentNode to neighbour
                {
                    float percentageFromCurrentNodeToNeighbour = currentNodeNeighbour.getValue().getNodeSuccessProbability();
                    nodesSuccessPercentage.put(currentNodeNeighbour.getKey(),percentageFromStartToCurrentNode * percentageFromCurrentNodeToNeighbour);
                } // add in nodesCost  the currentNodeNeighbour cost (from currentNode to neighbourNode) * currentNodeCost (from startNode to currentNode)
                else //verify if oldPercentage is lower than the possible one; if so, replace it
                {
                    float possibleHigherPercentage =  currentNodeNeighbour.getValue().getNodeSuccessProbability() * nodesSuccessPercentage.get(currentNode);
                    float oldPercentage = nodesSuccessPercentage.get(currentNodeNeighbour.getKey());
                    if(possibleHigherPercentage > oldPercentage)
                        nodesSuccessPercentage.replace(currentNodeNeighbour.getKey(),possibleHigherPercentage);
                }
            }
            //nodesSuccessPercentage - the ones available
            //processedNodes - the ones impossible to process

            if(processedNodes.size() != nodes.size()) {
                float highestPercentage = Float.MIN_VALUE;
                for (Map.Entry<Node, Float> possibleNextNode : nodesSuccessPercentage.entrySet()) {
                    boolean isPercentageHigher = possibleNextNode.getValue() > highestPercentage;
                    boolean isAlreadyProcessed = processedNodes.containsKey(possibleNextNode.getKey());
                    if (isPercentageHigher && !isAlreadyProcessed) {
                        highestPercentage = possibleNextNode.getValue();
                        currentNode = possibleNextNode.getKey();
                    }
                }
            }
            else
                break;

        }

        return nodesSuccessPercentage.get(endNode);
    }
}
