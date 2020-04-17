package net.thumbtack.school.colors.v3;

public enum Color {
    RED, GREEN, BLUE;

    public static Color setColor(Color color) throws ColorException {
        if (color == null) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        } else {
            return color;
        }
    }

    public static Color colorFromString(String colorString) throws ColorException {
        if(colorString == null){
            throw new ColorException(ColorErrorCode.NULL_COLOR, ColorErrorCode.NULL_COLOR.getErrorString());
        }
        try {
            return Color.valueOf(colorString);
        } catch (IllegalArgumentException ex) {
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING, colorString);
        }
    }
}
