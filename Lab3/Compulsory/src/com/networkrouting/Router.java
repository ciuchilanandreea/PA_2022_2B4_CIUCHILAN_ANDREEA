package com.networkrouting;

public class Router extends Node implements Identifiable{
    private String ipAdress;

    public Router(String name, int cost, String ipAdress) {
        super(name, cost);
        this.ipAdress = ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    @Override
    public String toString() {
        return "Router{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", ipAdress='" + ipAdress + '\'' +
                '}';
    }

    @Override
    public String getAdress() {
        return null;
    }
}
