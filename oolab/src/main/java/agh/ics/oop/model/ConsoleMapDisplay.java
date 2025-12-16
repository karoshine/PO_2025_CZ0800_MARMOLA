package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updatesCounter = 0;
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        IO.println(message);
        IO.println("Map ID: %s".formatted(worldMap.getID()));
        IO.println(worldMap.toString());
        IO.println("Number of updates: %d".formatted(++updatesCounter));
    }
}
