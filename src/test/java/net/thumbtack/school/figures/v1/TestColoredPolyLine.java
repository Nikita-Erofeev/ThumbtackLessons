package net.thumbtack.school.figures.v1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColoredPolyLine {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testColoredPolyLine1() {
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, 1);
        assertAll(
                () -> assertEquals(10, coloredPolyLine.getPoints()[0].getX()),
                () -> assertEquals(20, coloredPolyLine.getPoints()[0].getY()),
                () -> assertEquals(30, coloredPolyLine.getPoints()[1].getX()),
                () -> assertEquals(40, coloredPolyLine.getPoints()[1].getY()),
                () -> assertEquals(28.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, coloredPolyLine.getPoints()),
                () -> assertEquals(1, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testColoredPolyLine2() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, 1);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], coloredPolyLine.getPoints()[i].getX());
                        assertEquals(y[i], coloredPolyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, coloredPolyLine.getPoints()),
                () -> assertEquals(1, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testSetPoints() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, 1);
        Point[] newPoints = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            newPoints[i] = new Point(x[x.length - i - 1], y[y.length - i - 1]);
        }
        coloredPolyLine.setPoints(newPoints);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[x.length - i - 1], coloredPolyLine.getPoints()[i].getX());
                        assertEquals(y[y.length - i - 1], coloredPolyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(newPoints, coloredPolyLine.getPoints()),
                () -> assertEquals(1, coloredPolyLine.getColor())
        );
    }


    @Test
    public void testMoveTo1() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, 1);
        coloredPolyLine.moveTo(20, 20);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, coloredPolyLine.getPoints()[i].getX());
                        assertEquals(y[i] + 10, coloredPolyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, coloredPolyLine.getPoints()),
                () -> assertEquals(1, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testMoveTo2() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, 1);
        coloredPolyLine.moveTo(new Point(20, 20));
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, coloredPolyLine.getPoints()[i].getX());
                        assertEquals(y[i] + 10, coloredPolyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, coloredPolyLine.getPoints()),
                () -> assertEquals(1, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testMoveRel() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, 1);
        coloredPolyLine.moveRel(10, 10);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, coloredPolyLine.getPoints()[i].getX());
                        assertEquals(y[i] + 10, coloredPolyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, coloredPolyLine.getPoints()),
                () -> assertEquals(1, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testSetColor() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, 1);
        coloredPolyLine.setColor(2);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], coloredPolyLine.getPoints()[i].getX());
                        assertEquals(y[i], coloredPolyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, coloredPolyLine.getPoints()),
                () -> assertEquals(2, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testEqualsColoredPolyLine() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        Point[] newPoints = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
            newPoints[i] = new Point(x[x.length - i - 1], y[y.length - i - 1]);
        }
        ColoredPolyLine coloredPolyLine1 = new ColoredPolyLine(points, 1);
        ColoredPolyLine coloredPolyLine2 = new ColoredPolyLine(points, 1);
        ColoredPolyLine coloredPolyLine3 = new ColoredPolyLine(points, 2);
        ColoredPolyLine coloredPolyLine4 = new ColoredPolyLine(newPoints, 1);
        assertEquals(coloredPolyLine1, coloredPolyLine2);
        assertNotEquals(coloredPolyLine1, coloredPolyLine3);
        assertNotEquals(coloredPolyLine1, coloredPolyLine4);
    }

    @Test
    public void testBoundingRectangle1() {
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, 1);
        assertEquals(new Rectangle(new Point(10, 20), new Point(30, 40)), coloredPolyLine.getBoundingRectangle());
    }

    @Test
    public void testBoundingRectangle2() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, 1);
        assertEquals(new Rectangle(new Point(10, 10), new Point(40, 20)), coloredPolyLine.getBoundingRectangle());
    }

}
