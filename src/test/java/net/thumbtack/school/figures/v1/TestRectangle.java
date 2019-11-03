package net.thumbtack.school.figures.v1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRectangle {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testRectangle1() {
        Point topLeft = new Point(10, 20);
        Point bottomRight = new Point(30, 40);
        Rectangle rect = new Rectangle(topLeft, bottomRight);
        assertAll(
                () -> assertEquals(10, rect.getTopLeft().getX()),
                () -> assertEquals(20, rect.getTopLeft().getY()),
                () -> assertEquals(30, rect.getBottomRight().getX()),
                () -> assertEquals(40, rect.getBottomRight().getY()),
                () -> assertEquals(20, rect.getLength()),
                () -> assertEquals(20, rect.getWidth())
        );
    }

    @Test
    public void testRectangle2() {
        Rectangle rect = new Rectangle(10, 20, 30, 40);
        assertAll(
                () -> assertEquals(10, rect.getTopLeft().getX()),
                () -> assertEquals(20, rect.getTopLeft().getY()),
                () -> assertEquals(30, rect.getBottomRight().getX()),
                () -> assertEquals(40, rect.getBottomRight().getY()),
                () -> assertEquals(20, rect.getLength()),
                () -> assertEquals(20, rect.getWidth())
        );
    }

    @Test
    public void testRectangle3() {
        Rectangle rect = new Rectangle(10, 20);
        assertAll(
                () -> assertEquals(0, rect.getTopLeft().getX()),
                () -> assertEquals(-20, rect.getTopLeft().getY()),
                () -> assertEquals(10, rect.getBottomRight().getX()),
                () -> assertEquals(0, rect.getBottomRight().getY()),
                () -> assertEquals(10, rect.getLength()),
                () -> assertEquals(20, rect.getWidth())
        );
    }

    @Test
    public void testRectangle4() {
        Rectangle rect = new Rectangle();
        assertAll(
                () -> assertEquals(0, rect.getTopLeft().getX()),
                () -> assertEquals(-1, rect.getTopLeft().getY()),
                () -> assertEquals(1, rect.getBottomRight().getX()),
                () -> assertEquals(0, rect.getBottomRight().getY()),
                () -> assertEquals(1, rect.getLength()),
                () -> assertEquals(1, rect.getWidth())
        );
    }

    @Test
    public void testMoveRectangle() {
        Rectangle rectangle = new Rectangle(10, 20, 30, 40);
        rectangle.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, rectangle.getTopLeft().getX()),
                () -> assertEquals(70, rectangle.getTopLeft().getY()),
                () -> assertEquals(130, rectangle.getBottomRight().getX()),
                () -> assertEquals(90, rectangle.getBottomRight().getY())
        );
        rectangle.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, rectangle.getTopLeft().getX()),
                () -> assertEquals(200, rectangle.getTopLeft().getY()),
                () -> assertEquals(120, rectangle.getBottomRight().getX()),
                () -> assertEquals(220, rectangle.getBottomRight().getY())
        );
        rectangle.moveTo(new Point(1000, 2000));
        assertAll(
                () -> assertEquals(1000, rectangle.getTopLeft().getX()),
                () -> assertEquals(2000, rectangle.getTopLeft().getY()),
                () -> assertEquals(1020, rectangle.getBottomRight().getX()),
                () -> assertEquals(2020, rectangle.getBottomRight().getY())
        );
    }

    @Test
    public void testResizeRectangle() {
        Rectangle rect = new Rectangle(10, 20, 30, 40);
        rect.resize(3);
        assertAll(
                () -> assertEquals(10, rect.getTopLeft().getX()),
                () -> assertEquals(20, rect.getTopLeft().getY()),
                () -> assertEquals(70, rect.getBottomRight().getX()),
                () -> assertEquals(80, rect.getBottomRight().getY())
        );
    }

    @Test
    public void testIsPointInsideRectangle1() {
        Rectangle rect = new Rectangle(10, 20, 30, 40);
        assertAll(
                () -> assertTrue(rect.isInside(20, 30)),
                () -> assertTrue(rect.isInside(10, 30)),
                () -> assertTrue(rect.isInside(30, 30)),
                () -> assertTrue(rect.isInside(10, 40)),
                () -> assertFalse(rect.isInside(0, 0)),
                () -> assertFalse(rect.isInside(10, 100)),
                () -> assertFalse(rect.isInside(-10, 20)),
                () -> assertFalse(rect.isInside(10, -20))
        );
    }

    @Test
    public void testIsPointInsideRectangle2() {
        Rectangle rect = new Rectangle(10, 20, 30, 40);
        assertAll(
                () -> assertTrue(rect.isInside(new Point(20, 30))),
                () -> assertTrue(rect.isInside(new Point(10, 30))),
                () -> assertTrue(rect.isInside(new Point(30, 30))),
                () -> assertTrue(rect.isInside(new Point(10, 40))),
                () -> assertFalse(rect.isInside(new Point(0, 0))),
                () -> assertFalse(rect.isInside(new Point(10, 100))),
                () -> assertFalse(rect.isInside(new Point(-10, 20))),
                () -> assertFalse(rect.isInside(new Point(10, -20)))
        );
    }

    @Test
    public void testIsIntersectsRectangle() {
        Rectangle rect = new Rectangle(10, 20, 30, 40);
        assertAll(
                () -> assertTrue(rect.isIntersects(new Rectangle(15, 25, 25, 35))),
                () -> assertTrue(rect.isIntersects(new Rectangle(-10, 20, 30, 40))),
                () -> assertTrue(rect.isIntersects(new Rectangle(10, 20, 50, 40))),
                () -> assertTrue(rect.isIntersects(new Rectangle(-10, 20, 50, 40))),
                () -> assertTrue(rect.isIntersects(new Rectangle(10, -20, 30, 40))),
                () -> assertTrue(rect.isIntersects(new Rectangle(10, 20, 30, 60))),
                () -> assertTrue(rect.isIntersects(new Rectangle(-10, -20, 50, 60))),
                () -> assertTrue(rect.isIntersects(new Rectangle(0, 10, 20, 30))),
                () -> assertTrue(rect.isIntersects(new Rectangle(20, 30, 40, 50))),
                () -> assertFalse(rect.isIntersects(new Rectangle(-40, 20, -30, 40))),
                () -> assertFalse(rect.isIntersects(new Rectangle(110, 120, 130, 140))),
                () -> assertFalse(rect.isIntersects(new Rectangle(10, 120, 30, 140))),
                () -> assertFalse(rect.isIntersects(new Rectangle(10, -40, 30, -20)))
        );
    }

    @Test
    public void testIsRectangleInsideRectangle() {
        Rectangle rect = new Rectangle(10, 20, 30, 40);
        assertAll(
                () -> assertTrue(rect.isInside(new Rectangle(15, 25, 25, 35))),
                () -> assertFalse(rect.isInside(new Rectangle(-40, 20, -30, 40))),
                () -> assertFalse(rect.isInside(new Rectangle(110, 120, 130, 140))),
                () -> assertFalse(rect.isInside(new Rectangle(10, 120, 30, 140))),
                () -> assertFalse(rect.isInside(new Rectangle(10, -40, 30, -20)))
        );
    }

    @Test
    public void testEqualsRectangle() {
        Rectangle rect1 = new Rectangle(10, 20, 30, 40);
        Rectangle rect2 = new Rectangle(10, 20, 30, 40);
        Rectangle rect3 = new Rectangle(20, 30, 40, 50);
        assertEquals(rect1, rect2);
        assertNotEquals(rect1, rect3);
    }

    @Test
    public void testAreaRectangle() {
        Rectangle rect1 = new Rectangle(10, 20, 30, 40);
        Rectangle rect2 = new Rectangle(20, 30, 40, 80);
        assertEquals(400, rect1.getArea(), DOUBLE_EPS);
        assertEquals(1000, rect2.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testPerimeterRectangle() {
        Rectangle rect1 = new Rectangle(10, 20, 30, 40);
        Rectangle rect2 = new Rectangle(20, 30, 40, 80);
        assertEquals(80, rect1.getPerimeter(), DOUBLE_EPS);
        assertEquals(140, rect2.getPerimeter(), DOUBLE_EPS);
    }

}
