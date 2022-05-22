package org.homework2;

public class LectureHall extends Room{
    private boolean existProjector;

    public LectureHall(String name, int capacity, boolean existProjector) {
        super(name, capacity);
        this.existProjector = existProjector;
    }

    @Override
    public String toString() {
        return super.toString() + "type: LectureHall " +
                "existProjector=" + existProjector +
                '}';
    }
}
