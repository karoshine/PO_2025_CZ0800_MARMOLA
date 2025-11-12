package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextMapTest {

    @Test
    void shouldAddObjectToMap() {
        // given
        TextMap map = new TextMap();
        String word = "Kot";

        // when
        boolean placed = map.place(word);

        // then
        assertTrue(placed);
        assertEquals(word, map.objectAt(0));
        assertTrue(map.isOccupied(0));
    }

    @Test
    void shouldShiftObjectOnMoveRight() {
        // given
        TextMap map = new TextMap();
        String[] words = "Pies Zając Królik".split(" ");
        for (String w : words) map.place(w);

        // when
        map.move("Pies", MoveDirection.RIGHT);

        // then
        assertEquals("Pies", map.objectAt(1));
    }

    @Test
    void shouldNotMoveObjectOutOfBounds() {
        // given
        TextMap map = new TextMap();
        String[] words = "Słoń Lew Tygrys".split(" ");
        for (String w : words) map.place(w);

        // when
        map.move("Tygrys", MoveDirection.FORWARD);

        // then
        assertEquals("Tygrys", map.objectAt(2));
    }
}
