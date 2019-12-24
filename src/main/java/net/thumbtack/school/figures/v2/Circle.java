package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.HasMetrics;
import net.thumbtack.school.iface.v2.Movable;
import net.thumbtack.school.iface.v2.Resizable;

public class Circle extends ClosedFigure implements HasMetrics, Movable, Resizable {
    private Point center = new Point();
    private int radius = 1;

    //Только для возможности имплементации
    public Point getFirstPoint() {
        return center;
    }

    public Point getSecondPoint() {
        return center;
    }

    public void setFirstPoint(Point point) {
    }

    public void setSecondPoint(int x, int y) {
    }

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(int xCenter, int yCenter, int radius) {
        center.setX(xCenter);
        center.setY(yCenter);
        this.radius = radius;
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    public Circle() {
        radius = 1;
    }

    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void moveRel(int dx, int dy) {
        center.moveRel(dx, dy);
    }

    public void moveTo(int x, int y) {
        center.moveTo(x, y);
    }

    @Override
    public void moveTo(Point point) {
        center = point;
    }

    public void resize(int ratio) {
        radius *= ratio;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public boolean isInside(int x, int y) {
        int centerX = center.getX();
        int centerY = center.getY();
        int dist = (centerX - x) * (centerX - x) + (centerY - y) * (centerY - y);
        int radSquare = radius * radius;
        if (dist <= radSquare)
            return true;
        return false;
    }

    public boolean isInside(Point point) {
        int centerX = center.getX();
        int centerY = center.getY();
        int dist = (centerX - point.getX()) * (centerX - point.getX()) +
                (centerY - point.getY()) * (centerY - point.getY());
        int radSquare = radius * radius;
        if (dist <= radSquare)
            return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circle circle = (Circle) o;

        if (radius != circle.radius) return false;
        return center != null ? center.equals(circle.center) : circle.center == null;
    }

    @Override
    public int hashCode() {
        int result = center != null ? center.hashCode() : 0;
        result = 31 * result + radius;
        return result;
    }
}