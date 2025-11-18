package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
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

        grassField.place(new Animal(position1));
        grassField.place(new Animal(position2));
        grassField.place(new Animal(position3));
        grassField.place(new Animal(position4));

        assertEquals(9,grassField.getElements().size());
    }

    @Test
    void animalsCanMoveIndependently() {
        // given
        RectangularMap map = new RectangularMap(5, 5);
        Animal fox = new Animal(new Vector2d(2, 3));
        Animal rabbit = new Animal(new Vector2d(2, 2));

        // when
        map.place(fox);
        map.move(fox, MoveDirection.FORWARD);

        map.place(rabbit);
        map.move(rabbit, MoveDirection.RIGHT);

        // then
        assertEquals(fox, map.objectAt(new Vector2d(2, 4)));
        assertNotEquals(fox, map.objectAt(new Vector2d(2, 2)));
        assertEquals(rabbit, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void animalsMoveIntoEachOther() {
        // given
        RectangularMap map = new RectangularMap(5, 5);
        Animal fox = new Animal(new Vector2d(2, 3));
        Animal rabbit = new Animal(new Vector2d(2, 2));
        Animal eagle = new Animal(new Vector2d(4, 4));

        // when
        map.place(fox);
        map.move(fox, MoveDirection.RIGHT);

        map.place(rabbit);
        map.move(rabbit, MoveDirection.FORWARD);

        map.place(eagle);
        map.move(eagle, MoveDirection.FORWARD);

        // then
        assertEquals(fox, map.objectAt(new Vector2d(2, 3)));
        assertEquals(rabbit, map.objectAt(new Vector2d(2, 2)));
        assertEquals(eagle, map.objectAt(new Vector2d(4, 4)));
    }

}