package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;


public class World {
    public static final Vector2d MIN_MAP = new Vector2d(0, 0);
    public static final Vector2d MAX_MAP = new Vector2d(4, 4);

    public static void simulateRectangularMap(List<MoveDirection> directions) {
        List<Animal> animals = List.of(
                new Animal(new Vector2d(2, 2)),
                new Animal(new Vector2d(3, 4))
        );
        RectangularMap map = new RectangularMap(5, 5);

        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, directions, map);
        simulation.run();
    }

    public static void simulateTextMap(List<MoveDirection> directions) {
        List<String> textBlocks = List.of("Ala ma sowoniedzwiedzia".split(" "));
        TextMap map = new TextMap();

        Simulation<String, Integer> simulation = new Simulation<>(textBlocks, directions, map);
        simulation.run();
    }

    public static void main(String[] args) {

        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(0, 0));
        System.out.println(animal1.toString());
        System.out.println(animal2.toString());

        List<MoveDirection> directions = OptionsParser.parserChange(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        RectangularMap map = new RectangularMap(5,5);
        simulateRectangularMap(directions);
        simulateTextMap(directions);

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
