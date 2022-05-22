package org.homework2;

import java.util.Objects;

public class Event
{
    private String name;
    private int startTime;
    private int endTime;
    private int noOfParticipants;

    public Event(){}

    public Event(String name, int startTime, int endTime, int noOfParticipants) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.noOfParticipants = noOfParticipants;
    }

    public String getName() {
        return name;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getNoOfParticipants() {
        return noOfParticipants;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setNoOfParticipants(int noOfParticipants) {
        this.noOfParticipants = noOfParticipants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", noOfParticipants=" + noOfParticipants +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return startTime == event.startTime && endTime == event.endTime && noOfParticipants == event.noOfParticipants && Objects.equals(name, event.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startTime, endTime, noOfParticipants);
    }
}
