package org.homework2;

import java.util.Arrays;

public class Solution {
    private int noOfEvents;
    private Room[] assignment;
    private Event[] events;

    Solution(int noOfEvents1)
    {
        noOfEvents = noOfEvents1;
        assignment = new Room[noOfEvents1];
        events = new Event[noOfEvents1];
    }

    public void setAssignment(int index, Room assignment) {
        this.assignment[index] = assignment;
    }

    public void setEvent(int index, Event event)
    {
        this.events[index] = event;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "noOfEvents=" + noOfEvents +
                ", assignment=" + Arrays.toString(assignment) +
                '}';
    }

    public void print()
    {
        for(int index = 0; index < noOfEvents; index++)
            System.out.println("Evenimentul " + events[index].getName() + " are atribuit sala " + assignment[index].getName());

    }


}
