package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;

import agh.ics.oop.model.MoveDirection;

import agh.ics.oop.model.Vector2d;

import java.util.List;

import static agh.ics.oop.OptionsParser.parserChange;

public class World {
    public static void main(String[] args) {

        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(0, 0));
        System.out.println(animal1.toString());
        System.out.println(animal2.toString());

        List<MoveDirection> directions = OptionsParser.parserChange(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

    }

    public static void run(MoveDirection[] args) { // no usages
        for (MoveDirection arg : args) {
            String res = switch (arg) {
                case FORWARD -> "Zwierzak idzie do przodu,";
                case BACKWARD -> "Zwierzak idzie w tyl,";
                case RIGHT -> "Zwierzak idzie w prawo,";
                case LEFT -> "Zwierzak idzie w lewo,";
            };

            System.out.println(res);
        }
        System.out.println("Stop");
    }
}
