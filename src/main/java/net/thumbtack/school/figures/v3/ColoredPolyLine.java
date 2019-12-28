package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.iface.v3.Colored;

public class ColoredPolyLine extends PolyLine implements Colored {
    private Color color;

    public ColoredPolyLine(Point[] points, Color color) throws ColorException {
        super(points);
        this.color = Color.setColor(color);
    }

    public ColoredPolyLine(Point[] points, String color) throws ColorException {
        super(points);
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

        ColoredPolyLine that = (ColoredPolyLine) o;

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
