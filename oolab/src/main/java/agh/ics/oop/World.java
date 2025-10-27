package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import static agh.ics.oop.OptionsParser.parserChange;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartowal");

        run(parserChange(args));

        System.out.println("system zakonczyl dzialanie");
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
