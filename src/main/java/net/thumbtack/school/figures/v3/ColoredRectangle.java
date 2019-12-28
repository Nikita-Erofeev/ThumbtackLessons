package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.iface.v3.Colored;

public class ColoredRectangle extends Rectangle implements Colored {
    private Color color;

    public ColoredRectangle(Point leftTop, Point rightBottom, Color color) throws ColorException {
        super(leftTop, rightBottom);
        this.color = Color.setColor(color);
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, Color color) throws ColorException {
        super(xLeft, yTop, xRight, yBottom);
        this.color = Color.setColor(color);
    }

    public ColoredRectangle(int length, int width, Color color) throws ColorException {
        super(length, width);
        this.color = Color.setColor(color);
    }

    public ColoredRectangle(Color color) throws ColorException {
        super();
        this.color = Color.setColor(color);
    }

    public ColoredRectangle() {
        super();
        this.color = Color.RED;
    }

    public ColoredRectangle(Point leftTop, Point rightBottom, String color) throws ColorException {
        super(leftTop, rightBottom);
        this.color = Color.colorFromString(color);
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, String color) throws ColorException {
        super(xLeft, yTop, xRight, yBottom);
        this.color = Color.colorFromString(color);
    }

    public ColoredRectangle(int length, int width, String color) throws ColorException {
        super(length, width);
        this.color = Color.colorFromString(color);
    }

    public ColoredRectangle(String color) throws ColorException {
        super();
        this.color = Color.colorFromString(color);
    }


    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        color = Color.colorFromString(colorString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ColoredRectangle that = (ColoredRectangle) o;

        return color == that.color;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    public void setColor(Color color) throws ColorException {
        this.color = Color.setColor(color);
    }

}
