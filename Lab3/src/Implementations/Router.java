package Implementations;
import Identifiable.java.Identifiable;
import Storage.java.Storage;


public class Router extends Node implements Identifiable {
    private String address;


   public Router(String name,String address)
    {
        this.setName(name);
        this.address = address;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public int compareTo(Node other) {
        if(this.getAddress() == null)
            return -1;

        if(!(other instanceof Router))
            return -1;

        Router castedObject = (Router) other;

        if(castedObject.getAddress() == null)
            return 1;

       /* boolean haveTheSameAddress = this.getAddress().equals(castedObject.getAddress());
        if(haveTheSameAddress)
            return 0;
        return 1;*/
        return this.getAddress().compareTo(castedObject.getAddress());
    }

    public void printRoutes(){
        System.out.println("\n");
        System.out.println(this.getRoutes());
    }
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        temp.append(this.getName() + "(");
        temp.append("Identified by:" + this.getAddress() + ")");

        return temp.toString();
    }
}
