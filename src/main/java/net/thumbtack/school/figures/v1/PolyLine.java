package net.thumbtack.school.figures.v1;

import java.util.Arrays;

public class PolyLine {
    private int n = 256;
    private Point[] points = new Point[n];
    public PolyLine(Point[] points){
        this.points=points;
    }
    public Point[] getPoints(){
        return points;
    }
    public void setPoints(Point[] points){
        this.points=points;
    }
    public double getLength(){
        double length=0;
        for(int i=0;i<points.length-1;i++){
            length+=Math.sqrt(Math.abs(points[i].getX()-points[i+1].getX())*Math.abs(points[i].getX()-points[i+1].getX())
                    +Math.abs(points[i].getY()-points[i+1].getY())*Math.abs(points[i].getY()-points[i+1].getY()));
        }
        return length;
    }
    public void moveTo(int x, int y){
        int whereX = x - points[0].getX();
        int whereY = y - points[0].getY();
        points[0].setX(x);
        points[0].setY(y);
        for(int i=1;i<points.length;i++){
            points[i].setX(points[i].getX()+whereX);
            points[i].setY(points[i].getY()+whereY);
        }
    }
    public void moveTo(Point point){
        int whereX = point.getX() - points[0].getX();
        int whereY = point.getY() - points[0].getY();
        points[0]=point;
        for(int i=1;i<points.length;i++){
            points[i].setX(points[i].getX()+whereX);
            points[i].setY(points[i].getY()+whereY);
        }
    }
    public void moveRel(int dx, int dy){
        for(int i=0;i<points.length;i++){
            points[i].moveRel(dx, dy);
        }
    }
    public Rectangle getBoundingRectangle(){
        int xLeft=0,yTop=0,xRight=0,yBottom=0;
        for(int i=0;i<points.length;i++) {
            if (points[i].getX() > xRight)
                xRight = points[i].getX();
            if (points[i].getX() < xLeft)
                xLeft = points[i].getX();
            if (points[i].getY() > yTop)
                yTop = points[i].getY();
            if (points[i].getY() < yBottom)
                yBottom = points[i].getY();
        }
        Rectangle rect = new Rectangle(xLeft, yTop, xRight, yBottom);
        return rect;
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
