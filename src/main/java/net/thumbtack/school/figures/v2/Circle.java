package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.HasMetrics;
import net.thumbtack.school.iface.v2.Movable;
import net.thumbtack.school.iface.v2.Resizable;

public class Circle implements HasMetrics, Movable, Resizable {
    private Point center = new Point();
    private int radius=1;

    public Circle(Point center, int radius){
        this.center=center;
        this.radius=radius;
    }
    public Circle(int xCenter, int yCenter, int radius){
        center.setX(xCenter);
        center.setY(yCenter);
        this.radius=radius;
    }
    public Circle(int radius){
        this.radius=radius;
    }
    public Circle(){
        this.radius=1;
    }
    public Point getCenter(){
        return center;
    }
    public int getRadius(){
        return radius;
    }
    public void setCenter(Point center){
        this.center=center;
    }
    public void setRadius(int radius){
        this.radius=radius;
    }
    public void moveRel(int dx, int dy){
        this.center.moveRel(dx, dy);
    }
    public void moveTo(int x, int y){
        this.center.moveTo(x, y);
    }
    public void moveTo(Point point){
        this.center=point;
    }
    public void resize(int ratio){
        this.radius*=ratio;
    }
    public double getArea(){
        return Math.PI*radius*radius;
    }
    public double getPerimeter(){
        return 2*Math.PI*radius;
    }
    public boolean isInside(int x, int y){
        if ((this.center.getX()-x)*(this.center.getX()-x)+(this.center.getY()-y)*
                (this.center.getY()-y)<=this.radius*this.radius)
            return true;
        return false;
    }
    public boolean isInside(Point point){
        if ((this.center.getX()-point.getX())*(this.center.getX()-point.getX())+(this.center.getY()-point.getY())*
                (this.center.getY()-point.getY())<=this.radius*this.radius)
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