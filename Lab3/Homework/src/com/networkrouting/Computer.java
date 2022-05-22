package com.networkrouting;

import java.util.Map;

public class Computer extends Node implements Identifiable, Storage {
    private String Address;
    private int storageCapacity;

    public Computer(String name, String address, int storageCapacity) {
        super(name);
        Address = address;
        this.storageCapacity = storageCapacity;
    }

    public void setAddress(String ipAdress) {
        this.Address = ipAdress;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    @Override
    public String getAddress() {
        return Address;
    }

    @Override
    public int getCapacityStorage() {
        return storageCapacity;
    }

    @Override
    public int storage(String type, int storage) {
        return Storage.super.storage(type, storage);
    }

    @Override
    public String toString() {
        return "Computer{" + "name= '" + getName() + '\'' +
                ", ipAdress= '" + Address + '\'' +
                ", storageCapacity= " + storageCapacity +
                '}';
    }

}
