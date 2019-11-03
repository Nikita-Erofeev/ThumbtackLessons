package net.thumbtack.school.figures.v1;

public class Line {
    private Point startPoint = new Point();
    private Point endPoint = new Point();
    public Line(Point startPoint, Point endPoint){
        this.startPoint=startPoint;
        this.endPoint=endPoint;
    }
    public Line(int xLeft, int yTop, int xRight, int yBottom){
        startPoint.setX(xLeft);
        startPoint.setY(yTop);
        endPoint.setX(xRight);
        endPoint.setY(yBottom);
    }
    public Line(Point endPoint){
        this.endPoint=endPoint;
    }
    public Line(int x, int y){
        endPoint.setX(x);
        endPoint.setY(y);
    }
    public Line(){
        endPoint.setX(1);
        endPoint.setY(1);
    }
    public Point getStartPoint(){
        return startPoint;
    }
    public Point getEndPoint(){
        return endPoint;
    }
    public void setStartPoint(Point point){
        this.startPoint=point;
    }
    public void setEndPoint(Point point){
        this.endPoint=point;
    }
    public double getLength(){
        return Math.sqrt(Math.abs(startPoint.getX()-endPoint.getX())*Math.abs(startPoint.getX()-endPoint.getX())
                +Math.abs(startPoint.getY()-endPoint.getY())*Math.abs(startPoint.getY()-endPoint.getY()));
    }
    public void moveTo(int x, int y){
        int whereX = x - startPoint.getX();
        int whereY = y - startPoint.getY();
        startPoint.setX(x);
        startPoint.setY(y);
        endPoint.setX(endPoint.getX()+whereX);
        endPoint.setY(endPoint.getY()+whereY);
    }
    public void moveTo(Point point){
        int whereX = point.getX() - startPoint.getX();
        int whereY = point.getY() - startPoint.getY();
        startPoint=point;
        endPoint.setX(endPoint.getX()+whereX);
        endPoint.setY(endPoint.getY()+whereY);
    }
    public void moveRel(int dx, int dy){
        startPoint.moveRel(dx, dy);
        endPoint.moveRel(dx, dy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (startPoint != null ? !startPoint.equals(line.startPoint) : line.startPoint != null) return false;
        return endPoint != null ? endPoint.equals(line.endPoint) : line.endPoint == null;
    }

    @Override
    public int hashCode() {
        int result = startPoint != null ? startPoint.hashCode() : 0;
        result = 31 * result + (endPoint != null ? endPoint.hashCode() : 0);
        return result;
    }
}
