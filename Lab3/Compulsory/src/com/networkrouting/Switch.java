package com.networkrouting;

public class Switch extends Node{

    public Switch(String name, int cost) {
        super(name, cost);
    }

    @Override
    public String toString() {
        return "Switch{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
