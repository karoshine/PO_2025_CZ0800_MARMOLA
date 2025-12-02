package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void subscribingObservers() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        ConsoleMapDisplay observer1 = new ConsoleMapDisplay();
        ConsoleMapDisplay observer2 = new ConsoleMapDisplay();
        rectangularMap.subscribe(observer1);
        rectangularMap.subscribe(observer2);
        assertEquals(2,rectangularMap.getListeners().size());
    }

    @Test
    void placingAnimals() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(1,4));
        Animal animal3 = new Animal();
        try {
            rectangularMap.place(animal1);
            rectangularMap.place(animal2);
            rectangularMap.place(animal3);
        }
        catch (IncorrectPositionException e) {
            IO.println(e.getMessage());
        }

        assertEquals(animal1, rectangularMap.objectAt(new Vector2d(2,2)));
        assertEquals(animal2, rectangularMap.objectAt(new Vector2d(1,4)));
        assertThrows(IncorrectPositionException.class, () -> rectangularMap.place(animal3));
    }

    @Test
    void testingPositions() {
        RectangularMap rectangularMap = new RectangularMap(6,6);
        Vector2d position1 = new Vector2d(5,5);
        Vector2d position2 = new Vector2d(0,0);
        Vector2d position3 = new Vector2d(3,2);
        Vector2d position4 = new Vector2d(2,2);
        Animal animal1 = new Animal(position1);
        Animal animal2 = new Animal(position2);
        Animal animal3 = new Animal(position3);
        try {
            rectangularMap.place(animal1);
            rectangularMap.place(animal2);
            rectangularMap.place(animal3);
        }
        catch (IncorrectPositionException e) {
            fail(e.getMessage());
        }
        assertTrue(rectangularMap.isOccupied(position1));
        assertEquals(animal1, rectangularMap.objectAt(position1));
        assertTrue(rectangularMap.isOccupied(position2));
        assertEquals(animal2, rectangularMap.objectAt(position2));
        assertTrue(rectangularMap.isOccupied(position3));
        assertEquals(animal3, rectangularMap.objectAt(position3));
        assertFalse(rectangularMap.isOccupied(position4));
    }

    @Test
    void positionAccessibility() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Vector2d position1 = new Vector2d(5,4);
        Vector2d position2 = new Vector2d(5,6);
        Vector2d position3 = new Vector2d(6,2);
        Vector2d position4 = new Vector2d(3,2);
        Animal animal1 = new Animal(position1);
        try {
            rectangularMap.place(animal1);
        }
        catch (IncorrectPositionException e) {
            IO.println(e.getMessage());
        }

        assertFalse(rectangularMap.canMoveTo(position1));
        assertFalse(rectangularMap.canMoveTo(position2));
        assertFalse(rectangularMap.canMoveTo(position3));
        assertTrue(rectangularMap.canMoveTo(position4));
    }

    @Test
    void movesValidation() {
        RectangularMap rectangularMap = new RectangularMap(10,10);
        Vector2d position1 = new Vector2d(4,5);
        Vector2d position2 = new Vector2d(5,6);
        Vector2d position3 = new Vector2d(6,2);
        Vector2d position4 = new Vector2d(3,2);
        Vector2d position5 = new Vector2d(2,10);
        Vector2d position6 = new Vector2d(4,7);
        Vector2d position7 = new Vector2d(4,6);
        Vector2d position8 = new Vector2d(5,5);
        Vector2d position9 = new Vector2d(2,2);
        Animal animal1 = new Animal(position1);
        Animal animal2 = new Animal(position2);
        Animal animal3 = new Animal(position3);
        Animal animal4 = new Animal(position4);
        Animal animal5 = new Animal(position5);
        Animal animal6 = new Animal(position6);
        try {
            rectangularMap.place(animal1);
            rectangularMap.place(animal2);
            rectangularMap.place(animal3);
            rectangularMap.place(animal4);
            rectangularMap.place(animal5);
            rectangularMap.place(animal6);
        }
        catch (IncorrectPositionException e) {
            IO.println(e.getMessage());
        }
        rectangularMap.move(animal1,MoveDirection.FORWARD);
        rectangularMap.move(animal2,MoveDirection.BACKWARD);
        rectangularMap.move(animal3,MoveDirection.LEFT);
        rectangularMap.move(animal4,MoveDirection.RIGHT);
        rectangularMap.move(animal5,MoveDirection.FORWARD);
        rectangularMap.move(animal4,MoveDirection.BACKWARD);

        assertEquals(position7, animal1.getPosition());
        assertEquals(position8, animal2.getPosition());
        assertEquals(position3, animal3.getPosition());
        assertEquals(position9, animal4.getPosition());
        assertEquals(position5, animal5.getPosition());
        assertEquals(position6, animal6.getPosition());
    }

    @Test
    void validNumberOfAnimals() {
        RectangularMap rectangularMap = new RectangularMap(10,10);
        Vector2d position1 = new Vector2d(5,5);
        Vector2d position2 = new Vector2d(5,6);
        Vector2d position3 = new Vector2d(6,11);
        Vector2d position4 = new Vector2d(10,7);
        try {
            rectangularMap.place(new Animal(position1));
            rectangularMap.place(new Animal(position2));
            rectangularMap.place(new Animal(position3));
            rectangularMap.place(new Animal(position4));
        }
        catch(IncorrectPositionException e) {
            IO.println(e.getMessage());
        }
        assertEquals(2,rectangularMap.getElements().size());
    }


}