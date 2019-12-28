package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.iface.v3.Colored;

public class ColoredLine extends Line implements Colored {
    private Color color;

    public ColoredLine(Point startPoint, Point endPoint, Color color) throws ColorException{
        super(startPoint, endPoint);
        this.color = Color.setColor(color);
    }

    public ColoredLine(int xLeft, int yTop, int xRight, int yBottom, Color color) throws ColorException {
        super(xLeft, yTop, xRight, yBottom);
        this.color = Color.setColor(color);
    }

    public ColoredLine(Point endPoint, Color color) throws ColorException {
        super(endPoint);
        this.color = Color.setColor(color);
    }

    public ColoredLine(int x, int y, Color color) throws ColorException {
        super(x, y);
        this.color = Color.setColor(color);
    }

    public ColoredLine(Color color) throws ColorException {
        super();
        this.color = Color.setColor(color);
    }

    public ColoredLine() {
        super();
        this.color = Color.RED;
    }

    public ColoredLine(Point startPoint, Point endPoint, String color) throws ColorException {
        super(startPoint, endPoint);
        this.color = Color.colorFromString(color);
    }

    public ColoredLine(int xLeft, int yTop, int xRight, int yBottom, String color) throws ColorException {
        super(xLeft, yTop, xRight, yBottom);
        this.color = Color.colorFromString(color);
    }

    public ColoredLine(Point endPoint, String color) throws ColorException {
        super(endPoint);
        this.color = Color.colorFromString(color);
    }

    public ColoredLine(int x, int y, String color) throws ColorException {
        super(x, y);
        this.color = Color.colorFromString(color);
    }

    public ColoredLine(String color) throws ColorException {
        super();
        this.color = Color.colorFromString(color);
    }

    public void setColor(Color color) throws ColorException {
        this.color = Color.setColor(color);
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

        ColoredLine that = (ColoredLine) o;

        return color == that.color;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
