package net.thumbtack.school.figures.v1;

public class Rectangle {
    private Point leftTop = new Point();
    private Point rightBottom = new Point();
    private int sideA(){
        return  rightBottom.getY() - leftTop.getY();
    }
    private int sideB(){
        return rightBottom.getX() - leftTop.getX();
    }
    public Rectangle(Point leftTop, Point rightBottom){
        this.leftTop = leftTop;
        this.rightBottom =rightBottom;
    }
    public Rectangle(int xLeft, int yTop, int xRight, int yBottom){
        leftTop.setX(xLeft);
        leftTop.setY(yTop);
        rightBottom.setX(xRight);
        rightBottom.setY(yBottom);
    }
    public Rectangle(int length, int width){
        leftTop.setY(length);
        rightBottom.setX(width);
    }
    public Rectangle(){
        leftTop.setY(1);
        rightBottom.setX(1);
    }
    public Point getTopLeft(){
        return this.leftTop;
    }
    public Point getBottomRight(){
        return this.rightBottom;
    }
    public void setTopLeft(Point topLeft){
        this.leftTop=topLeft;
    }
    public void setBottomRight(Point bottomRight){
        this.rightBottom=bottomRight;
    }
    public int getLength(){
        if(this.sideA()>this.sideB())
            return this.sideA();
        else
            return this.sideB();
    }
    public int getWidth(){
        if(this.sideA()>this.sideB())
            return this.sideB();
        else
            return this.sideA();
    }
    public void moveRel(int dx, int dy){
        leftTop.moveRel(dx, dy);
        rightBottom.moveRel(dx, dy);
    }
    public void moveTo(int x, int y){
        leftTop.moveTo(x, y);
        rightBottom.setX(leftTop.getX()+sideB());
        rightBottom.setY(leftTop.getY()+sideA());
    }
    public void moveTo(Point point){
        leftTop=point;
        rightBottom.setX(leftTop.getX()+sideB());
        rightBottom.setY(leftTop.getY()+sideA());
    }
    public void resize(int ratio){
        rightBottom.setX(leftTop.getX()+sideB()*ratio);
        rightBottom.setY(leftTop.getY()+sideA()*ratio);
    }
    public double getArea(){
        return sideA()*sideB();
    }
    public double getPerimeter(){
        return 2*(sideA()+sideB());
    }
    public boolean isInside(int x, int y){
        if (leftTop.getX()<=x & leftTop.getY()<=y & rightBottom.getX()>=x & rightBottom.getY()>=y)
            return true;
        return false;
    }
    public boolean isInside(Point point){
        if (leftTop.getX()<=point.getX() & leftTop.getY()<=point.getY() & rightBottom.getX()>=point.getX() &
                rightBottom.getY()>=point.getY())
            return true;
        return false;
    }
    public boolean isIntersects(Rectangle rectangle){
        if (this.leftTop.getX()>rectangle.rightBottom.getX() || this.leftTop.getY()>rectangle.rightBottom.getY()
                || this.rightBottom.getX()<rectangle.leftTop.getX() || this.rightBottom.getY()<rectangle.leftTop.getY())
            return false;
        return true;
    }
    public boolean isInside(Rectangle rectangle){
        if (this.leftTop.getX()<=rectangle.leftTop.getX() & this.leftTop.getY()<=rectangle.leftTop.getY()
        & this.rightBottom.getX()>=rectangle.rightBottom.getX() & this.rightBottom.getY()>=rectangle.rightBottom.getY())
            return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (leftTop != null ? !leftTop.equals(rectangle.leftTop) : rectangle.leftTop != null) return false;
        return rightBottom != null ? rightBottom.equals(rectangle.rightBottom) : rectangle.rightBottom == null;
    }

    @Override
    public int hashCode() {
        int result = leftTop != null ? leftTop.hashCode() : 0;
        result = 31 * result + (rightBottom != null ? rightBottom.hashCode() : 0);
        return result;
    }
}
