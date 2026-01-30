package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    @Test
    void equalsValidation() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(-1, 0);

        assertTrue(v1.equals(v2));
        assertTrue(v1.equals(v1));
        assertFalse(v1.equals(v3));
    }

    @Test
    void toStringValidation() {
        Vector2d v1 = new Vector2d(8, -4);

        assertEquals("(8,-4)", v1.toString());
    }

    @Test
    void precedesValidation() {
        Vector2d v1 = new Vector2d(2, 5);
        Vector2d v2 = new Vector2d(5, 5);

        assertTrue(v1.precedes(v2));
        assertTrue(v1.precedes(v1));
    }

    @Test
    void precedesFalseValidation() {
        Vector2d v1 = new Vector2d(7, 3);
        Vector2d v2 = new Vector2d(4, 8);

        assertFalse(v1.precedes(v2));
    }

    @Test
    void followsValidation() {
        Vector2d v1 = new Vector2d(6, 9);
        Vector2d v2 = new Vector2d(2, 4);

        assertTrue(v1.follows(v2));
        assertTrue(v1.follows(v1));
    }

    @Test
    void followsFalseValidation() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(3, 2);

        assertFalse(v1.follows(v2));
    }

    @Test
    void upperRightValidation() {
        Vector2d v1 = new Vector2d(3, 7);
        Vector2d v2 = new Vector2d(8, 2);
        Vector2d expected = new Vector2d(8, 7);

        assertEquals(expected, v1.upperRight(v2));
    }

    @Test
    void lowerLeftValidation() {
        Vector2d v1 = new Vector2d(3, 7);
        Vector2d v2 = new Vector2d(8, 2);
        Vector2d expected = new Vector2d(3, 2);

        assertEquals(expected, v1.lowerLeft(v2));
    }

    @Test
    void addValidation() {
        Vector2d v1 = new Vector2d(-2, 4);
        Vector2d v2 = new Vector2d(6, -1);
        Vector2d expected = new Vector2d(4, 3);

        assertEquals(expected, v1.add(v2));
    }

    @Test
    void subtractValidation() {
        Vector2d v1 = new Vector2d(10, 3);
        Vector2d v2 = new Vector2d(4, 9);

        Vector2d expected1 = new Vector2d(6, -6);
        Vector2d expected2 = new Vector2d(-6, 6);

        assertEquals(expected1, v1.subtract(v2));
        assertEquals(expected2, v2.subtract(v1));
    }

    @Test
    void oppositeValidation() {
        Vector2d v1 = new Vector2d(5, -8);
        Vector2d zero = new Vector2d(0, 0);
        Vector2d expected = new Vector2d(-5, 8);

        assertEquals(expected, v1.opposite());
        assertEquals(zero, zero.opposite());
    }
}