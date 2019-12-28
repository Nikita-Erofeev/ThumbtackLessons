package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColoredCircle {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testColoredCircle1() throws ColorException {
        Point center = new Point(10, 20);
        ColoredCircle coloredCircle = new ColoredCircle(center, 10, Color.RED);
        assertAll(
                () -> assertEquals(10, coloredCircle.getCenter().getX()),
                () -> assertEquals(20, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius()),
                () -> assertEquals(Color.RED, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle2() throws ColorException {
        Point center = new Point(10, 20);
        ColoredCircle coloredCircle = new ColoredCircle(center, 10, "RED");
        assertAll(
                () -> assertEquals(10, coloredCircle.getCenter().getX()),
                () -> assertEquals(20, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius()),
                () -> assertEquals(Color.RED, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle3() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, Color.RED);
        assertAll(
                () -> assertEquals(10, coloredCircle.getCenter().getX()),
                () -> assertEquals(20, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius()),
                () -> assertEquals(Color.RED, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle4() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, "RED");
        assertAll(
                () -> assertEquals(10, coloredCircle.getCenter().getX()),
                () -> assertEquals(20, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius()),
                () -> assertEquals(Color.RED, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle5() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle(10, Color.RED);
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius()),
                () -> assertEquals(Color.RED, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle6() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle(10, "RED");
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius()),
                () -> assertEquals(Color.RED, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle7() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle(Color.RED);
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(1, coloredCircle.getRadius()),
                () -> assertEquals(Color.RED, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle8() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle("RED");
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(1, coloredCircle.getRadius()),
                () -> assertEquals(Color.RED, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle9() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle();
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(1, coloredCircle.getRadius()),
                () -> assertEquals(Color.RED, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle10() {
        assertThrows(ColorException.class, () -> new ColoredCircle((Color) null));
    }

    @Test
    public void testColoredCircle11() {
        assertThrows(ColorException.class, () -> new ColoredCircle((String) null));
    }

    @Test
    public void testSetCenterAndRadius() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, Color.RED);
        coloredCircle.setCenter(new Point(100, 50));
        coloredCircle.setRadius(200);
        assertAll(
                () -> assertEquals(100, coloredCircle.getCenter().getX()),
                () -> assertEquals(50, coloredCircle.getCenter().getY()),
                () -> assertEquals(200, coloredCircle.getRadius())
        );
    }

    @Test
    public void testSetColor1() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle();
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(1, coloredCircle.getRadius()),
                () -> assertEquals(Color.RED, coloredCircle.getColor())
        );
        coloredCircle.setColor(Color.GREEN);
        assertEquals(Color.GREEN, coloredCircle.getColor());
    }

    @Test
    public void testSetColor2() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle();
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(1, coloredCircle.getRadius()),
                () -> assertEquals(Color.RED, coloredCircle.getColor())
        );
        coloredCircle.setColor("GREEN");
        assertEquals(Color.GREEN, coloredCircle.getColor());
    }

    @Test
    public void testSetColor3() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle("GREEN");
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(1, coloredCircle.getRadius()),
                () -> assertEquals(Color.GREEN, coloredCircle.getColor())
        );
        assertThrows(ColorException.class, () -> coloredCircle.setColor((Color) null));
    }

    @Test
    public void testSetColor4() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle("GREEN");
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(1, coloredCircle.getRadius()),
                () -> assertEquals(Color.GREEN, coloredCircle.getColor())
        );
        assertThrows(ColorException.class, () -> coloredCircle.setColor((String) null));
    }

    @Test
    public void testMoveColoredCircle() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, Color.RED);
        coloredCircle.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, coloredCircle.getCenter().getX()),
                () -> assertEquals(70, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius())
        );
        coloredCircle.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, coloredCircle.getCenter().getX()),
                () -> assertEquals(200, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius())
        );
        coloredCircle.moveTo(new Point(1000, 2000));
        assertAll(
                () -> assertEquals(1000, coloredCircle.getCenter().getX()),
                () -> assertEquals(2000, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius())
        );
    }

    @Test
    public void testResizeColoredCircle() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, Color.RED);
        coloredCircle.resize(5);
        assertAll(
                () -> assertEquals(10, coloredCircle.getCenter().getX()),
                () -> assertEquals(20, coloredCircle.getCenter().getY()),
                () -> assertEquals(50, coloredCircle.getRadius())
        );
    }


    @Test
    public void testAreaColoredCircle() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, Color.RED);
        assertEquals(Math.PI * 100, coloredCircle.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testPerimeterColoredCircle() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, Color.RED);
        assertEquals(2 * Math.PI * 10, coloredCircle.getPerimeter(), DOUBLE_EPS);
    }

    @Test
    public void testIsPoint2DInsideColoredCircle1() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle(10, 10, 10, Color.RED);
        assertAll(
                () -> assertTrue(coloredCircle.isInside(10, 10)),
                () -> assertTrue(coloredCircle.isInside(20, 10)),
                () -> assertTrue(coloredCircle.isInside(10, 20)),
                () -> assertTrue(coloredCircle.isInside(15, 15)),
                () -> assertFalse(coloredCircle.isInside(18, 18))
        );
    }

    @Test
    public void testIsPoint2DInsideColoredCircle2() throws ColorException {
        ColoredCircle coloredCircle = new ColoredCircle(new Point(10, 10), 10, Color.RED);
        assertAll(
                () -> assertTrue(coloredCircle.isInside(new Point(10, 10))),
                () -> assertTrue(coloredCircle.isInside(new Point(20, 10))),
                () -> assertTrue(coloredCircle.isInside(new Point(10, 20))),
                () -> assertTrue(coloredCircle.isInside(new Point(15, 15))),
                () -> assertFalse(coloredCircle.isInside(new Point(18, 18)))
        );
    }

    @Test
    public void testEqualsColoredCircle() throws ColorException {
        ColoredCircle coloredCircle1 = new ColoredCircle(new Point(10, 10), 10, Color.RED);
        ColoredCircle coloredCircle2 = new ColoredCircle(new Point(10, 10), 10, Color.RED);
        ColoredCircle coloredCircle3 = new ColoredCircle(new Point(10, 10), 20, Color.RED);
        ColoredCircle coloredCircle4 = new ColoredCircle(new Point(0, 0), 10, Color.RED);
        ColoredCircle coloredCircle5 = new ColoredCircle(new Point(10, 10), 10, Color.GREEN);
        assertEquals(coloredCircle1, coloredCircle2);
        assertNotEquals(coloredCircle1, coloredCircle3);
        assertNotEquals(coloredCircle1, coloredCircle4);
        assertNotEquals(coloredCircle1, coloredCircle5);
    }

}
