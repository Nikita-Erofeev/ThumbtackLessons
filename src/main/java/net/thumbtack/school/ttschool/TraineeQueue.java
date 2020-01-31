package net.thumbtack.school.ttschool;

import java.util.LinkedList;
import java.util.Queue;

public class TraineeQueue {
    private Queue<Trainee> queue = new LinkedList<>();

    public TraineeQueue() {
    }

    public void addTrainee(Trainee trainee) {
        queue.add(trainee);
    }

    public Trainee removeTrainee() throws TrainingException{
        if(queue.isEmpty()){
            throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        } else {
            return queue.remove();
        }
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
