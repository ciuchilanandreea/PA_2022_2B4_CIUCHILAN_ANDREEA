package com.networkrouting;

import java.util.Map;

public class Router extends Node implements Identifiable{
    private String Address;

    public Router(String name, String address) {
        super(name);
        Address = address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String getAddress() {
        return Address;
    }

    @Override
    public String toString() {
        return "Router{" + "name= '" + getName() +
                ", Address= '" + Address + '\'' +
                '}';
    }
}
