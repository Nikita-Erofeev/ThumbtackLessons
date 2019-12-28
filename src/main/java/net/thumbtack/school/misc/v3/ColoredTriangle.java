package net.thumbtack.school.misc.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.iface.v3.Colored;

public class ColoredTriangle extends Triangle implements Colored {
    private Color color;

    public ColoredTriangle() {
        super();
        color = Color.RED;
    }

    public ColoredTriangle(Color color) {
        super();
        this.color = color;
    }

    public ColoredTriangle(Point firstPoint, Point secondPoint, Point thirdPoint, Color color) throws ColorException{
        super(firstPoint, secondPoint, thirdPoint);
        this.color = Color.setColor(color);
    }

    public ColoredTriangle(int xFirst, int yFirst, int xSecond, int ySecond, int xThird, int yThird, Color color) throws ColorException {
        super(xFirst, yFirst, xSecond, ySecond, xThird, yThird);
        this.color = Color.setColor(color);
    }

    public ColoredTriangle(String color) throws ColorException {
        super();
        this.color = Color.colorFromString(color);
    }

    public ColoredTriangle(Point firstPoint, Point secondPoint, Point thirdPoint,String color) throws ColorException {
        super(firstPoint, secondPoint, thirdPoint);
        this.color = Color.colorFromString(color);
    }

    public ColoredTriangle(int xFirst, int yFirst, int xSecond, int ySecond, int xThird, int yThird,String color) throws ColorException {
        super(xFirst, yFirst, xSecond, ySecond, xThird, yThird);
        this.color = Color.colorFromString(color);
    }

    public void setColor(Color color) throws ColorException {
        this.color = Color.setColor(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ColoredTriangle that = (ColoredTriangle) o;

        return color == that.color;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        this.color = Color.colorFromString(colorString);
    }

}
