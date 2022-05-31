package Implementations;
import Identifiable.java.Identifiable;
import Storage.java.Storage;


public class Computer extends Node implements Identifiable, Storage {
    private String address;
    private int storageCapacity;

    public Computer(String name, String address, int storageCapacity)
    {
        this.setName(name);
        this.address = address;
        this.storageCapacity = storageCapacity;
    }


    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }
    @Override
    public int compareTo(Node other) {
        if(this.getAddress() == null)
            return -1;

        if(!(other instanceof Computer))
            return -1;

        Computer castedObject = (Computer) other;

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
        temp.append("Identified by: " + this.getAddress() + ",");
        temp.append("Storage: " + this.storageCapacity + " GB)");

        return temp.toString();
    }


}