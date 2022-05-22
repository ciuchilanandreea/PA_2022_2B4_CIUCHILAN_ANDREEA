package org.homework2;

public class Greedy{
    private Instance problem;

    public Greedy(Instance problem) {
        this.problem = problem;
    }

    public Solution solve()
    {
        Solution solution = new Solution(problem.getNoOfEvents());
        int indexi, indexj;
        problem.sortEvents();
        problem.sortRooms();

         /**
         * we assign a room to each event, only when capacity of room is bigger or equal than number of participants
         * and the end time of the previous event is smaller or equal than new event
         * after we assigned the room, we update the end time of event which takes place in the specified room
         */
        for(indexi = 0; indexi < problem.getNoOfEvents(); indexi++)
            for(indexj = 0; indexj < problem.getNoOfRooms(); indexj++)
                if(problem.getEvent(indexi).getNoOfParticipants() <= problem.getRoom(indexj).getCapacity())
                    if(problem.getRoom(indexj).getEventEndTime() <= problem.getEvent(indexi).getStartTime())
                    {
                        problem.getRoom(indexj).setEventEndTime(problem.getEvent(indexi).getEndTime());
                        solution.setAssignment(indexi, problem.getRoom(indexj));
                        solution.setEvent(indexi, problem.getEvent(indexi));
                        break;
                    }
        return solution;
    }
}
