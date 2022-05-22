package RoomAssign;

public class Event
{
    String name;
    int startTime;
    int endTime;
    int noOfParticipants;

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
}
