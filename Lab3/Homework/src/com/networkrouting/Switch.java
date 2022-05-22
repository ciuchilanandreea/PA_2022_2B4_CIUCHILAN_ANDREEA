package com.networkrouting;

import java.util.Map;

public class Switch extends Node{

    public Switch(String name) {
        super(name);
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String toString() {
        return "Switch{}";
    }
}
