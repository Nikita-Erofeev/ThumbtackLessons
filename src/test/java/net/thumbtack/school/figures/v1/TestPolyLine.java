package net.thumbtack.school.figures.v1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPolyLine {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testPolyLine1() {
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        PolyLine polyLine = new PolyLine(points);
        assertAll(
                () -> assertEquals(10, polyLine.getPoints()[0].getX()),
                () -> assertEquals(20, polyLine.getPoints()[0].getY()),
                () -> assertEquals(30, polyLine.getPoints()[1].getX()),
                () -> assertEquals(40, polyLine.getPoints()[1].getY()),
                () -> assertEquals(28.284271, polyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, polyLine.getPoints())
        );
    }

    @Test
    public void testPolyLine2() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        PolyLine polyLine = new PolyLine(points);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], polyLine.getPoints()[i].getX());
                        assertEquals(y[i], polyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, polyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, polyLine.getPoints())
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
        PolyLine polyLine = new PolyLine(points);
        Point[] newPoints = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            newPoints[i] = new Point(x[x.length - i - 1], y[y.length - i - 1]);
        }
        polyLine.setPoints(newPoints);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[x.length - i - 1], polyLine.getPoints()[i].getX());
                        assertEquals(y[y.length - i - 1], polyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, polyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(newPoints, polyLine.getPoints())
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
        PolyLine polyLine = new PolyLine(points);
        polyLine.moveTo(20, 20);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, polyLine.getPoints()[i].getX());
                        assertEquals(y[i] + 10, polyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, polyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, polyLine.getPoints())
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
        PolyLine polyLine = new PolyLine(points);
        polyLine.moveTo(new Point(20, 20));
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, polyLine.getPoints()[i].getX());
                        assertEquals(y[i] + 10, polyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, polyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, polyLine.getPoints())
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
        PolyLine polyLine = new PolyLine(points);
        polyLine.moveRel(10, 10);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i] + 10, polyLine.getPoints()[i].getX());
                        assertEquals(y[i] + 10, polyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, polyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, polyLine.getPoints())
        );
    }

    @Test
    public void testEqualsPolyLine() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        Point[] newPoints = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
            newPoints[i] = new Point(x[x.length - i - 1], y[y.length - i - 1]);
        }
        PolyLine polyLine1 = new PolyLine(points);
        PolyLine polyLine2 = new PolyLine(points);
        PolyLine polyLine3 = new PolyLine(newPoints);
        assertEquals(polyLine1, polyLine2);
        assertNotEquals(polyLine1, polyLine3);
    }

    @Test
    public void testBoundingRectangle1() {
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        PolyLine polyLine = new PolyLine(points);
        assertEquals(new Rectangle(new Point(10, 20), new Point(30, 40)), polyLine.getBoundingRectangle());
    }

    @Test
    public void testBoundingRectangle2() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        PolyLine polyLine = new PolyLine(points);
        assertEquals(new Rectangle(new Point(10, 10), new Point(40, 20)), polyLine.getBoundingRectangle());
    }

}
