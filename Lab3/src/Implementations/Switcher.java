package Implementations;
import Identifiable.java.Identifiable;
import Storage.java.Storage;


public class Switcher extends Node {
    private String address;
    private int storageCapacity;

    public Switcher(String name)
    {
        this.setName(name);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public void printRoutes(){
        System.out.println("\n");
        System.out.println(this.getRoutes());
    }

    @Override
    public String toString() {
       return this.getName();
    }
}
