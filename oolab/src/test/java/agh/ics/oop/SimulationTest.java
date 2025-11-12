package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static agh.ics.oop.model.RectangularMap.Zero;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimulationTest {

    @Test
    void twoAnimalsMoveCorrectlyWithValidDirections() {
        // given
        String[] commands = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> expectedMoves = Arrays.asList(
                MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD
        );

        List<MoveDirection> moves = OptionsParser.parserChange(commands);
        List<Animal> animals = List.of(new Animal(new Vector2d(2, 2)), new Animal(new Vector2d(3, 4)));

        MapDirection[] expectedDirections = {MapDirection.SOUTH, MapDirection.NORTH};
        Vector2d[] expectedPositions = {new Vector2d(2, 0), new Vector2d(3, 4)};

        RectangularMap map = new RectangularMap(5, 5);
        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, moves, map);

        // when
        simulation.run();

        // then
        List<Animal> animalsOnMap = simulation.getObjectsOnMap();

        assertEquals(expectedMoves, moves);
        assertEquals(animalsOnMap.size(), 2);

        for (int i = 0; i < animalsOnMap.size(); i++) {
            assertTrue(animalsOnMap.get(i).getPosition().follows(Zero)
                    && animalsOnMap.get(i).getPosition().precedes(map.getUpperRight()));
            assertTrue(animalsOnMap.get(i).isAt(expectedPositions[i]));
            assertEquals(animalsOnMap.get(i), map.objectAt(expectedPositions[i]));
            assertEquals(animalsOnMap.get(i).getOrientation(), expectedDirections[i]);
        }
    }

    @Test
    void threeAnimalsStayInPlaceWithInvalidDirections() {
        // given
        String[] commands = {"fb", "bgh", "rr", "u", "a", "mm"};
        List<MoveDirection> expectedMoves = new ArrayList<>();

        List<MoveDirection> moves = OptionsParser.parserChange(commands);
        List<Vector2d> startPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(1, 1));
        List<Animal> animals = List.of(
                new Animal(startPositions.get(0)),
                new Animal(startPositions.get(1)),
                new Animal(startPositions.get(2))
        );

        RectangularMap map = new RectangularMap(5, 5);
        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, moves, map);

        // when
        simulation.run();

        // then
        List<Animal> animalsOnMap = simulation.getObjectsOnMap();
        assertEquals(expectedMoves, moves);
        assertEquals(3, animalsOnMap.size());

        for (int i = 0; i < animalsOnMap.size(); i++) {
            assertTrue(animalsOnMap.get(i).getPosition().follows(Zero)
                    && animalsOnMap.get(i).getPosition().precedes(map.getUpperRight()));
            assertTrue(animalsOnMap.get(i).isAt(startPositions.get(i)));
            assertEquals(animalsOnMap.get(i), map.objectAt(startPositions.get(i)));
            assertEquals(animalsOnMap.get(i).getOrientation(), MapDirection.NORTH);
        }
    }

    @Test
    void fourAnimalsHandleMixedDirectionsCorrectly() {
        // given
        String[] commands = {"mn", "bb", "f", "m", "r", "l", "b", "f", "u", "f", "f", "b"};
        List<MoveDirection> expectedMoves = Arrays.asList(
                MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.LEFT, MoveDirection.BACKWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.BACKWARD
        );

        List<MoveDirection> moves = OptionsParser.parserChange(commands);
        List<Animal> animals = List.of(
                new Animal(new Vector2d(0, 0)),
                new Animal(new Vector2d(4, 4)),
                new Animal(new Vector2d(3, 2)),
                new Animal(new Vector2d(2, 1))
        );

        MapDirection[] expectedDirections = {MapDirection.NORTH, MapDirection.EAST, MapDirection.WEST, MapDirection.NORTH};
        Vector2d[] expectedPositions = {new Vector2d(0, 2), new Vector2d(4, 4), new Vector2d(2, 2), new Vector2d(2, 0)};

        RectangularMap map = new RectangularMap(5, 5);
        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, moves, map);

        // when
        simulation.run();

        // then
        List<Animal> animalsOnMap = simulation.getObjectsOnMap();
        assertEquals(expectedMoves, moves);
        assertEquals(4, animalsOnMap.size());

        for (int i = 0; i < animalsOnMap.size(); i++) {
            assertTrue(animalsOnMap.get(i).getPosition().follows(Zero)
                    && animalsOnMap.get(i).getPosition().precedes(map.getUpperRight()));
            assertTrue(animalsOnMap.get(i).isAt(expectedPositions[i]));
            assertEquals(animalsOnMap.get(i), map.objectAt(expectedPositions[i]));
            assertEquals(animalsOnMap.get(i).getOrientation(), expectedDirections[i]);
        }
    }

    @Test
    void textBlocksMoveCorrectlyOnTextMap() {
        // given
        String[] commands = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> expectedMoves = Arrays.asList(
                MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD
        );

        List<MoveDirection> moves = OptionsParser.parserChange(commands);
        List<String> textBlocks = List.of("Ala ma białego kota".split(" "));

        int[] expectedPositions = {2, 0, 3, 1};

        TextMap map = new TextMap();
        Simulation<String, Integer> simulation = new Simulation<>(textBlocks, moves, map);

        // when
        simulation.run();

        // then
        List<String> textsOnMap = simulation.getObjectsOnMap();
        assertEquals(expectedMoves, moves);
        assertEquals(4, textsOnMap.size());

        for (int i = 0; i < textsOnMap.size(); i++) {
            assertEquals(textsOnMap.get(i), map.objectAt(expectedPositions[i]));
        }
    }
}
