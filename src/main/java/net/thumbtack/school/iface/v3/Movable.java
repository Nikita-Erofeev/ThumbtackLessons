package net.thumbtack.school.iface.v3;

import net.thumbtack.school.figures.v3.Point;


public interface Movable {

    void moveTo(int x, int y);
    // void moveTo(Point point);
    void moveRel(int dx, int dy);

    Point getFirstPoint();
    Point getSecondPoint();
    void setFirstPoint(Point point);
    void setSecondPoint(int x, int y);


    default void moveTo(Point point) {
        int toX = point.getX() - getFirstPoint().getX();
        int toY = point.getY() - getFirstPoint().getY();
        setFirstPoint(point);
        setSecondPoint(getSecondPoint().getX()+toX, getSecondPoint().getY()+toY);
    }

}
