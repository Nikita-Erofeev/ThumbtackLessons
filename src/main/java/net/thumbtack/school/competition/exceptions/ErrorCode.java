package net.thumbtack.school.competition.exceptions;

public enum  ErrorCode {
    ERROR_UPLOAD_DATABASE("Database could not be loaded"),
    ERROR_SAVING_DATABASE("Saving failed, try again"),
    DUPLICATE_USER("This user already exist"),
    ERROR_DELETING_USER("Access denied"),
    WRONG_PASSWORD("Wrong password"),
    WRONG_LOGIN_PASSWORD("Wrong login and password"),
    LOGIN_ERROR("Wrong user profile"),
    DUPLICATE_APPLICATION("This application already exist"),
    APPLICATION_NOT_EXIST("Such application not exist"),
    RATINGS_NOT_EXIST("There are no rated applications"),
    INVALID_REQUEST("Invalid request");

    private String errorString;

    ErrorCode(String errorString){
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
