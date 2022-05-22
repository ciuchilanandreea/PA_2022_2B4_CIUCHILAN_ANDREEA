package RoomAssign;


import java.util.Scanner;

public class RoomAssignment {
    public static void main(String args[])
    {
        int noOfEvents;
        int noOfRooms;
        Event[] events;
        Room[] rooms;
        Scanner in = new Scanner(System.in);

        System.out.println("Introduceti numarul de evenimente: ");
        noOfEvents = in.nextInt();

        events = new Event[noOfEvents];
        GetDataOfEvents(noOfEvents, events);

        System.out.println("Introduceti numarul de sali: ");
        noOfRooms = in.nextInt();
        rooms = new Room[noOfRooms];
        GetDataOfRooms(noOfRooms, rooms);

        System.out.println("Salile sunt: ");
        for(int index = 0; index < noOfRooms; index++)
            System.out.println(rooms[index].toString());

        System.out.println("Evenimentele sunt: ");
        for(int index = 0; index < noOfEvents; index++)
            System.out.println(events[index].toString());

    }

    public static void GetDataOfEvents(int noEvents, Event[] events) {
        int index;
        String name;
        Scanner in;
        int noParticipants;
        int stTime;
        int stopTime;
        for (index = 0; index < noEvents; index++) {
            System.out.println("Introduceti numele, numarul de participanti, timpul de start si de sfarsit al evenimentului" + (index+1) + ": ");
            in = new Scanner(System.in);
            name = in.nextLine();
            noParticipants = in.nextInt();
            stTime = in.nextInt();
            stopTime = in.nextInt();
            events[index] = new Event(name, stTime, stopTime, noParticipants);
        }
    }

    public static void GetDataOfRooms(int noOfRooms, Room[] rooms) {
        int index;
        Scanner in;
        String name;
        String typeOfRoom;
        int capacity;

        for (index = 0; index < noOfRooms; index++) {
            System.out.println("Introduceti numele, capacitatea si tipul salii " + (index+1) + ": ");
            in = new Scanner(System.in);
            name = in.nextLine();
            capacity = in.nextInt();
            in.nextLine();
            typeOfRoom = in.nextLine();

            if(typeOfRoom.equals("lecture_hall"))
                rooms[index] = new Room(name, Type.LECTURE_HALL, capacity);
            else
                rooms[index] = new Room(name, Type.LAB, capacity);
        }

    }
}

