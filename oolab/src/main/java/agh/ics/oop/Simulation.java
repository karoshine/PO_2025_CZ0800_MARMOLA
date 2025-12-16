package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.IncorrectPositionException;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable  {

    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private final WorldMap worldMap;

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> directions, WorldMap map) {
        this.directions = directions;
        this.worldMap = map;
        this.animals = new ArrayList<>();

        for (Vector2d pos : initialPositions) {
            Animal animal = new Animal(pos);
            try {
                this.worldMap.place(animal);
                animals.add(animal);
            } catch (IncorrectPositionException e) {
                IO.println(e.getMessage());
            }
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }
    @Override
    public void run() {
        if (animals.isEmpty()) {
            System.out.println("Brak zwierząt do symulacji — kończę działanie.");
            return;
        }

        int animalIndex = 0;

        for (MoveDirection direction : directions) {
            Animal currentAnimal = animals.get(animalIndex);
            worldMap.move(currentAnimal, direction);

            try{
                Thread.sleep(500);
            }catch (InterruptedException e) {
                System.out.println(e.getMessage());
                break;
            }

            animalIndex = (animalIndex + 1) % animals.size();
        }
    }
}
