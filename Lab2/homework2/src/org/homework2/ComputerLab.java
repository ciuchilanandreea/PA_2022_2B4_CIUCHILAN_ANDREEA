package org.homework2;

public class ComputerLab extends Room{
    private String operatingSystem;

    public ComputerLab(String name, int capacity, String operatingSystem) {
        super(name, capacity);
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String toString() {
        return super.toString() + "type: ComputerLab " +
                "operatingSystem='" + operatingSystem + '\'' +
                '}';
    }
}
