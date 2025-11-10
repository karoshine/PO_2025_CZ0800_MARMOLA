package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> directions;

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> directions) {
        this.directions = directions;

        for (Vector2d pos : initialPositions) {
            Animal animal = new Animal(pos);
            if (animal.isWithinBounds(pos)) {
                animals.add(new Animal(pos));
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
            currentAnimal.move(direction);

            System.out.println(
                    "Animal" + animalIndex + ": " + currentAnimal
            );

            animalIndex = (animalIndex + 1) % animals.size();
        }
    }
}
