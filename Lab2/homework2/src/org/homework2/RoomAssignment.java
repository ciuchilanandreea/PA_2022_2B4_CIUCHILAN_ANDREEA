package org.homework2;

import java.util.Scanner;

public class RoomAssignment {
    public static void main(String args[])
    {
        int index;
        Instance problem;
        int noOfRooms = 4;
        Room[] rooms = new Room[4];
        rooms[0] = new LectureHall("309", 200,true);
        rooms[1] = new ComputerLab("401", 40, "linux");
        rooms[2] = new ComputerLab("403", 40, "MacOS");
        rooms[3] = new ComputerLab("405", 40, "Windows");

        int noOfEvents = 5;
        Event[] events = new Event[5];
        events[0] = new Event("C1", 8,10, 100);
        events[1] = new Event("C2", 10,12,100);
        events[2] = new Event("L1", 8, 10, 30);
        events[3] = new Event("L2", 8,10,30);
        events[4] = new Event("L3", 10,12,30);

        problem = new Instance(noOfEvents, noOfRooms);
        for(index = 0; index < noOfRooms; index++)
            problem.addRoom(rooms[index]);

        for(index = 0; index < noOfEvents; index++)
            problem.addEvent(events[index]);

        Greedy greedy = new Greedy(problem);

        /**
        * generating solution
        */
        Solution sol = greedy.solve();
        sol.print();
    }
}

