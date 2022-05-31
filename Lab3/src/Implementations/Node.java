package Implementations;

import java.util.HashMap;
import java.util.Map;

public abstract class Node implements Comparable<Node>{
    private String name;
    private Map<Node,NodeStatistics> cost = new HashMap<>();

    public void setCost(Node node, int value)
    {
        cost.put(node,new NodeStatistics(value,1F));
    }

    public void setCost(Node node, int value,float probability)
    {
        cost.put(node,new NodeStatistics(value,probability));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoutes()
    {
        StringBuilder tempString = new StringBuilder("\n");

        for(Map.Entry<Node,NodeStatistics> route : cost.entrySet())
        {
            tempString.append(route.getKey().name + " -> " +  route.getValue() + "\n");
        }
        return tempString.toString();
    }

    public Map<Node,NodeStatistics> getCosts()
    {
        return this.cost;
    }

    @Override
    public int compareTo(Node other) {
        if(this.name == null)
            return -1;
        if(other.name == null)
            return 1;
        return this.name.compareTo(other.name);
    }





}


