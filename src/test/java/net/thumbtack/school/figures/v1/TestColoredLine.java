package net.thumbtack.school.figures.v1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColoredLine {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testColoredLine1() {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        ColoredLine coloredLine = new ColoredLine(startPoint, endPoint, 1);
        assertAll(
                () -> assertEquals(10, coloredLine.getStartPoint().getX()),
                () -> assertEquals(20, coloredLine.getStartPoint().getY()),
                () -> assertEquals(30, coloredLine.getEndPoint().getX()),
                () -> assertEquals(40, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(1, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine2() {
        ColoredLine coloredLine = new ColoredLine(10, 20, 30, 40, 1);
        assertAll(
                () -> assertEquals(10, coloredLine.getStartPoint().getX()),
                () -> assertEquals(20, coloredLine.getStartPoint().getY()),
                () -> assertEquals(30, coloredLine.getEndPoint().getX()),
                () -> assertEquals(40, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(1, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine3() {
        ColoredLine coloredLine = new ColoredLine(10, 10, 1);
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(10, coloredLine.getEndPoint().getX()),
                () -> assertEquals(10, coloredLine.getEndPoint().getY()),
                () -> assertEquals(14.142135, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(1, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine4() {
        ColoredLine coloredLine = new ColoredLine(new Point(10, 10), 1);
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(10, coloredLine.getEndPoint().getX()),
                () -> assertEquals(10, coloredLine.getEndPoint().getY()),
                () -> assertEquals(14.142135, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(1, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine5() {
        ColoredLine coloredLine = new ColoredLine(1);
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(1, coloredLine.getEndPoint().getX()),
                () -> assertEquals(1, coloredLine.getEndPoint().getY()),
                () -> assertEquals(1.4142135, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(1, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine6() {
        ColoredLine coloredLine = new ColoredLine();
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(1, coloredLine.getEndPoint().getX()),
                () -> assertEquals(1, coloredLine.getEndPoint().getY()),
                () -> assertEquals(1.4142135, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(1, coloredLine.getColor())
        );
    }

    @Test
    public void testSetStartPoint() {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        ColoredLine coloredLine = new ColoredLine(startPoint, endPoint, 1);
        coloredLine.setStartPoint(new Point(0, 0));
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(30, coloredLine.getEndPoint().getX()),
                () -> assertEquals(40, coloredLine.getEndPoint().getY()),
                () -> assertEquals(50, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(1, coloredLine.getColor())
        );
    }

    @Test
    public void testSetEndPoint() {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        ColoredLine coloredLine = new ColoredLine(startPoint, endPoint, 1);
        coloredLine.setEndPoint(new Point(0, 0));
        assertAll(
                () -> assertEquals(10, coloredLine.getStartPoint().getX()),
                () -> assertEquals(20, coloredLine.getStartPoint().getY()),
                () -> assertEquals(0, coloredLine.getEndPoint().getX()),
                () -> assertEquals(0, coloredLine.getEndPoint().getY()),
                () -> assertEquals(22.360679, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(1, coloredLine.getColor())
        );
    }

    @Test
    public void testSetColor() {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        ColoredLine coloredLine = new ColoredLine(startPoint, endPoint, 1);
        coloredLine.setColor(2);
        assertAll(
                () -> assertEquals(10, coloredLine.getStartPoint().getX()),
                () -> assertEquals(20, coloredLine.getStartPoint().getY()),
                () -> assertEquals(30, coloredLine.getEndPoint().getX()),
                () -> assertEquals(40, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(2, coloredLine.getColor())
        );
    }

    @Test
    public void testMoveTo1() {
        ColoredLine coloredLine = new ColoredLine(10, 20, 30, 40, 1);
        coloredLine.moveTo(100, 50);
        assertAll(
                () -> assertEquals(100, coloredLine.getStartPoint().getX()),
                () -> assertEquals(50, coloredLine.getStartPoint().getY()),
                () -> assertEquals(120, coloredLine.getEndPoint().getX()),
                () -> assertEquals(70, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(1, coloredLine.getColor())
        );
        coloredLine.moveTo(new Point(100, 50));
        assertAll(
                () -> assertEquals(100, coloredLine.getStartPoint().getX()),
                () -> assertEquals(50, coloredLine.getStartPoint().getY()),
                () -> assertEquals(120, coloredLine.getEndPoint().getX()),
                () -> assertEquals(70, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(1, coloredLine.getColor())
        );
    }

    @Test
    public void testMoveRel() {
        ColoredLine coloredLine = new ColoredLine(10, 20, 30, 40, 1);
        coloredLine.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, coloredLine.getStartPoint().getX()),
                () -> assertEquals(70, coloredLine.getStartPoint().getY()),
                () -> assertEquals(130, coloredLine.getEndPoint().getX()),
                () -> assertEquals(90, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(1, coloredLine.getColor())
        );
    }


    @Test
    public void testEqualsColoredLine() {
        ColoredLine coloredLine1 = new ColoredLine(10, 20, 30, 40, 1);
        ColoredLine coloredLine2 = new ColoredLine(10, 20, 30, 40, 1);
        ColoredLine coloredLine3 = new ColoredLine(10, 20, 30, 40, 2);
        ColoredLine coloredLine4 = new ColoredLine(20, 30, 40, 50, 1);
        assertEquals(coloredLine1, coloredLine2);
        assertNotEquals(coloredLine1, coloredLine3);
        assertNotEquals(coloredLine1, coloredLine4);
    }


}
