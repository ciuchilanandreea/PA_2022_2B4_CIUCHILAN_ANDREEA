package org.homework2;

import java.util.Arrays;
import java.util.Scanner;

public class Instance {
    private int noOfEvents;
    private Event[] events;
    private int noOfRooms;
    private Room[] rooms;

    Instance(int noOfEvents, int noOfRooms)
    {
        events = new Event[noOfEvents];
        rooms = new Room[noOfRooms];
        this.noOfEvents = 0;
        this.noOfRooms = 0;

    }

    /**
     * adding a new event
     */

    public void addEvent(Event event)
    {
        int index;

        for(index = 0; index < noOfEvents; index++)
            if(events[index].equals(event))
            {
                System.out.println("Evenimentul " + event + " exista deja!" );
                return;
            }
        events[noOfEvents] = event;
        noOfEvents++;
    }

    /**
     * adding a new room
     */

    public void addRoom(Room room)
    {
        int index;
        if(noOfRooms > 0)
        {
            for(index = 0; index < noOfRooms; index++)
                if(rooms[index].equals(room))
                {
                    System.out.println("Sala " + room + " exista deja! ");
                    return;
                }
        }
        rooms[noOfRooms] = room;
        noOfRooms++;
    }

    public int getNoOfEvents() {
        return noOfEvents;
    }

    public Event[] getEvents() {return events;}

    public Event getEvent(int i) {
        return events[i];
    }

    public void setEvent(int i, Event event) {events[i] = event;}

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public  Room[] getRooms() {return rooms;}

    public Room getRoom(int i) {
        return rooms[i];
    }

    public void setRoom(int i, Room room) {rooms[i] = room;}

     /**
     * sorting events ascending depending on end time of event
     */

    public void sortEvents() {
        boolean sorted;
        int index;
        Event auxiliary = new Event();
        do {
            sorted = true;
            for(index = 0; index < noOfEvents-1; index++)
                if(events[index].getEndTime() > events[index+1].getEndTime())
                {
                   auxiliary = events[index];
                   events[index] = events[index+1];
                   events[index+1] = auxiliary;
                   sorted = false;
                }
        }while(!sorted);
    }

    /**
     * sorting rooms ascending depending on capacity
     */
    public void sortRooms() {
        boolean sorted;
        int index;
        Room auxiliary;
        do {
            sorted = true;
            for(index = 0; index < noOfRooms - 1; index++)
                if(rooms[index].getCapacity() > rooms[index+1].getCapacity())
                {
                    auxiliary = rooms[index];
                    rooms[index] = rooms[index+1];
                    rooms[index+1] = auxiliary;
                    sorted = false;
                }
        }while(!sorted);
    }

    public void print()
    {
        for(int index = 0; index < 5; index++)
            System.out.println(rooms[index].toString());

        for(int index = 0; index < 4; index++)
            System.out.println(events[index].toString());
    }
}


