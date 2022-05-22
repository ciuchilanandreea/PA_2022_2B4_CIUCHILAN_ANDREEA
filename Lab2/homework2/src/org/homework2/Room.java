package org.homework2;

import java.util.Objects;

public abstract class Room {
    private String name;
    private int capacity;
    private int eventEndTime;

    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.eventEndTime = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(int eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", eventEndTime=" + eventEndTime +
                ' ';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return capacity == room.capacity && Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity);
    }
}
