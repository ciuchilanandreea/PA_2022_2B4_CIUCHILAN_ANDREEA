package NetworkAlgorithm;

import Identifiable.java.Identifiable;
import Implementations.Node;
import Implementations.NodeStatistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DijkstraNetworkAlgorithm {

    private List<Node> nodes;



    private List<Map<Node,Integer>> solution; // used to store multiple solutions



   public DijkstraNetworkAlgorithm(List<Node> nodes)
    {
        this.nodes = nodes;
    }


    public Map<Node,Integer> solveForNode(Node startNode)
    {

        Map<Node, Boolean> processedNodes = new HashMap<>();
        Map<Node,Integer> nodesCost = new HashMap<>(); // if a node is not in this hashmap, it's cost is infinite
        /*
        -get the currentNode (first is the startNode);
        -mark it as processed (add in processedNodes);
        -iterate through all neighbours of current node:
            -if the neighbour is unvisited (it doesn't exist in nodesCost): add them in nodesCost with the cost of currentNode + the cost to go from currentNode to the neighbour
            -if the neighbour is visited (it does exist in nodesCost): verify if the cost of currentNode + the cost to go from currentNode to the neighbour < value of neighbour in nodesCost
        -get the next node; do this for n-1 times(for every node in network)


        at final, in nodesCost will be all costs for the paths startNode-neighbour (if aren't the cost is infinite)
         */

        Node currentNode = startNode;
        processedNodes.put(startNode,true);
        nodesCost.put(startNode,0);
        int i=0;
        for(i=0;i<this.nodes.size();i++) // do all below n-1 times (for every node in network)
        {
            processedNodes.put(currentNode,true);
            int costFromStartToCurrentNode = nodesCost.get(currentNode);
            for(Map.Entry<Node, NodeStatistics> currentNodeNeighbour : currentNode.getCosts().entrySet()) // Iterate through all neighbours of current node
            {


                if(!nodesCost.containsKey(currentNodeNeighbour.getKey())) // if there is no path from the startNode to currentNodeNeighbour then add it with the cost of currentNode + currentNode to neighbour
                {
                    int costFromCurrentNodeToNeighbour = currentNodeNeighbour.getValue().getNodeCost();
                    nodesCost.put(currentNodeNeighbour.getKey(),costFromStartToCurrentNode + costFromCurrentNodeToNeighbour);
                } // add in nodesCost  the currentNodeNeighbour cost (from currentNode to neighbourNode) + currentNodeCost (from startNode to currentNode)
                else //verify if oldCost is higher than the possible one
                {
                    int possibleLowerCost =  currentNodeNeighbour.getValue().getNodeCost() + nodesCost.get(currentNode);
                    int oldCost = nodesCost.get(currentNodeNeighbour.getKey());
                    if(possibleLowerCost < oldCost)
                        nodesCost.replace(currentNodeNeighbour.getKey(),possibleLowerCost);
                }
            }
            //nodesCost - the ones available
            //processedNodes - the ones impossible to process
            //get the entry that is minimum in processedNodes, and it is not in nodesCost

            if(processedNodes.size() != nodes.size()) {
                int minimumCost = Integer.MAX_VALUE;
                for (Map.Entry<Node, Integer> possibleNextNode : nodesCost.entrySet()) {
                    boolean isCostLower = possibleNextNode.getValue() < minimumCost;
                    boolean isAlreadyProcessed = processedNodes.containsKey(possibleNextNode.getKey());
                    if (isCostLower && !isAlreadyProcessed) {
                        minimumCost = possibleNextNode.getValue();
                        currentNode = possibleNextNode.getKey();
                    }
                }
            }
            else
                break;

        }

        return nodesCost;
    }

    public List<Map<Node,Integer>> solveAllNodes()
    {
        List<Map<Node,Integer>> toBeReturned = new ArrayList<>();
        for(int i=0;i<nodes.size();i++)
            toBeReturned.add(solveForNode(this.nodes.get(i)));

        solution = toBeReturned;
        return toBeReturned;
    }

    public String listIdentifiableSolutions()
    {
        StringBuilder temp = new StringBuilder();

        if(solution == null)
            System.out.println("Solution not called");
        else
        {
            for(int i=0;i<nodes.size();i++) {
               if(this.nodes.get(i) instanceof Identifiable) {
                    temp.append("\nFor node: ");
                    temp.append(this.nodes.get(i));
                    temp.append(" to:\n{");
                    for (Map.Entry<Node, Integer> iterator : solution.get(i).entrySet())
                        if (iterator.getKey() instanceof Identifiable && iterator.getValue() != 0) {
                            temp.append(iterator.getKey());
                            temp.append("=");
                            temp.append(iterator.getValue());
                            temp.append(",  ");
                        }
                   temp.append("}\n");


                }
            }

        }
        System.out.println(temp);
        return temp.toString();
    }
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();

        if(solution == null)
            return "Solution not called.";
            else
        {
            for(int i=0;i<nodes.size();i++) {
                temp.append("\nFor node: ");
                temp.append(this.nodes.get(i));
                temp.append(" to:\n");
                temp.append(solution.get(i));
            }
        }
        return temp.toString();
    }
}
