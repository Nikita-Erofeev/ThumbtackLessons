package net.thumbtack.school.competition.exceptions;

public class CompetitionException extends Exception {
    private ErrorCode errorCode;

    public CompetitionException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
