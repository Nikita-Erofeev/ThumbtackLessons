package net.thumbtack.school.figures.v3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestLine {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testLine1() {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        Line line = new Line(startPoint, endPoint);
        assertAll(
                () -> assertEquals(10, line.getStartPoint().getX()),
                () -> assertEquals(20, line.getStartPoint().getY()),
                () -> assertEquals(30, line.getEndPoint().getX()),
                () -> assertEquals(40, line.getEndPoint().getY()),
                () -> assertEquals(28.284271, line.getLength(), DOUBLE_EPS)
        );
    }

    @Test
    public void testLine2() {
        Line line = new Line(10, 20, 30, 40);
        assertAll(
                () -> assertEquals(10, line.getStartPoint().getX()),
                () -> assertEquals(20, line.getStartPoint().getY()),
                () -> assertEquals(30, line.getEndPoint().getX()),
                () -> assertEquals(40, line.getEndPoint().getY()),
                () -> assertEquals(28.284271, line.getLength(), DOUBLE_EPS)
        );
    }

    @Test
    public void testLine3() {
        Line line = new Line(10, 10);
        assertAll(
                () -> assertEquals(0, line.getStartPoint().getX()),
                () -> assertEquals(0, line.getStartPoint().getY()),
                () -> assertEquals(10, line.getEndPoint().getX()),
                () -> assertEquals(10, line.getEndPoint().getY()),
                () -> assertEquals(14.142135, line.getLength(), DOUBLE_EPS)
        );
    }

    @Test
    public void testLine4() {
        Line line = new Line(new Point(10, 10));
        assertAll(
                () -> assertEquals(0, line.getStartPoint().getX()),
                () -> assertEquals(0, line.getStartPoint().getY()),
                () -> assertEquals(10, line.getEndPoint().getX()),
                () -> assertEquals(10, line.getEndPoint().getY()),
                () -> assertEquals(14.142135, line.getLength(), DOUBLE_EPS)
        );
    }

    @Test
    public void testLine5() {
        Line line = new Line();
        assertAll(
                () -> assertEquals(0, line.getStartPoint().getX()),
                () -> assertEquals(0, line.getStartPoint().getY()),
                () -> assertEquals(1, line.getEndPoint().getX()),
                () -> assertEquals(1, line.getEndPoint().getY()),
                () -> assertEquals(1.4142135, line.getLength(), DOUBLE_EPS)
        );
    }

    @Test
    public void testSetStartPoint() {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        Line line = new Line(startPoint, endPoint);
        line.setStartPoint(new Point(0, 0));
        assertAll(
                () -> assertEquals(0, line.getStartPoint().getX()),
                () -> assertEquals(0, line.getStartPoint().getY()),
                () -> assertEquals(30, line.getEndPoint().getX()),
                () -> assertEquals(40, line.getEndPoint().getY()),
                () -> assertEquals(50, line.getLength(), DOUBLE_EPS)
        );
    }

    @Test
    public void testSetEndPoint() {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        Line line = new Line(startPoint, endPoint);
        line.setEndPoint(new Point(0, 0));
        assertAll(
                () -> assertEquals(10, line.getStartPoint().getX()),
                () -> assertEquals(20, line.getStartPoint().getY()),
                () -> assertEquals(0, line.getEndPoint().getX()),
                () -> assertEquals(0, line.getEndPoint().getY()),
                () -> assertEquals(22.360679, line.getLength(), DOUBLE_EPS)
        );
    }

    @Test
    public void testMoveTo1() {
        Line line = new Line(10, 20, 30, 40);
        line.moveTo(100, 50);
        assertAll(
                () -> assertEquals(100, line.getStartPoint().getX()),
                () -> assertEquals(50, line.getStartPoint().getY()),
                () -> assertEquals(120, line.getEndPoint().getX()),
                () -> assertEquals(70, line.getEndPoint().getY()),
                () -> assertEquals(28.284271, line.getLength(), DOUBLE_EPS)
        );
        line.moveTo(new Point(100, 50));
        assertAll(
                () -> assertEquals(100, line.getStartPoint().getX()),
                () -> assertEquals(50, line.getStartPoint().getY()),
                () -> assertEquals(120, line.getEndPoint().getX()),
                () -> assertEquals(70, line.getEndPoint().getY()),
                () -> assertEquals(28.284271, line.getLength(), DOUBLE_EPS)
        );
    }

    @Test
    public void testMoveRel() {
        Line line = new Line(10, 20, 30, 40);
        line.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, line.getStartPoint().getX()),
                () -> assertEquals(70, line.getStartPoint().getY()),
                () -> assertEquals(130, line.getEndPoint().getX()),
                () -> assertEquals(90, line.getEndPoint().getY()),
                () -> assertEquals(28.284271, line.getLength(), DOUBLE_EPS)
        );
    }


    @Test
    public void testEqualsLine() {
        Line line1 = new Line(10, 20, 30, 40);
        Line line2 = new Line(10, 20, 30, 40);
        Line line3 = new Line(20, 30, 40, 50);
        assertEquals(line1, line2);
        assertNotEquals(line1, line3);
    }


}
