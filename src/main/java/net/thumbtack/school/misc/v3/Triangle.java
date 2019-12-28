package net.thumbtack.school.misc.v3;

import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.iface.v3.HasMetrics;
import net.thumbtack.school.iface.v3.Movable;

public class Triangle implements Movable, HasMetrics {
    private Point firstPoint = new Point();
    private Point secondPoint = new Point();
    private Point thirdPoint = new Point();

    public Triangle(Point firstPoint, Point secondPoint, Point thirdPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.thirdPoint = thirdPoint;
    }

    public Triangle(int xFirst, int yFirst, int xSecond, int ySecond, int xThird, int yThird) {
        firstPoint.setX(xFirst);
        firstPoint.setY(yFirst);
        secondPoint.setX(xSecond);
        secondPoint.setY(ySecond);
        thirdPoint.setX(xThird);
        thirdPoint.setY(yThird);
    }

    public Triangle() {
        firstPoint.setX(0);
        firstPoint.setY(-1);
        secondPoint.setX(1);
        secondPoint.setY(0);
        thirdPoint.setX(0);
        thirdPoint.setY(0);
    }

    @Override
    public net.thumbtack.school.figures.v3.Point getFirstPoint() {
        return firstPoint;
    }

    @Override
    public Point getSecondPoint() {
        return secondPoint;
    }

    public Point getThirdPoint() {
        return thirdPoint;
    }

    @Override
    public void setFirstPoint(Point point) {
        firstPoint = point;
    }

    public void setFirstPoint(int x, int y) {
        firstPoint.setX(x);
        firstPoint.setY(y);
    }

    public void setSecondPoint(Point point) {
        secondPoint = point;
    }

    @Override
    public void setSecondPoint(int x, int y) {
        secondPoint.setX(x);
        secondPoint.setY(y);
    }

    public void setThirdPoint(Point point) {
        thirdPoint = point;
    }

    public void setThirdPoint(int x, int y) {
        thirdPoint.setX(x);
        thirdPoint.setY(y);
    }

    @Override
    public double getArea() {
        double area;
        double firstStep = (firstPoint.getX() - thirdPoint.getX()) * (secondPoint.getY() - thirdPoint.getY());
        double secondStep = (secondPoint.getX() - thirdPoint.getX()) * (firstPoint.getY() - thirdPoint.getY());
        area = Math.abs((firstStep - secondStep) / 2);
        return area;
    }

    @Override
    public double getPerimeter() {
        double fS = Math.sqrt(Math.pow(secondPoint.getX() - firstPoint.getX(), 2) +
                Math.pow(secondPoint.getY() - firstPoint.getY(), 2));
        double sTh = Math.sqrt(Math.pow(thirdPoint.getX() - secondPoint.getX(), 2) +
                Math.pow(thirdPoint.getY() - secondPoint.getY(), 2));
        double fTh = Math.sqrt(Math.pow(thirdPoint.getX() - firstPoint.getX(), 2) +
                Math.pow(thirdPoint.getY() - firstPoint.getY(), 2));
        double perimeter = fS + sTh + fTh;
        return perimeter;
    }

    @Override
    public void moveTo(int x, int y) {
        int toX = x - firstPoint.getX();
        int toY = y - firstPoint.getY();
        firstPoint.moveTo(x, y);
        secondPoint.setX(secondPoint.getX() + toX);
        secondPoint.setY(secondPoint.getY() + toY);
        thirdPoint.setX(thirdPoint.getX() + toX);
        thirdPoint.setY(thirdPoint.getY() + toY);
    }

    public void moveTo(Point point) {
        int toX = point.getX() - firstPoint.getX();
        int toY = point.getY() - firstPoint.getY();
        firstPoint = point;
        secondPoint.setX(secondPoint.getX() + toX);
        secondPoint.setY(secondPoint.getY() + toY);
        thirdPoint.setX(thirdPoint.getX() + toX);
        thirdPoint.setY(thirdPoint.getY() + toY);
    }

    @Override
    public void moveRel(int dx, int dy) {
        firstPoint.moveRel(dx, dy);
        secondPoint.moveRel(dx, dy);
        thirdPoint.moveRel(dx, dy);
    }

    public boolean isInside(int x, int y) {
        double  ur1 = (firstPoint.getX()-x)*(secondPoint.getY()-firstPoint.getY())-
                (secondPoint.getX()-firstPoint.getX())*(firstPoint.getY()-y);
        double  ur2 = (secondPoint.getX()-x)*(thirdPoint.getY()-secondPoint.getY())-
                (thirdPoint.getX()-secondPoint.getX())*(secondPoint.getY()-y);
        double  ur3 = (thirdPoint.getX()-x)*(firstPoint.getY()-thirdPoint.getY())-
                (firstPoint.getX()-thirdPoint.getX())*(thirdPoint.getY()-y);
        if ((ur1>=0 && ur2 >=0 && ur3>=0)||(ur1<=0 && ur2 <=0 && ur3<=0)){
            return true;
        } else{
            return false;
        }
    }

    public boolean isInside(Point point) {
        double  ur1 = (firstPoint.getX()-point.getX())*(secondPoint.getY()-firstPoint.getY())-
                (secondPoint.getX()-firstPoint.getX())*(firstPoint.getY()-point.getY());
        double  ur2 = (secondPoint.getX()-point.getX())*(thirdPoint.getY()-secondPoint.getY())-
                (thirdPoint.getX()-secondPoint.getX())*(secondPoint.getY()-point.getY());
        double  ur3 = (thirdPoint.getX()-point.getX())*(firstPoint.getY()-thirdPoint.getY())-
                (firstPoint.getX()-thirdPoint.getX())*(thirdPoint.getY()-point.getY());
        if ((ur1>=0 && ur2 >=0 && ur3>=0)||(ur1<=0 && ur2 <=0 && ur3<=0)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

        if (firstPoint != null ? !firstPoint.equals(triangle.firstPoint) : triangle.firstPoint != null) return false;
        if (secondPoint != null ? !secondPoint.equals(triangle.secondPoint) : triangle.secondPoint != null)
            return false;
        return thirdPoint != null ? thirdPoint.equals(triangle.thirdPoint) : triangle.thirdPoint == null;
    }

    @Override
    public int hashCode() {
        int result = firstPoint != null ? firstPoint.hashCode() : 0;
        result = 31 * result + (secondPoint != null ? secondPoint.hashCode() : 0);
        result = 31 * result + (thirdPoint != null ? thirdPoint.hashCode() : 0);
        return result;
    }
}
