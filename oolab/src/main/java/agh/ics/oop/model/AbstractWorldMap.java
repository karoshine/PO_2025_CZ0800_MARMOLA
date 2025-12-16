package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<Vector2d, Animal>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected List<MapChangeListener> listeners = new ArrayList<>();
    protected final UUID id;

    public AbstractWorldMap() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public void place(Animal animal) throws IncorrectPositionException{
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            mapChanged("Animal has been placed at %s".formatted(animal.getPosition()));
        } else {
            throw new IncorrectPositionException(animal.getPosition());
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction){
        if (objectAt(animal.getPosition()) == animal){
            Vector2d previousPosition = animal.getPosition();
            animals.remove(animal.getPosition(), animal);
            animal.move(direction,this);
            animals.put(animal.getPosition(), animal);
            Vector2d currentPosition = animal.getPosition();
            if (!(previousPosition.equals(currentPosition))) {
                mapChanged("Animal moved on %s".formatted(animal.getPosition()));
            }
        }
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(List.copyOf(animals.values()));
    }


    @Override
    public String toString() {
        Boundary boundary = getCurrentBounds();
        return mapVisualizer.draw(boundary.start(),  boundary.end());
    }

    public void subscribe(MapChangeListener mapChangeListener) {
        listeners.add(mapChangeListener);
    }

    public void unsubscribe(MapChangeListener mapChangeListener) {
        listeners.remove(mapChangeListener);
    }

    public List<MapChangeListener> getListeners() {
        return listeners;
    }

    public void mapChanged(String message) {
        for (MapChangeListener listener : listeners) {
            listener.mapChanged(this, message);
        }
    }

}

