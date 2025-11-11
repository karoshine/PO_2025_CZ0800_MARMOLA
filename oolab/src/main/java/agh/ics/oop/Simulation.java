package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private final WorldMap worldMap;

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> directions, WorldMap map) {
        this.directions = directions;
        this.worldMap = map;
        this.animals = new ArrayList<>();

        for (Vector2d pos : initialPositions) {
            Animal animal = new Animal(pos);
            if (this.worldMap.place (animal)) {
                this.animals.add(animal);
            }
        }

    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void run() {
        if (animals.isEmpty()) {
            System.out.println("Brak zwierząt do symulacji — kończę działanie.");
            return;
        }

        int animalIndex = 0;

        for (MoveDirection direction : directions) {
            Animal currentAnimal = animals.get(animalIndex);
            worldMap.move(currentAnimal, direction);

            System.out.println(
                    "Animal" + animalIndex + ": " + currentAnimal
            );
            System.out.println(worldMap);

            animalIndex = (animalIndex + 1) % animals.size();
        }
    }
}
