package agh.ics.oop;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.IncorrectPositionException;

import java.util.ArrayList;
import java.util.List;




public class World {
    public static void run(String[] args) {
        List<MoveDirection> directions = OptionsParser.parserChange(args);
        ConsoleMapDisplay observer = new ConsoleMapDisplay();
        List<Vector2d> positions = List.of(new Vector2d(0,0), new Vector2d(3,3));

        List<Simulation> simulations = new ArrayList<>();
        for(int i = 0; i < 1000; i++){
            RectangularMap map = new RectangularMap(5,5);
            map.subscribe(observer);
            Simulation simulation = new Simulation(positions, directions,map);
            GrassField grassMap = new GrassField(10);
            grassMap.subscribe(observer);
            Simulation grassSimulation = new Simulation(positions, directions,grassMap);
            simulations.add(simulation);
            simulations.add(grassSimulation);
        }

        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        simulationEngine.runAsyncInThreadPool();
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
        WorldGUI.main(args);
    }

}