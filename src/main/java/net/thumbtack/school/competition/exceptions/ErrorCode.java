package net.thumbtack.school.competition.exceptions;

public enum  ErrorCode {
    ERROR_UPLOAD_DATABASE("Database could not be loaded"),
    ERROR_SAVING_DATABASE("Saving failed, try again"),
    DUPLICATE_USER("This user already exist"),
    ERROR_DELETING_USER("Access denied");

    private String errorString;

    ErrorCode(String errorString){
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
