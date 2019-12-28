package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColoredPolyLine {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testColoredPolyLine1() throws ColorException {
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, Color.RED);
        assertAll(
                () -> assertEquals(10, coloredPolyLine.getPoints()[0].getX()),
                () -> assertEquals(20, coloredPolyLine.getPoints()[0].getY()),
                () -> assertEquals(30, coloredPolyLine.getPoints()[1].getX()),
                () -> assertEquals(40, coloredPolyLine.getPoints()[1].getY()),
                () -> assertEquals(28.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, coloredPolyLine.getPoints()),
                () -> assertEquals(Color.RED, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testColoredPolyLine2() throws ColorException {
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, "RED");
        assertAll(
                () -> assertEquals(10, coloredPolyLine.getPoints()[0].getX()),
                () -> assertEquals(20, coloredPolyLine.getPoints()[0].getY()),
                () -> assertEquals(30, coloredPolyLine.getPoints()[1].getX()),
                () -> assertEquals(40, coloredPolyLine.getPoints()[1].getY()),
                () -> assertEquals(28.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, coloredPolyLine.getPoints()),
                () -> assertEquals(Color.RED, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testColoredPolyLine3() throws ColorException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, Color.RED);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], coloredPolyLine.getPoints()[i].getX());
                        assertEquals(y[i], coloredPolyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, coloredPolyLine.getPoints()),
                () -> assertEquals(Color.RED, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testColoredPolyLine4() throws ColorException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, "RED");
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], coloredPolyLine.getPoints()[i].getX());
                        assertEquals(y[i], coloredPolyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, coloredPolyLine.getPoints()),
                () -> assertEquals(Color.RED, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testColoredPolyLine5() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        assertThrows(ColorException.class, () -> new ColoredPolyLine(points, (Color) null));
    }

    @Test
    public void testColoredPolyLine6() {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        assertThrows(ColorException.class, () -> new ColoredPolyLine(points, (String) null));
    }

    @Test
    public void testSetPoints() throws ColorException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, Color.RED);
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
                () -> assertEquals(Color.RED, coloredPolyLine.getColor())
        );
    }


    @Test
    public void testMoveTo1() throws ColorException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, Color.RED);
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
                () -> assertEquals(Color.RED, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testMoveTo2() throws ColorException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, Color.RED);
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
                () -> assertEquals(Color.RED, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testMoveRel() throws ColorException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, Color.RED);
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
                () -> assertEquals(Color.RED, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testSetColor1() throws ColorException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, Color.RED);
        coloredPolyLine.setColor(Color.GREEN);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], coloredPolyLine.getPoints()[i].getX());
                        assertEquals(y[i], coloredPolyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, coloredPolyLine.getPoints()),
                () -> assertEquals(Color.GREEN, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testSetColor2() throws ColorException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, "RED");
        coloredPolyLine.setColor(Color.GREEN);
        assertAll(
                () -> {
                    for (int i = 0; i < x.length; i++) {
                        assertEquals(x[i], coloredPolyLine.getPoints()[i].getX());
                        assertEquals(y[i], coloredPolyLine.getPoints()[i].getY());
                    }
                },
                () -> assertEquals(48.284271, coloredPolyLine.getLength(), DOUBLE_EPS),
                () -> assertNotSame(points, coloredPolyLine.getPoints()),
                () -> assertEquals(Color.GREEN, coloredPolyLine.getColor())
        );
    }

    @Test
    public void testSetColor3() throws ColorException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, "RED");
        assertThrows(ColorException.class, () -> coloredPolyLine.setColor((Color) null));
    }

    @Test
    public void testSetColor4() throws ColorException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, "RED");
        assertThrows(ColorException.class, () -> coloredPolyLine.setColor((String) null));
    }

    @Test
    public void testEqualsColoredPolyLine() throws ColorException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        Point[] newPoints = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
            newPoints[i] = new Point(x[x.length - i - 1], y[y.length - i - 1]);
        }
        ColoredPolyLine coloredPolyLine1 = new ColoredPolyLine(points, Color.RED);
        ColoredPolyLine coloredPolyLine2 = new ColoredPolyLine(points, Color.RED);
        ColoredPolyLine coloredPolyLine3 = new ColoredPolyLine(points, Color.GREEN);
        ColoredPolyLine coloredPolyLine4 = new ColoredPolyLine(newPoints, Color.RED);
        assertEquals(coloredPolyLine1, coloredPolyLine2);
        assertNotEquals(coloredPolyLine1, coloredPolyLine3);
        assertNotEquals(coloredPolyLine1, coloredPolyLine4);
    }

    @Test
    public void testBoundingRectangle1() throws ColorException {
        Point[] points = {new Point(10, 20), new Point(30, 40)};
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, Color.RED);
        assertEquals(new Rectangle(new Point(10, 20), new Point(30, 40)), coloredPolyLine.getBoundingRectangle());
    }

    @Test
    public void testBoundingRectangle2() throws ColorException {
        int[] x = {10, 20, 30, 30, 40};
        int[] y = {10, 20, 10, 20, 20};
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point(x[i], y[i]);
        }
        ColoredPolyLine coloredPolyLine = new ColoredPolyLine(points, Color.RED);
        assertEquals(new Rectangle(new Point(10, 10), new Point(40, 20)), coloredPolyLine.getBoundingRectangle());
    }

}
