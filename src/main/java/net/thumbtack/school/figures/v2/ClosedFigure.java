package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.HasMetrics;
import net.thumbtack.school.iface.v2.Movable;
import net.thumbtack.school.iface.v2.Resizable;

abstract class ClosedFigure extends Figure implements HasMetrics, Movable, Resizable {
   // abstract double getArea();
   // abstract double getPerimeter();
    public boolean isInside(Point point){

        return false;
    }
    abstract public boolean isInside(int x, int y);


}
