package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.ClosedFigure;
import net.thumbtack.school.iface.v3.HasMetrics;

class PairBox<T extends ClosedFigure, R extends ClosedFigure> implements HasMetrics {
    private static final double EPS = 1E-6;
    private T contentFirst;
    private R contentSecond;

    public PairBox(T contentFirst,R contentSecond){
        super();
        this.contentFirst = contentFirst;
        this.contentSecond = contentSecond;
    }

    public T getContentFirst() {
        return contentFirst;
    }

    public R getContentSecond() {
        return contentSecond;
    }

    public void setContentFirst(T contentFirst) {
        this.contentFirst = contentFirst;
    }

    public void setContentSecond(R contentSecond) {
        this.contentSecond = contentSecond;
    }

    @Override
    public double getArea() {
        return contentFirst.getArea()+contentSecond.getArea();
    }

    @Override
    public double getPerimeter() {
        return contentFirst.getPerimeter()+contentSecond.getPerimeter();
    }

    public boolean isAreaEqual(){
        return (contentFirst.getArea()-contentSecond.getArea())<EPS;
    }

    public boolean isAreaEqual(PairBox<?,?> another){
        return ((contentFirst.getArea()+contentSecond.getArea())-(another.getArea()))<EPS;
    }

    public boolean isPerimeterEqual(){
        return (contentFirst.getPerimeter()-contentSecond.getPerimeter())<EPS;
    }

    public boolean isPerimeterEqual(PairBox<?,?> another){
        return ((contentFirst.getPerimeter()+contentSecond.getPerimeter())-(another.getPerimeter()))<EPS;
    }

    public static boolean isAreaEqual(PairBox<?,?> first, PairBox<?,?> second){
        return (first.getArea()-second.getArea())<EPS;
    }

    public static boolean isPerimeterEqual(PairBox<?,?> first, PairBox<?,?> second){
        return (first.getPerimeter()-second.getPerimeter())<EPS;
    }
}
