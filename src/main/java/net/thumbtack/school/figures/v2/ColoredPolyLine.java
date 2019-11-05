package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.Colored;

public class ColoredPolyLine extends PolyLine implements Colored {
    private int color;
    public ColoredPolyLine(Point[] points, int color){
        super(points);
        this.color=color;
    }
    public int getColor(){
        return color;
    }
    public void setColor(int color){
        this.color=color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ColoredPolyLine that = (ColoredPolyLine) o;

        return color == that.color;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + color;
        return result;
    }
}
