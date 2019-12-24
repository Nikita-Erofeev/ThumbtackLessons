package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColoredTriangle {

    @Test
    public void testColoredTriangle1() {
        Point first = new Point(1, 1);
        Point second = new Point(0, 5);
        Point third = new Point(5, 0);
        ColoredTriangle triangle = new ColoredTriangle(first, second, third, 1);
        assertAll(
                () -> assertEquals(1, triangle.getFirstPoint().getX()),
                () -> assertEquals(1, triangle.getFirstPoint().getY()),
                () -> assertEquals(0, triangle.getSecondPoint().getX()),
                () -> assertEquals(5, triangle.getSecondPoint().getY()),
                () -> assertEquals(5, triangle.getThirdPoint().getX()),
                () -> assertEquals(0, triangle.getThirdPoint().getY()),
                () -> assertEquals(1, triangle.getColor())
        );
    }

    @Test
    public void testColoredTriangle2() {
        ColoredTriangle triangle2 = new ColoredTriangle(1, 1, 0, 5, 5, 0, 1);
        assertAll(
                () -> assertEquals(1, triangle2.getFirstPoint().getX()),
                () -> assertEquals(1, triangle2.getFirstPoint().getY()),
                () -> assertEquals(0, triangle2.getSecondPoint().getX()),
                () -> assertEquals(5, triangle2.getSecondPoint().getY()),
                () -> assertEquals(5, triangle2.getThirdPoint().getX()),
                () -> assertEquals(0, triangle2.getThirdPoint().getY()),
                () -> assertEquals(1, triangle2.getColor())
        );
    }

    @Test
    public void testColoredTriangle3() {
        ColoredTriangle triangle = new ColoredTriangle(3);
        assertAll(
                () -> assertEquals(0, triangle.getFirstPoint().getX()),
                () -> assertEquals(-1, triangle.getFirstPoint().getY()),
                () -> assertEquals(1, triangle.getSecondPoint().getX()),
                () -> assertEquals(0, triangle.getSecondPoint().getY()),
                () -> assertEquals(0, triangle.getThirdPoint().getX()),
                () -> assertEquals(0, triangle.getThirdPoint().getY()),
                () -> assertEquals(3, triangle.getColor())
        );
    }

    @Test
    public void testColoredTriangle4() {
        ColoredTriangle triangle = new ColoredTriangle();
        assertAll(
                () -> assertEquals(0, triangle.getFirstPoint().getX()),
                () -> assertEquals(-1, triangle.getFirstPoint().getY()),
                () -> assertEquals(1, triangle.getSecondPoint().getX()),
                () -> assertEquals(0, triangle.getSecondPoint().getY()),
                () -> assertEquals(0, triangle.getThirdPoint().getX()),
                () -> assertEquals(0, triangle.getThirdPoint().getY()),
                () -> assertEquals(1, triangle.getColor())
        );
    }

    @Test
    public void testSetColor(){
        ColoredTriangle triangle = new ColoredTriangle();
        assertAll(
                () -> assertEquals(0, triangle.getFirstPoint().getX()),
                () -> assertEquals(-1, triangle.getFirstPoint().getY()),
                () -> assertEquals(1, triangle.getSecondPoint().getX()),
                () -> assertEquals(0, triangle.getSecondPoint().getY()),
                () -> assertEquals(0, triangle.getThirdPoint().getX()),
                () -> assertEquals(0, triangle.getThirdPoint().getY()),
                () -> assertEquals(1, triangle.getColor())
        );
        triangle.setColor(15);
        assertEquals(15,triangle.getColor());
    }


    @Test
    public void testAreaColoredTriangle() {
        ColoredTriangle triangle1 = new ColoredTriangle();
        ColoredTriangle triangle2 = new ColoredTriangle(0, 0, 0, 5, 5, 0,1);
        ColoredTriangle triangle3 = new ColoredTriangle(500, 500, 100, 0, 0, 100,1);
        assertEquals(0.5, triangle1.getArea());
        assertEquals(12.5, triangle2.getArea());
        assertEquals(45000, triangle3.getArea());
    }

    @Test
    public void testPerimeterColoredTriangle() {
        ColoredTriangle triangle1 = new ColoredTriangle();
        ColoredTriangle triangle2 = new ColoredTriangle(0, 0, 0, 5, 5, 0,1);
        ColoredTriangle triangle3 = new ColoredTriangle(500, 500, 100, 0, 0, 100,1);
        assertEquals(3.41, (double) Math.round(triangle1.getPerimeter() * 100) / 100);
        assertEquals(17.07, (double) Math.round(triangle2.getPerimeter() * 100) / 100);
        assertEquals(1422.05, (double) Math.round(triangle3.getPerimeter() * 100) / 100);
    }

    @Test
    public void testMoveColoredTriangle() {
        ColoredTriangle triangle = new ColoredTriangle(0, 0, 0, 5, 5, 0,1);
        triangle.moveRel(100, 50);
        assertAll(
                () -> assertEquals(100, triangle.getFirstPoint().getX()),
                () -> assertEquals(50, triangle.getFirstPoint().getY()),
                () -> assertEquals(100, triangle.getSecondPoint().getX()),
                () -> assertEquals(55, triangle.getSecondPoint().getY()),
                () -> assertEquals(105, triangle.getThirdPoint().getX()),
                () -> assertEquals(50, triangle.getThirdPoint().getY())
        );
        triangle.moveTo(35, 10);
        assertAll(
                () -> assertEquals(35, triangle.getFirstPoint().getX()),
                () -> assertEquals(10, triangle.getFirstPoint().getY()),
                () -> assertEquals(35, triangle.getSecondPoint().getX()),
                () -> assertEquals(15, triangle.getSecondPoint().getY()),
                () -> assertEquals(40, triangle.getThirdPoint().getX()),
                () -> assertEquals(10, triangle.getThirdPoint().getY())
        );
        triangle.moveTo(new Point(1000, 300));
        assertAll(
                () -> assertEquals(1000, triangle.getFirstPoint().getX()),
                () -> assertEquals(300, triangle.getFirstPoint().getY()),
                () -> assertEquals(1000, triangle.getSecondPoint().getX()),
                () -> assertEquals(305, triangle.getSecondPoint().getY()),
                () -> assertEquals(1005, triangle.getThirdPoint().getX()),
                () -> assertEquals(300, triangle.getThirdPoint().getY())
        );
    }

    @Test
    public void isPointInsideColoredTriangle1() {
        ColoredTriangle triangle = new ColoredTriangle(0, 0, 0, 5, 5, 0,1);
        assertAll(
                () -> assertTrue(triangle.isInside(0, 0)),
                () -> assertTrue(triangle.isInside(1, 1)),
                () -> assertFalse(triangle.isInside(4, 2)),
                () -> assertTrue(triangle.isInside(0, 5)),
                () -> assertTrue(triangle.isInside(3, 2)),
                () -> assertFalse(triangle.isInside(5, 1)),
                () -> assertFalse(triangle.isInside(-1, 0)),
                () -> assertFalse(triangle.isInside(0, -1))
        );
    }

    @Test
    public void isPointInsideColoredTriangle2() {
        ColoredTriangle triangle = new ColoredTriangle(0, 0, 0, 5, 5, 0,1);
        assertAll(
                () -> assertTrue(triangle.isInside(new Point(0, 0))),
                () -> assertTrue(triangle.isInside(new Point(1, 1))),
                () -> assertFalse(triangle.isInside(new Point(4, 2))),
                () -> assertTrue(triangle.isInside(new Point(0, 5))),
                () -> assertTrue(triangle.isInside(new Point(3, 2))),
                () -> assertFalse(triangle.isInside(new Point(5, 1))),
                () -> assertFalse(triangle.isInside(new Point(-1, 0))),
                () -> assertFalse(triangle.isInside(new Point(0, -1)))
        );
    }

    @Test
    public void testEqualsColoredTriangle() {
        ColoredTriangle triangle1 = new ColoredTriangle(0, 0, 0, 5, 5, 0,1);
        ColoredTriangle triangle2 = new ColoredTriangle(0, 0, 0, 5, 5, 0,1);
        ColoredTriangle triangle3 = new ColoredTriangle(0, 1, 0, 5, 5, 0,3);
        assertEquals(triangle1,triangle2);
        assertNotEquals(triangle1,triangle3);
    }
}
