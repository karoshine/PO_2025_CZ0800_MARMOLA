package agh.ics.oop;

import agh.ics.oop.model.MapDirection;

import agh.ics.oop.model.MoveDirection;

import agh.ics.oop.model.Vector2d;

import static agh.ics.oop.OptionsParser.parserChange;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartowal");

        run(parserChange(args));

        System.out.println("system zakonczyl dzialanie");
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        System.out.println(MapDirection.EAST.toString());
        System.out.println(MapDirection.EAST.next());
        System.out.println(MapDirection.EAST.previous());
        System.out.println(MapDirection.NORTH.toUnitVector());
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
