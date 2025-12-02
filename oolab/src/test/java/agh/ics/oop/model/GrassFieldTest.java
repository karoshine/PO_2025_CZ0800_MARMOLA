package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void subscribingObservers() {
        GrassField grassField = new GrassField(5);
        ConsoleMapDisplay observer1 = new ConsoleMapDisplay();
        ConsoleMapDisplay observer2 = new ConsoleMapDisplay();
        grassField.subscribe(observer1);
        grassField.subscribe(observer2);
        assertEquals(2,grassField.getListeners().size());
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
        GrassField grassField = new GrassField(5);

        Vector2d position1 = new Vector2d(5,5);
        Vector2d position2 = new Vector2d(5,6);
        Vector2d position3 = new Vector2d(6,11);
        Vector2d position4 = new Vector2d(10,7);
        try {
            grassField.place(new Animal(position1));
            grassField.place(new Animal(position2));
            grassField.place(new Animal(position3));
            grassField.place(new Animal(position4));
        }
        catch (IncorrectPositionException e) {
            fail(e.getMessage());
        }
        assertEquals(9,grassField.getElements().size());
    }

    @Test
    void testingPositions() {
        GrassField grassField = new GrassField(5);
        Vector2d position1 = new Vector2d(5,5);
        Vector2d position2 = new Vector2d(0,0);
        Vector2d position3 = new Vector2d(3,2);
        Vector2d position4 = new Vector2d(2,2);
        Animal animal1 = new Animal(position1);
        Animal animal2 = new Animal(position2);
        Animal animal3 = new Animal(position3);
        try {
            grassField.place(animal1);
            grassField.place(animal2);
            grassField.place(animal3);
        }
        catch (IncorrectPositionException e) {
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
        Vector2d position1 = new Vector2d(5,4);
        Vector2d position2 = new Vector2d(5,6);
        Vector2d position3 = new Vector2d(6,2);
        Vector2d position4 = new Vector2d(3,2);
        Animal animal1 = new Animal(position1);

        try {
            grassField.place(animal1);
        }
        catch (IncorrectPositionException e) {
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
        Vector2d position1 = new Vector2d(4,5);
        Vector2d position2 = new Vector2d(5,6);
        Vector2d position3 = new Vector2d(6,2);
        Vector2d position4 = new Vector2d(3,2);
        Vector2d position5 = new Vector2d(2,10);
        Vector2d position6 = new Vector2d(4,7);
        Vector2d position7 = new Vector2d(4,6);
        Vector2d position8 = new Vector2d(5,5);
        Vector2d position9 = new Vector2d(2,2);
        Vector2d position10 = new Vector2d(2,11);
        Animal animal1 = new Animal(position1);
        Animal animal2 = new Animal(position2);
        Animal animal3 = new Animal(position3);
        Animal animal4 = new Animal(position4);
        Animal animal5 = new Animal(position5);
        Animal animal6 = new Animal(position6);
        try {
            grassField.place(animal1);
            grassField.place(animal2);
            grassField.place(animal3);
            grassField.place(animal4);
            grassField.place(animal5);
            grassField.place(animal6);
        }
        catch (IncorrectPositionException e) {
            fail(e.getMessage());
        }
        grassField.move(animal1,MoveDirection.FORWARD);
        grassField.move(animal2,MoveDirection.BACKWARD);
        grassField.move(animal3,MoveDirection.LEFT);
        grassField.move(animal4,MoveDirection.RIGHT);
        grassField.move(animal5,MoveDirection.FORWARD);
        grassField.move(animal4,MoveDirection.BACKWARD);

        assertEquals(position7, animal1.getPosition());
        assertEquals(position8, animal2.getPosition());
        assertEquals(position3, animal3.getPosition());
        assertEquals(position9, animal4.getPosition());
        assertEquals(position10, animal5.getPosition());
        assertEquals(position6, animal6.getPosition());
    }

}