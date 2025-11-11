package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;
import java.util.List;

import static agh.ics.oop.OptionsParser.parserChange;
import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @ParameterizedTest
    @CsvSource({
            "'f,r,f', 2,2, 3,3, EAST",
            "'f,f,l,f', 2,2, 1,4, WEST",
            "'r,f,f', 2,2, 4,2, EAST"
    })
    void animalMovesAndTurnsCorrectly(String commands, int startX, int startY,
                                      int expectedX, int expectedY, MapDirection expectedDir) {
        List<Vector2d> positions = List.of(new Vector2d(startX, startY));
        String[] input = commands.split(",");
        List<MoveDirection> moves = parserChange(input);
        RectangularMap map = new RectangularMap(5,5);

        Simulation simulation = new Simulation(positions, moves, map);
        simulation.run();

        Animal animal = simulation.getAnimals().get(0);

        assertEquals(new Vector2d(expectedX, expectedY), animal.getPosition());
        assertEquals(expectedDir, animal.getOrientation());
    }

    @Test
    void animalDoesNotLeaveMap() {
        List<Vector2d> positions = List.of(new Vector2d(0, 0));
        List<MoveDirection> moves = parserChange(new String[]{"b", "l", "f"});
        RectangularMap map = new RectangularMap(5,5);

        Simulation simulation = new Simulation(positions, moves, map);
        simulation.run();

        Animal animal = simulation.getAnimals().get(0);

        assertEquals(new Vector2d(0, 0), animal.getPosition());
        assertEquals(MapDirection.WEST, animal.getOrientation());
    }

    @Test
    void multipleAnimalsAlternateMoves() {
        List<Vector2d> positions = List.of(
                new Vector2d(2, 2),
                new Vector2d(4, 4)
        );

        List<MoveDirection> moves = parserChange(new String[]{"f", "b", "l", "r"});
        RectangularMap map = new RectangularMap(5,5);
        Simulation simulation = new Simulation(positions, moves, map);
        simulation.run();

        Animal a1 = simulation.getAnimals().get(0);
        Animal a2 = simulation.getAnimals().get(1);

        assertEquals(new Vector2d(2, 3), a1.getPosition());
        assertEquals(MapDirection.WEST, a1.getOrientation());

        assertEquals(new Vector2d(4, 3), a2.getPosition());
        assertEquals(MapDirection.EAST, a2.getOrientation());
    }

    @Test
    void parserCorrectlyParsesInput() {
        String[] input = {"f", "x", "r", "b", "hello", "l"};
        List<MoveDirection> expected = List.of(
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT
        );

        List<MoveDirection> parsed = parserChange(input);
        assertEquals(expected, parsed);
    }

    @Test
    void ZeroAnimalsVerify() {
        //given
        String[] args = {"f", "f", "r"};
        List<MoveDirection> moves = OptionsParser.parserChange(args);
        List<Vector2d> startPositions = Collections.emptyList();
        RectangularMap map = new RectangularMap(5,5);
        Simulation simulation = new Simulation(startPositions, moves, map);

        //when
        simulation.run();
        //then
        assertEquals(0, simulation.getAnimals().size());
    }
}
