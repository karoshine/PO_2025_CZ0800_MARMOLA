package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void canPlaceAnimalInsideMap() {
        // given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 2));

        // when
        boolean result = map.place(animal);

        // then
        assertTrue(result);
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertEquals(animal, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void cannotPlaceAnimalOutsideMap() {
        // given
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(-2, 15));

        // when
        boolean result = map.place(animal);

        // then
        assertFalse(result);
        assertNull(map.objectAt(new Vector2d(-2, 15)));
        assertFalse(map.isOccupied(new Vector2d(-2, 15)));
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
    void animalsCannotLeaveMapOrMoveIntoEachOther() {
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
    @Test
    void numberOfAnimalsInMap() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal fox = new Animal(new Vector2d(2, 3));
        Animal rabbit = new Animal(new Vector2d(2, 2));
        Animal eagle = new Animal(new Vector2d(5, 4));
        map.place(fox);
        map.place(rabbit);
        map.place(eagle);

        assertEquals(2, map.getElements().size());
    }
}
