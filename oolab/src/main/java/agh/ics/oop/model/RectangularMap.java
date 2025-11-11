package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    public static final Vector2d Zero = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final MapVisualizer visualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height){
        upperRight = new Vector2d(width -1, height -1);
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(Zero) && position.precedes(upperRight) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animals.get(animal.getPosition()) == animal){
            animals.remove(animal.getPosition(), animal);
            animal.move(direction, this);
            animals.put(animal.getPosition(), animal);
        }
    }

    @Override
    public String toString() {
        return visualizer.draw(Zero, upperRight);
    }
}