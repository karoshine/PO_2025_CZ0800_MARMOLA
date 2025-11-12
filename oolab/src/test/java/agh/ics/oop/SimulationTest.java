package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    void twoAnimalsMoveCorrectlyWithValidCommands() {

        String[] input = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f"};
        List<MoveDirection> moves = OptionsParser.parserChange(input);
        List<Animal> animals = List.of(
                new Animal(new Vector2d(2, 2)),
                new Animal(new Vector2d(3, 4))
        );
        RectangularMap map = new RectangularMap(5, 5);

        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, moves, map);

        simulation.run();

        List<Animal> animalsOnMap = simulation.getObjectsOnMap();

        assertEquals(2, animalsOnMap.size());

        assertTrue(animalsOnMap.get(0).getPosition().follows(new Vector2d(0, 0)));
        assertTrue(animalsOnMap.get(0).getPosition().precedes(map.getUpperRight()));
        assertTrue(animalsOnMap.get(1).getPosition().follows(new Vector2d(0, 0)));
        assertTrue(animalsOnMap.get(1).getPosition().precedes(map.getUpperRight()));
    }

    @Test
    void threeAnimalsStayInPlaceWithInvalidCommands() {
        String[] input = {"x", "y", "z"};
        List<MoveDirection> moves = OptionsParser.parserChange(input);
        List<Animal> animals = List.of(
                new Animal(new Vector2d(2, 2)),
                new Animal(new Vector2d(3, 4)),
                new Animal(new Vector2d(1, 1))
        );
        RectangularMap map = new RectangularMap(5, 5);

        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, moves, map);
        simulation.run();

        List<Animal> animalsOnMap = simulation.getObjectsOnMap();

        assertEquals(3, animalsOnMap.size());
        for (int i = 0; i < animalsOnMap.size(); i++) {
            assertEquals(animals.get(i).getPosition(), animalsOnMap.get(i).getPosition());
            assertEquals(MapDirection.NORTH, animalsOnMap.get(i).getOrientation());
        }
    }

    @Test
    void fourAnimalsHandleMixedCommandsCorrectly() {
        String[] input = {"f", "z", "r", "l", "b", "f"};
        List<MoveDirection> moves = OptionsParser.parserChange(input);
        List<Animal> animals = List.of(
                new Animal(new Vector2d(0, 0)),
                new Animal(new Vector2d(4, 4)),
                new Animal(new Vector2d(3, 2)),
                new Animal(new Vector2d(2, 1))
        );
        RectangularMap map = new RectangularMap(5, 5);

        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, moves, map);
        simulation.run();

        List<Animal> animalsOnMap = simulation.getObjectsOnMap();

        assertEquals(4, animalsOnMap.size());
        for (Animal a : animalsOnMap) {
            assertTrue(a.getPosition().follows(new Vector2d(0, 0)));
            assertTrue(a.getPosition().precedes(map.getUpperRight()));
        }
    }

    @Test
    void textBlocksMoveCorrectlyOnTextMap() {
        List<String> texts = List.of("Ala", "ma", "kota");
        String[] input = {"r", "r", "l", "f"};
        List<MoveDirection> moves = OptionsParser.parserChange(input);
        TextMap map = new TextMap();

        Simulation<String, Integer> simulation = new Simulation<>(texts, moves, map);
        simulation.run();

        List<String> objectsOnMap = simulation.getObjectsOnMap();
        assertEquals(3, objectsOnMap.size());
        assertTrue(objectsOnMap.containsAll(texts));
    }
}
