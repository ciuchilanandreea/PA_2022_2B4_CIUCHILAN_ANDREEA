package com.networkrouting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NetworkRouting {
    public static void main(String args[])
    {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Computer("calculator", 5, "127.0.0.1", 512));
        nodes.add(new Switch("switch1", 10));
        nodes.add(new Router("router1", 10, "127.0.0.1"));

        Network ntk = new Network(nodes);

        ntk.print();
    }


}
