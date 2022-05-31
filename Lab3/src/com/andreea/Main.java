package com.andreea;

import Implementations.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

    Node v1 = new Computer("Computer A","192.168.0.1",4);
    Node v2 = new Router("Router A", "0.0.0.123");
    Node v3 =new Switcher("Switch A");
    Node v4 =new Switcher("Switch B");
    Node v5 = new Router("Router B","1.1.1.1.1");
    Node v6 = new Computer("computerB", "2.2.2.2.2", 256);
    v1.setCost(v2,10,0.5F);
    v1.setCost(v3,50,0.6F);
    v2.setCost(v3,20,0.75F);
    v2.setCost(v4,20,1F);
    v2.setCost(v5,20,0.75F);
    v3.setCost(v4,10,0.5F);
    v4.setCost(v5,30,0.75F);
    v4.setCost(v6,10,0.7F);
    v5.setCost(v6,20,1F);
        Network n1 = new Network(List.of(v1,v2,v3,v4,v5,v6));
        System.out.println(n1);
        n1.printIdentifiable();
        System.out.println("\n");
        n1.printIdentifiableRoutes();
        System.out.println("\n");
        n1.printSuccessRateFromAtoB(v1,v6);




    }
}
