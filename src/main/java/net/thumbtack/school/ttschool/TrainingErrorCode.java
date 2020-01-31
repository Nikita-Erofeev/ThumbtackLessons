package net.thumbtack.school.ttschool;

public enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("Wrong first name, invalid input"),
    TRAINEE_WRONG_LASTNAME("Wrong last name, invalid input"),
    TRAINEE_WRONG_RATING("Wrong rating"),
    GROUP_WRONG_ROOM("Wrong room"),
    GROUP_WRONG_NAME("Invalid group name"),
    TRAINEE_NOT_FOUND("There is no such trainee in the group"),
    SCHOOL_WRONG_NAME("Wrong name"),
    DUPLICATE_GROUP_NAME("This name already exists"),
    GROUP_NOT_FOUND("Group not found"),
    DUPLICATE_TRAINEE("This trainee already exists"),
    EMPTY_TRAINEE_QUEUE("Empty queue");

    private String errorString;

    TrainingErrorCode(String errorString){
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
