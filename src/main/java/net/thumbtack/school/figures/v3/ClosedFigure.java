package net.thumbtack.school.figures.v3;

import net.thumbtack.school.iface.v3.HasMetrics;
import net.thumbtack.school.iface.v3.Movable;
import net.thumbtack.school.iface.v3.Resizable;

abstract class ClosedFigure extends Figure implements HasMetrics, Movable, Resizable {
   // abstract double getArea();
   // abstract double getPerimeter();
    public boolean isInside(Point point){
        return false;
    }
    abstract public boolean isInside(int x, int y);


}
