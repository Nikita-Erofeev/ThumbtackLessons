package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestClosedFigure {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testCompareArea1() {
        ClosedFigure rect1 = new Rectangle(0, 0, 10, 10);
        ClosedFigure rect2 = new Rectangle(10, 10, 20, 20);
        assertEquals(rect1.getArea(), rect2.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testCompareArea2() throws ColorException {
        ClosedFigure rect1 = new Rectangle(0, 0, 10, 10);
        ClosedFigure rect2 = new ColoredRectangle(0, 0, 10, 10, Color.RED);
        assertEquals(rect1.getArea(), rect2.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testIsInside1() {
        ClosedFigure[] figures = new ClosedFigure[2];
        figures[0] = new Circle(0, 0, 10);
        figures[1] = new Rectangle(-10, -20, 10, 20);
        Point point1 = new Point(2, 0);
        Point point2 = new Point(-1, 0);
        Point point3 = new Point(0, 2);
        for (ClosedFigure figure : figures)
            assertTrue(figure.isInside(0, 0));

    }

    @Test
    public void testIsInside2() {
        ClosedFigure[] figures = new ClosedFigure[2];
        figures[0] = new Circle(0, 0, 10);
        figures[1] = new Rectangle(-10, -20, 10, 20);
        Point point1 = new Point(2, 0);
        Point point2 = new Point(-1, 0);
        Point point3 = new Point(0, 2);
        for (ClosedFigure figure : figures)
            assertTrue(figure.isInside(new Point(0, 0)));
    }

    @Test
    public void testMoveRel() {
        Rectangle rect = new Rectangle(0, 0, 10, 10);
        Figure figure = rect;
        figure.moveRel(10, 10);
        assertAll(
                () -> assertEquals(10, rect.getTopLeft().getX()),
                () -> assertEquals(10, rect.getTopLeft().getY()),
                () -> assertEquals(20, rect.getBottomRight().getX()),
                () -> assertEquals(20, rect.getBottomRight().getY())
        );
    }

    @Test
    public void testMoveTo1() {
        Rectangle rect = new Rectangle(0, 0, 10, 10);
        Figure figure = rect;
        figure.moveTo(10, 10);
        assertAll(
                () -> assertEquals(10, rect.getTopLeft().getX()),
                () -> assertEquals(10, rect.getTopLeft().getY()),
                () -> assertEquals(20, rect.getBottomRight().getX()),
                () -> assertEquals(20, rect.getBottomRight().getY())
        );
    }

    @Test
    public void testMoveTo2() {
        Rectangle rect = new Rectangle(0, 0, 10, 10);
        Figure figure = rect;
        figure.moveTo(new Point(10, 10));
        assertAll(
                () -> assertEquals(10, rect.getTopLeft().getX()),
                () -> assertEquals(10, rect.getTopLeft().getY()),
                () -> assertEquals(20, rect.getBottomRight().getX()),
                () -> assertEquals(20, rect.getBottomRight().getY())
        );
    }
}
