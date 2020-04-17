package net.thumbtack.school.colors.v3;

public enum ColorErrorCode {
    WRONG_COLOR_STRING("This color does not exist %s"),
    NULL_COLOR("Null pointer");

    private String errorString;

    ColorErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
