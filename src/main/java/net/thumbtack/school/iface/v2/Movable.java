package net.thumbtack.school.iface.v2;

import net.thumbtack.school.figures.v2.Point;

public interface Movable {
    void moveTo(int x, int y);
    void moveTo(Point point);
    void moveRel(int dx, int dy);
}
