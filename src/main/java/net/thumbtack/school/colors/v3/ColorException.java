package net.thumbtack.school.colors.v3;

public class ColorException extends Exception {

    private ColorErrorCode colorErrorCode;

    ColorException(ColorErrorCode colorErrorCode, Throwable cause) {
        super(colorErrorCode.getErrorString(), cause);
        this.colorErrorCode = colorErrorCode;
    }

    ColorException(ColorErrorCode colorErrorCode) {
        super(colorErrorCode.getErrorString());
        this.colorErrorCode = colorErrorCode;
    }

    ColorException(ColorErrorCode colorErrorCode, String param) {
        super(String.format(colorErrorCode.getErrorString(), param));
        this.colorErrorCode = colorErrorCode;
    }

    public ColorErrorCode getErrorCode() {
        return colorErrorCode;
    }
}
