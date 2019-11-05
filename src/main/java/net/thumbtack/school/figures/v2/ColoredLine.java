package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.Colored;

public class ColoredLine extends Line implements Colored {
    private int color;
    public ColoredLine(Point startPoint, Point endPoint, int color){
        super(startPoint, endPoint);
        this.color=color;
    }
    public ColoredLine(int xLeft, int yTop, int xRight, int yBottom, int color){
        super(xLeft, yTop, xRight, yBottom);
        this.color=color;
    }
    public ColoredLine(Point endPoint, int color){
        super(endPoint);
        this.color=color;
    }
    public ColoredLine(int x, int y, int color){
        super(x, y);
        this.color=color;
    }
    public ColoredLine(int color){
        super();
        this.color=color;
    }
    public ColoredLine(){
        super();
        this.color=1;
    }
    public  void setColor(int color){
        this.color=color;
    }
    public int getColor(){
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ColoredLine that = (ColoredLine) o;

        return color == that.color;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + color;
        return result;
    }
}
