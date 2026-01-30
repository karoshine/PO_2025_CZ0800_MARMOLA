package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void subscribingObservers() {
        GrassField grassField = new GrassField(8);
        ConsoleMapDisplay observer1 = new ConsoleMapDisplay();
        ConsoleMapDisplay observer2 = new ConsoleMapDisplay();
        ConsoleMapDisplay observer3 = new ConsoleMapDisplay();

        grassField.subscribe(observer1);
        grassField.subscribe(observer2);
        grassField.subscribe(observer3);

        assertEquals(3, grassField.getListeners().size());
    }

    @Test
    void placingGrass() {
        GrassField grassField = new GrassField(5);

        List<WorldElement> grass = grassField.getElements();
        Vector2d lowerLeft = new Vector2d(0,0);
        Vector2d upperRight = new Vector2d((int) sqrt(5*10), (int) sqrt(5*10));

        assertEquals(5,grass.size());
        for(WorldElement object : grass){
            assertTrue(object.getPosition().follows(lowerLeft));
            assertTrue(object.getPosition().precedes(upperRight));
        }
    }

    @Test
    void placingGrassAndAnimals() {
        GrassField grassField = new GrassField(6);

        Vector2d position1 = new Vector2d(2, 3);
        Vector2d position2 = new Vector2d(4, 5);
        Vector2d position3 = new Vector2d(7, 1);

        try {
            grassField.place(new Animal(position1));
            grassField.place(new Animal(position2));
            grassField.place(new Animal(position3));
        } catch (IncorrectPositionException e) {
            fail(e.getMessage());
        }

        assertEquals(9, grassField.getElements().size());
    }

    @Test
    void testingPositions() {
        GrassField grassField = new GrassField(4);

        Vector2d position1 = new Vector2d(1, 1);
        Vector2d position2 = new Vector2d(2, 2);
        Vector2d position3 = new Vector2d(3, 3);
        Vector2d position4 = new Vector2d(5, 5);

        Animal animal1 = new Animal(position1);
        Animal animal2 = new Animal(position2);
        Animal animal3 = new Animal(position3);

        try {
            grassField.place(animal1);
            grassField.place(animal2);
            grassField.place(animal3);
        } catch (IncorrectPositionException e) {
            fail(e.getMessage());
        }

        assertTrue(grassField.isOccupied(position1));
        assertEquals(animal1, grassField.objectAt(position1));
        assertTrue(grassField.isOccupied(position2));
        assertEquals(animal2, grassField.objectAt(position2));
        assertTrue(grassField.isOccupied(position3));
        assertEquals(animal3, grassField.objectAt(position3));
        assertFalse(grassField.isOccupied(position4));
    }

    @Test
    void positionAccessibility() {
        GrassField grassField = new GrassField(5);

        Vector2d position1 = new Vector2d(3, 3);
        Vector2d position2 = new Vector2d(3, 4);
        Vector2d position3 = new Vector2d(1, 2);
        Vector2d position4 = new Vector2d(6, 6);

        Animal animal1 = new Animal(position1);

        try {
            grassField.place(animal1);
        } catch (IncorrectPositionException e) {
            fail(e.getMessage());
        }

        assertFalse(grassField.canMoveTo(position1));
        assertTrue(grassField.canMoveTo(position2));
        assertTrue(grassField.canMoveTo(position3));
        assertTrue(grassField.canMoveTo(position4));
    }

    @Test
    void movesValidation() {
        GrassField grassField = new GrassField(5);

        Vector2d position1 = new Vector2d(2, 2);
        Vector2d position2 = new Vector2d(3, 3);
        Vector2d position3 = new Vector2d(4, 1);
        Vector2d position4 = new Vector2d(1, 1);

        Vector2d expected1 = new Vector2d(2, 3);
        Vector2d expected2 = new Vector2d(3, 2);
        Vector2d expected3 = position3;
        Vector2d expected4 = new Vector2d(1, 2); // poprawione z (0,1)

        Animal animal1 = new Animal(position1);
        Animal animal2 = new Animal(position2);
        Animal animal3 = new Animal(position3);
        Animal animal4 = new Animal(position4);

        try {
            grassField.place(animal1);
            grassField.place(animal2);
            grassField.place(animal3);
            grassField.place(animal4);
        } catch (IncorrectPositionException e) {
            fail(e.getMessage());
        }

        grassField.move(animal1, MoveDirection.FORWARD);
        grassField.move(animal2, MoveDirection.BACKWARD);
        grassField.move(animal3, MoveDirection.LEFT);
        grassField.move(animal4, MoveDirection.FORWARD);

        assertEquals(expected1, animal1.getPosition());
        assertEquals(expected2, animal2.getPosition());
        assertEquals(expected3, animal3.getPosition());
        assertEquals(expected4, animal4.getPosition());
    }
}