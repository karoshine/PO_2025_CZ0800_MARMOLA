package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;


public class World {
    public static void run(String[] args) {
        List<MoveDirection> directions = OptionsParser.parserChange(args);
        ConsoleMapDisplay observer = new ConsoleMapDisplay();
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));

        RectangularMap map = new RectangularMap(5,5);
        map.subscribe(observer);
        Simulation simulation = new Simulation(positions, directions,map);
        simulation.run();

        GrassField grassMap = new GrassField(15);
        grassMap.subscribe(observer);
        Simulation grassSimulation = new Simulation(positions, directions,grassMap);
        grassSimulation.run();

    }
    public static void main(String[] args) {
        IO.println("System wystartowal");
        try {
            run(args);
        }
        catch (IllegalArgumentException e) {
            IO.println(e.getMessage());
        }
        IO.println("System zakonczyl dzialanie");
    }
}