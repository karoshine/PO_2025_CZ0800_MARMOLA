package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    @Test
    void nextValidation() {

        MapDirection n = MapDirection.NORTH;
        MapDirection e = MapDirection.EAST;
        MapDirection s = MapDirection.SOUTH;
        MapDirection w = MapDirection.WEST;


        assertEquals(MapDirection.EAST, n.next());
        assertEquals(MapDirection.SOUTH, e.next());
        assertEquals(MapDirection.WEST, s.next());
        assertEquals(MapDirection.NORTH, w.next());
    }

    @Test
    void previousValidation() {

        MapDirection n = MapDirection.NORTH;
        MapDirection e = MapDirection.EAST;
        MapDirection s = MapDirection.SOUTH;
        MapDirection w = MapDirection.WEST;


        assertEquals(MapDirection.WEST, n.previous());
        assertEquals(MapDirection.NORTH, e.previous());
        assertEquals(MapDirection.EAST, s.previous());
        assertEquals(MapDirection.SOUTH, w.previous());
    }
}
