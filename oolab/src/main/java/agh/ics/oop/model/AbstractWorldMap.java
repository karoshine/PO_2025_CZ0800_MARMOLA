package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<Vector2d, Animal>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public boolean place(Animal animal){
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction){
        if (objectAt(animal.getPosition()) == animal){
            animals.remove(animal.getPosition(), animal);
            animal.move(direction,this);
            animals.put(animal.getPosition(), animal);
        }
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(List.copyOf(animals.values()));
    }

}
