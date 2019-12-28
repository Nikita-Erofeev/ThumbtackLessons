package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColoredLine {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testColoredLine1() throws ColorException {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        ColoredLine coloredLine = new ColoredLine(startPoint, endPoint, Color.RED);
        assertAll(
                () -> assertEquals(10, coloredLine.getStartPoint().getX()),
                () -> assertEquals(20, coloredLine.getStartPoint().getY()),
                () -> assertEquals(30, coloredLine.getEndPoint().getX()),
                () -> assertEquals(40, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine2() throws ColorException {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        ColoredLine coloredLine = new ColoredLine(startPoint, endPoint, "RED");
        assertAll(
                () -> assertEquals(10, coloredLine.getStartPoint().getX()),
                () -> assertEquals(20, coloredLine.getStartPoint().getY()),
                () -> assertEquals(30, coloredLine.getEndPoint().getX()),
                () -> assertEquals(40, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine3() throws ColorException {
        ColoredLine coloredLine = new ColoredLine(10, 20, 30, 40, Color.RED);
        assertAll(
                () -> assertEquals(10, coloredLine.getStartPoint().getX()),
                () -> assertEquals(20, coloredLine.getStartPoint().getY()),
                () -> assertEquals(30, coloredLine.getEndPoint().getX()),
                () -> assertEquals(40, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine4() throws ColorException {
        ColoredLine coloredLine = new ColoredLine(10, 20, 30, 40, "RED");
        assertAll(
                () -> assertEquals(10, coloredLine.getStartPoint().getX()),
                () -> assertEquals(20, coloredLine.getStartPoint().getY()),
                () -> assertEquals(30, coloredLine.getEndPoint().getX()),
                () -> assertEquals(40, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine5() throws ColorException {
        ColoredLine coloredLine = new ColoredLine(10, 10, Color.RED);
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(10, coloredLine.getEndPoint().getX()),
                () -> assertEquals(10, coloredLine.getEndPoint().getY()),
                () -> assertEquals(14.142135, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine6() throws ColorException {
        ColoredLine coloredLine = new ColoredLine(10, 10, "RED");
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(10, coloredLine.getEndPoint().getX()),
                () -> assertEquals(10, coloredLine.getEndPoint().getY()),
                () -> assertEquals(14.142135, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine7() throws ColorException {
        ColoredLine coloredLine = new ColoredLine(new Point(10, 10), Color.RED);
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(10, coloredLine.getEndPoint().getX()),
                () -> assertEquals(10, coloredLine.getEndPoint().getY()),
                () -> assertEquals(14.142135, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine8() throws ColorException {
        ColoredLine coloredLine = new ColoredLine(new Point(10, 10), "RED");
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(10, coloredLine.getEndPoint().getX()),
                () -> assertEquals(10, coloredLine.getEndPoint().getY()),
                () -> assertEquals(14.142135, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine9() throws ColorException {
        ColoredLine coloredLine = new ColoredLine(Color.RED);
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(1, coloredLine.getEndPoint().getX()),
                () -> assertEquals(1, coloredLine.getEndPoint().getY()),
                () -> assertEquals(1.4142135, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine10() throws ColorException {
        ColoredLine coloredLine = new ColoredLine("RED");
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(1, coloredLine.getEndPoint().getX()),
                () -> assertEquals(1, coloredLine.getEndPoint().getY()),
                () -> assertEquals(1.4142135, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine11() throws ColorException {
        ColoredLine coloredLine = new ColoredLine();
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(1, coloredLine.getEndPoint().getX()),
                () -> assertEquals(1, coloredLine.getEndPoint().getY()),
                () -> assertEquals(1.4142135, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testColoredLine12() {
        assertThrows(ColorException.class, () -> new ColoredLine((Color) null));
    }

    @Test
    public void testColoredCircle13() {
        assertThrows(ColorException.class, () -> new ColoredLine((String) null));
    }

    @Test
    public void testSetStartPoint() throws ColorException {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        ColoredLine coloredLine = new ColoredLine(startPoint, endPoint, Color.RED);
        coloredLine.setStartPoint(new Point(0, 0));
        assertAll(
                () -> assertEquals(0, coloredLine.getStartPoint().getX()),
                () -> assertEquals(0, coloredLine.getStartPoint().getY()),
                () -> assertEquals(30, coloredLine.getEndPoint().getX()),
                () -> assertEquals(40, coloredLine.getEndPoint().getY()),
                () -> assertEquals(50, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testSetEndPoint() throws ColorException {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        ColoredLine coloredLine = new ColoredLine(startPoint, endPoint, Color.RED);
        coloredLine.setEndPoint(new Point(0, 0));
        assertAll(
                () -> assertEquals(10, coloredLine.getStartPoint().getX()),
                () -> assertEquals(20, coloredLine.getStartPoint().getY()),
                () -> assertEquals(0, coloredLine.getEndPoint().getX()),
                () -> assertEquals(0, coloredLine.getEndPoint().getY()),
                () -> assertEquals(22.360679, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testSetColor1() throws ColorException {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        ColoredLine coloredLine = new ColoredLine(startPoint, endPoint, Color.RED);
        coloredLine.setColor(Color.GREEN);
        assertAll(
                () -> assertEquals(10, coloredLine.getStartPoint().getX()),
                () -> assertEquals(20, coloredLine.getStartPoint().getY()),
                () -> assertEquals(30, coloredLine.getEndPoint().getX()),
                () -> assertEquals(40, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.GREEN, coloredLine.getColor())
        );
    }

    @Test
    public void testSetColor2() throws ColorException {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        ColoredLine coloredLine = new ColoredLine(startPoint, endPoint, Color.RED);
        coloredLine.setColor("GREEN");
        assertAll(
                () -> assertEquals(10, coloredLine.getStartPoint().getX()),
                () -> assertEquals(20, coloredLine.getStartPoint().getY()),
                () -> assertEquals(30, coloredLine.getEndPoint().getX()),
                () -> assertEquals(40, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.GREEN, coloredLine.getColor())
        );
    }

    @Test
    public void testSetColor3() throws ColorException {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        ColoredLine coloredLine = new ColoredLine(startPoint, endPoint, Color.RED);
        assertThrows(ColorException.class, () -> coloredLine.setColor((Color) null));
    }

    @Test
    public void testSetColor4() throws ColorException {
        Point startPoint = new Point(10, 20);
        Point endPoint = new Point(30, 40);
        ColoredLine coloredLine = new ColoredLine(startPoint, endPoint, Color.RED);
        assertThrows(ColorException.class, () -> coloredLine.setColor((String) null));
    }

    @Test
    public void testMoveTo1() throws ColorException {
        ColoredLine coloredLine = new ColoredLine(10, 20, 30, 40, Color.RED);
        coloredLine.moveTo(100, 50);
        assertAll(
                () -> assertEquals(100, coloredLine.getStartPoint().getX()),
                () -> assertEquals(50, coloredLine.getStartPoint().getY()),
                () -> assertEquals(120, coloredLine.getEndPoint().getX()),
                () -> assertEquals(70, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
        coloredLine.moveTo(new Point(100, 50));
        assertAll(
                () -> assertEquals(100, coloredLine.getStartPoint().getX()),
                () -> assertEquals(50, coloredLine.getStartPoint().getY()),
                () -> assertEquals(120, coloredLine.getEndPoint().getX()),
                () -> assertEquals(70, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }

    @Test
    public void testMoveRel() throws ColorException {
        ColoredLine coloredLine = new ColoredLine(10, 20, 30, 40, Color.RED);
        coloredLine.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, coloredLine.getStartPoint().getX()),
                () -> assertEquals(70, coloredLine.getStartPoint().getY()),
                () -> assertEquals(130, coloredLine.getEndPoint().getX()),
                () -> assertEquals(90, coloredLine.getEndPoint().getY()),
                () -> assertEquals(28.284271, coloredLine.getLength(), DOUBLE_EPS),
                () -> assertEquals(Color.RED, coloredLine.getColor())
        );
    }


    @Test
    public void testEqualsColoredLine() throws ColorException {
        ColoredLine coloredLine1 = new ColoredLine(10, 20, 30, 40, Color.RED);
        ColoredLine coloredLine2 = new ColoredLine(10, 20, 30, 40, Color.RED);
        ColoredLine coloredLine3 = new ColoredLine(10, 20, 30, 40, Color.GREEN);
        ColoredLine coloredLine4 = new ColoredLine(20, 30, 40, 50, Color.RED);
        assertEquals(coloredLine1, coloredLine2);
        assertNotEquals(coloredLine1, coloredLine3);
        assertNotEquals(coloredLine1, coloredLine4);
    }


}
