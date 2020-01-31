package net.thumbtack.school.ttschool;

import java.util.*;

public class TraineeMap {
    private Map<Trainee, String> traineeMap = new TreeMap<>(Comparator.comparing(Trainee::getFullName));

    public TraineeMap() {

    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if (traineeMap.containsKey(trainee)) {
            throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
        } else {
            traineeMap.put(trainee, institute);
        }
    }

    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if (traineeMap.containsKey(trainee)) {
//            traineeMap.put(trainee,institute);
            traineeMap.replace(trainee, institute);
        } else {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException {
        if (traineeMap.containsKey(trainee)) {
            traineeMap.remove(trainee);
        } else {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public int getTraineesCount() {
        return traineeMap.size();
    }

    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {
        if(traineeMap.containsKey(trainee)){
            return traineeMap.get(trainee);
        }else {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public Set<Trainee> getAllTrainees() {
        return traineeMap.keySet();
    }

    public Set<String> getAllInstitutes(){
        return new TreeSet<>(traineeMap.values());
    }

    public boolean isAnyFromInstitute(String institute){
        return traineeMap.containsValue(institute);
    }
}
