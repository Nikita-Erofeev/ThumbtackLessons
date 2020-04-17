package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.iface.v3.Colored;

public class ColoredCircle extends Circle implements Colored {
    private Color color;

    public ColoredCircle(Point center, int radius, Color color) throws ColorException {
        super(center, radius);
        this.color = Color.setColor(color);
    }

    public ColoredCircle(int xCenter, int yCenter, int radius, Color color) throws ColorException {
        super(xCenter, yCenter, radius);
        this.color = Color.setColor(color);
    }

    public ColoredCircle(int radius, Color color) throws ColorException {
        super(radius);
        this.color = Color.setColor(color);
    }

    public ColoredCircle(Color color) throws ColorException {
        super();
        this.color = Color.setColor(color);
    }

    public ColoredCircle() {
        super();
        color = Color.RED;
    }

    public ColoredCircle(Point center, int radius, String color) throws ColorException {
        super(center, radius);
        this.color = Color.colorFromString(color);
    }

    public ColoredCircle(int xCenter, int yCenter, int radius, String color) throws ColorException {
        super(xCenter, yCenter, radius);
        this.color = Color.colorFromString(color);
    }

    public ColoredCircle(int radius, String color) throws ColorException {
        super(radius);
        this.color = Color.colorFromString(color);
    }

    public ColoredCircle(String color) throws ColorException {
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

    public void setColor(Color color) throws ColorException {
        this.color = Color.setColor(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ColoredCircle that = (ColoredCircle) o;

        return color == that.color;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
