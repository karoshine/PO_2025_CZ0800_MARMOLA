package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void subscribingObservers() {
        RectangularMap rectangularMap = new RectangularMap(8, 4);
        ConsoleMapDisplay observer1 = new ConsoleMapDisplay();
        ConsoleMapDisplay observer2 = new ConsoleMapDisplay();
        ConsoleMapDisplay observer3 = new ConsoleMapDisplay();

        rectangularMap.subscribe(observer1);
        rectangularMap.subscribe(observer2);
        rectangularMap.subscribe(observer3);

        assertEquals(3, rectangularMap.getListeners().size());
    }

    @Test
    void placingAnimals() {
        RectangularMap rectangularMap = new RectangularMap(6, 6);

        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(4, 1));
        Animal animal3 = new Animal();

        try {
            rectangularMap.place(animal1);
            rectangularMap.place(animal2);
        } catch (IncorrectPositionException e) {
            fail(e.getMessage());
        }

        assertEquals(animal1, rectangularMap.objectAt(new Vector2d(2, 2)));
        assertEquals(animal2, rectangularMap.objectAt(new Vector2d(4, 1)));
        assertThrows(IncorrectPositionException.class, () -> rectangularMap.place(animal3));
    }

    @Test
    void testingPositions() {
        RectangularMap rectangularMap = new RectangularMap(7, 7);

        Vector2d position1 = new Vector2d(6, 6);
        Vector2d position2 = new Vector2d(1, 1);
        Vector2d position3 = new Vector2d(3, 4);
        Vector2d freePosition = new Vector2d(5, 2);

        Animal animal1 = new Animal(position1);
        Animal animal2 = new Animal(position2);
        Animal animal3 = new Animal(position3);

        try {
            rectangularMap.place(animal1);
            rectangularMap.place(animal2);
            rectangularMap.place(animal3);
        } catch (IncorrectPositionException e) {
            fail(e.getMessage());
        }

        assertTrue(rectangularMap.isOccupied(position1));
        assertEquals(animal1, rectangularMap.objectAt(position1));

        assertTrue(rectangularMap.isOccupied(position2));
        assertEquals(animal2, rectangularMap.objectAt(position2));

        assertTrue(rectangularMap.isOccupied(position3));
        assertEquals(animal3, rectangularMap.objectAt(position3));

        assertFalse(rectangularMap.isOccupied(freePosition));
    }

    @Test
    void positionAccessibility() {
        RectangularMap rectangularMap = new RectangularMap(4, 4);

        Vector2d occupied = new Vector2d(2, 2);
        Vector2d outside1 = new Vector2d(5, 2);
        Vector2d outside2 = new Vector2d(2, -1);
        Vector2d freeInside = new Vector2d(1, 3);

        try {
            rectangularMap.place(new Animal(occupied));
        } catch (IncorrectPositionException e) {
            fail(e.getMessage());
        }

        assertFalse(rectangularMap.canMoveTo(occupied));
        assertFalse(rectangularMap.canMoveTo(outside1));
        assertFalse(rectangularMap.canMoveTo(outside2));
        assertTrue(rectangularMap.canMoveTo(freeInside));
    }

    @Test
    void movesValidation() {
        RectangularMap rectangularMap = new RectangularMap(8, 8);

        Vector2d start1 = new Vector2d(3, 3);
        Vector2d start2 = new Vector2d(4, 4);
        Vector2d start3 = new Vector2d(6, 1);
        Vector2d start4 = new Vector2d(1, 1);

        Vector2d expected1 = new Vector2d(3, 4);
        Vector2d expected2 = new Vector2d(4, 3);
        Vector2d expected4 = new Vector2d(1, 2);

        Animal animal1 = new Animal(start1);
        Animal animal2 = new Animal(start2);
        Animal animal3 = new Animal(start3);
        Animal animal4 = new Animal(start4);

        try {
            rectangularMap.place(animal1);
            rectangularMap.place(animal2);
            rectangularMap.place(animal3);
            rectangularMap.place(animal4);
        } catch (IncorrectPositionException e) {
            fail(e.getMessage());
        }

        rectangularMap.move(animal1, MoveDirection.FORWARD);
        rectangularMap.move(animal2, MoveDirection.BACKWARD);
        rectangularMap.move(animal3, MoveDirection.LEFT);
        rectangularMap.move(animal4, MoveDirection.FORWARD);

        assertEquals(expected1, animal1.getPosition());
        assertEquals(expected2, animal2.getPosition());
        assertEquals(start3, animal3.getPosition());
        assertEquals(expected4, animal4.getPosition());
    }

    @Test
    void validNumberOfAnimals() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);

        try {
            rectangularMap.place(new Animal(new Vector2d(1, 1)));
            rectangularMap.place(new Animal(new Vector2d(2, 2)));
            rectangularMap.place(new Animal(new Vector2d(7, 3)));
            rectangularMap.place(new Animal(new Vector2d(3, 6)));
        } catch (IncorrectPositionException ignored) {}

        assertEquals(2, rectangularMap.getElements().size());
    }
}
