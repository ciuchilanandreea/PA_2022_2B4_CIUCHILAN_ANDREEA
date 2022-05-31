package Implementations;

public class NodeStatistics {
   private Integer nodeCost;
  private  Float nodeSuccessProbability;

    public NodeStatistics(Integer nodeCost) {
        this.nodeCost = nodeCost;
        this.nodeSuccessProbability = 1F;

    }

    public NodeStatistics(Integer nodeCost, Float nodeSuccessProbability) {
        this.nodeCost = nodeCost;
        if(nodeSuccessProbability >=0F && nodeSuccessProbability <=1F)
            this.nodeSuccessProbability = nodeSuccessProbability;
        else
        {
            System.out.println("probability got invalid float: " + nodeSuccessProbability);
            this.nodeSuccessProbability = 1F;}
    }

    public Integer getNodeCost() {
        return nodeCost;
    }

    public void setNodeCost(Integer nodeCost) {
        this.nodeCost = nodeCost;
    }

    public Float getNodeSuccessProbability() {
        return nodeSuccessProbability;
    }

    public void setNodeSuccessProbability(Float nodeSuccessProbability) {
        if(nodeSuccessProbability >=0 && nodeSuccessProbability <=1)
            this.nodeSuccessProbability = nodeSuccessProbability;
        else
        {
            System.out.println("Probability got invalid float: " + nodeSuccessProbability);
            this.nodeSuccessProbability = 1F;}
    }

    @Override
    public String toString() {
     StringBuilder temp = new StringBuilder();
     temp.append("Cost: ");
     temp.append(this.nodeCost);
     temp.append(", Probability:");
     temp.append(this.nodeSuccessProbability);
     temp.append("\n");


     return temp.toString();
    }
}