package net.thumbtack.school.ttschool;

public class TrainingException extends Exception {

    private TrainingErrorCode trainingErrorCode;

    TrainingException(TrainingErrorCode trainingErrorCode){
        super(trainingErrorCode.getErrorString());
        this.trainingErrorCode = trainingErrorCode;
    }

    public TrainingErrorCode getErrorCode() {
        return trainingErrorCode;
    }
}
