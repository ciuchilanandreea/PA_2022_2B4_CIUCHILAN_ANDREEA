package com.networkrouting;

public class Computer extends Node implements Identifiable, Storage {
    private String ipAdress;
    private int storageCapacity;

    public Computer(String name, int cost, String ipAdress, int storageCapacity) {
        super(name, cost);
        this.ipAdress = ipAdress;
        this.storageCapacity = storageCapacity;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    @Override
    public int getCapacityStorage() {
        return storageCapacity;
    }

    @Override
    public String getAdress() {
        return ipAdress;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "ipAdress='" + ipAdress + '\'' +
                ", storageCapacity=" + storageCapacity +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }


}
