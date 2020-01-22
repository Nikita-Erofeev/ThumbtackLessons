package net.thumbtack.school.misc.v3;

import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.misc.v3.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTriangle {

    @Test
    public void testTriangle1() {
        Triangle triangle = new Triangle();
        assertAll(
                () -> assertEquals(0, triangle.getFirstPoint().getX()),
                () -> assertEquals(-1, triangle.getFirstPoint().getY()),
                () -> assertEquals(1, triangle.getSecondPoint().getX()),
                () -> assertEquals(0, triangle.getSecondPoint().getY()),
                () -> assertEquals(0, triangle.getThirdPoint().getX()),
                () -> assertEquals(0, triangle.getThirdPoint().getY())
        );
    }

    @Test
    public void testTriangle2() {
        Point first = new Point(1, 1);
        Point second = new Point(7, 7);
        Point third = new Point(7, -2);
        Triangle triangle = new Triangle(first, second, third);
        assertAll(
                () -> assertEquals(1, triangle.getFirstPoint().getX()),
                () -> assertEquals(1, triangle.getFirstPoint().getY()),
                () -> assertEquals(7, triangle.getSecondPoint().getX()),
                () -> assertEquals(7, triangle.getSecondPoint().getY()),
                () -> assertEquals(7, triangle.getThirdPoint().getX()),
                () -> assertEquals(-2, triangle.getThirdPoint().getY())
        );
    }

    @Test
    public void testTriangle3() {
        Triangle triangle = new Triangle(1, 2, 3, 4, 5, 6);
        assertAll(
                () -> assertEquals(1, triangle.getFirstPoint().getX()),
                () -> assertEquals(2, triangle.getFirstPoint().getY()),
                () -> assertEquals(3, triangle.getSecondPoint().getX()),
                () -> assertEquals(4, triangle.getSecondPoint().getY()),
                () -> assertEquals(5, triangle.getThirdPoint().getX()),
                () -> assertEquals(6, triangle.getThirdPoint().getY())
        );
    }

    @Test
    public void testAreaTriangle() {
        Triangle triangle1 = new Triangle();
        Triangle triangle2 = new Triangle(0, 0, 0, 5, 5, 0);
        Triangle triangle3 = new Triangle(500, 500, 100, 0, 0, 100);
        assertEquals(0.5, triangle1.getArea());
        assertEquals(12.5, triangle2.getArea());
        assertEquals(45000, triangle3.getArea());
    }

    @Test
    public void testPerimeterTriangle() {
        Triangle triangle1 = new Triangle();
        Triangle triangle2 = new Triangle(0, 0, 0, 5, 5, 0);
        Triangle triangle3 = new Triangle(500, 500, 100, 0, 0, 100);
        assertEquals(3.41, (double) Math.round(triangle1.getPerimeter() * 100) / 100);
        assertEquals(17.07, (double) Math.round(triangle2.getPerimeter() * 100) / 100);
        assertEquals(1422.05, (double) Math.round(triangle3.getPerimeter() * 100) / 100);
    }

    @Test
    public void testMoveTriangle() {
        Triangle triangle = new Triangle(0, 0, 0, 5, 5, 0);
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
    public void isPointInsideTriangle1() {
        Triangle triangle = new Triangle(0, 0, 0, 5, 5, 0);
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
    public void isPointInsideTriangle2() {
        Triangle triangle = new Triangle(0, 0, 0, 5, 5, 0);
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
    public void testEqualsTriangle() {
        Triangle triangle1 = new Triangle(0, 0, 0, 5, 5, 0);
        Triangle triangle2 = new Triangle(0, 0, 0, 5, 5, 0);
        Triangle triangle3 = new Triangle(0, 1, 0, 5, 5, 0);
        assertEquals(triangle1,triangle2);
        assertNotEquals(triangle1,triangle3);
    }
}