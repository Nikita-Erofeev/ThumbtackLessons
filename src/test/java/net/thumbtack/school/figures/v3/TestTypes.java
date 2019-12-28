package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.iface.v3.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;


public class TestTypes {

    @Test
    public void testTypes() throws NoSuchMethodException {
        assertTrue(Colored.class.isInterface());
        assertTrue(Color.class.isEnum());
        assertTrue(Exception.class.isAssignableFrom(ColorException.class));
        assertTrue(Resizable.class.isInterface());
        assertTrue(HasMetrics.class.isInterface());
        assertTrue(Movable.class.isInterface());
        assertTrue(HasMetrics.class.isAssignableFrom(ClosedFigure.class));
        assertFalse(Colored.class.isAssignableFrom(Figure.class));
        assertTrue(Colored.class.isAssignableFrom(ColoredCircle.class));
        assertTrue(Colored.class.isAssignableFrom(ColoredRectangle.class));
        assertTrue(Colored.class.isAssignableFrom(ColoredLine.class));
        assertTrue(Colored.class.isAssignableFrom(ColoredPolyLine.class));
        assertTrue(Resizable.class.isAssignableFrom(ClosedFigure.class));
        assertTrue(Movable.class.isAssignableFrom(Figure.class));
        assertTrue(Figure.class.isAssignableFrom(Rectangle.class));
        assertTrue(Figure.class.isAssignableFrom(Circle.class));
        assertTrue(ClosedFigure.class.isAssignableFrom(Rectangle.class));
        assertTrue(ClosedFigure.class.isAssignableFrom(Circle.class));
        assertFalse(ClosedFigure.class.isAssignableFrom(Line.class));
        assertFalse(ClosedFigure.class.isAssignableFrom(PolyLine.class));
        assertFalse(Movable.class.isAssignableFrom(Resizable.class));
        assertTrue(Movable.class.isAssignableFrom(Point.class));
        assertFalse(Colored.class.isAssignableFrom(Point.class));
        assertFalse(HasMetrics.class.isAssignableFrom(Point.class));
        assertNotEquals(0, Figure.class.getModifiers() & Modifier.ABSTRACT);
        assertNotEquals(0, ClosedFigure.class.getModifiers() & Modifier.ABSTRACT);
        assertEquals(0, Movable.class.getMethod("moveTo", Point.class).getModifiers() & Modifier.ABSTRACT);
        assertNotEquals(0, Movable.class.getMethod("moveTo", int.class, int.class).getModifiers() & Modifier.ABSTRACT);
        assertNotEquals(0, Movable.class.getMethod("moveRel", int.class, int.class).getModifiers() & Modifier.ABSTRACT);
        assertEquals(0, ClosedFigure.class.getMethod("isInside", Point.class).getModifiers() & Modifier.ABSTRACT);
        assertNotEquals(0, ClosedFigure.class.getMethod("isInside", int.class, int.class).getModifiers() & Modifier.ABSTRACT);
    }
}
