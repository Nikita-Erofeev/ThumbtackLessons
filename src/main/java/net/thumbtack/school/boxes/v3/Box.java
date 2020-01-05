package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.ClosedFigure;
import net.thumbtack.school.iface.v3.HasMetrics;

class Box<T extends ClosedFigure> implements HasMetrics {
    private T content;
    private static final double EPS = 1E-6;

    public Box(T content) {
        super();
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public double getArea() {
        return content.getArea();
    }

    @Override
    public double getPerimeter() {
        return content.getPerimeter();
    }

    public boolean isAreaEqual(Box<? extends ClosedFigure> another) {
        return Math.abs(content.getArea() - another.getArea()) < EPS;
    }

    public static boolean isAreaEqual(Box<? extends ClosedFigure> first, Box<? extends ClosedFigure> second) {
        return Math.abs(first.getArea() - second.getArea()) < EPS;
    }

    public boolean isPerimeterEqual(Box<? extends ClosedFigure> another) {
        return Math.abs(content.getPerimeter() - another.getPerimeter()) < EPS;
    }

    public static boolean isPerimeterEqual(Box<? extends ClosedFigure> first, Box<? extends ClosedFigure> second) {
        return Math.abs(first.getPerimeter() - second.getPerimeter()) < EPS;
    }
}
