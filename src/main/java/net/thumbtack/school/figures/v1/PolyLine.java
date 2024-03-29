package net.thumbtack.school.figures.v1;

import java.util.Arrays;

public class PolyLine {
    private int n = 256;
    private Point[] points = new Point[n];

    public PolyLine(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return points.clone();
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public double getLength() {
        double length = 0;
        double diffSqX;
        double diffSqY;
        for (int i = 0; i < points.length - 1; i++) {
            diffSqX = Math.pow(points[i].getX() - points[i + 1].getX(),2);
            diffSqY = Math.pow(points[i].getY() - points[i + 1].getY(),2);
            length += Math.sqrt(diffSqX + diffSqY);
        }
        return length;
    }

    public void moveTo(int x, int y) {
        int whereX = x - points[0].getX();
        int whereY = y - points[0].getY();
        points[0].setX(x);
        points[0].setY(y);
        for (int i = 1; i < points.length; i++) {
            points[i].setX(points[i].getX() + whereX);
            points[i].setY(points[i].getY() + whereY);
        }
    }

    public void moveTo(Point point) {
        int whereX = point.getX() - points[0].getX();
        int whereY = point.getY() - points[0].getY();
        //points[0]=point;
        points[0].setX(point.getX());
        points[0].setY(point.getY());
        for (int i = 1; i < points.length; i++) {
            points[i].setX(points[i].getX() + whereX);
            points[i].setY(points[i].getY() + whereY);
        }
    }

    public void moveRel(int dx, int dy) {
        for (int i = 0; i < points.length; i++) {
            //points[i].moveRel(dx, dy);
            points[i].setX(points[i].getX() + dx);
            points[i].setY(points[i].getY() + dy);
        }
    }

    public Rectangle getBoundingRectangle() {
        int xLeft = points[0].getX();
        int yTop = points[0].getY();
        int xRight = points[0].getX();
        int yBottom = points[0].getY();
        for (int i = 0; i < points.length; i++) {
            if (xLeft > points[i].getX())
                xLeft = points[i].getX();
            if (xRight < points[i].getX())
                xRight = points[i].getX();
            if (yBottom < points[i].getY())
                yBottom = points[i].getY();
            if (yTop > points[i].getY())
                yTop = points[i].getY();
        }
        return new Rectangle(xLeft, yTop, xRight, yBottom);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PolyLine polyLine = (PolyLine) o;

        if (n != polyLine.n) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(points, polyLine.points);
    }

    @Override
    public int hashCode() {
        int result = n;
        result = 31 * result + Arrays.hashCode(points);
        return result;
    }
}
