package agh.ics.oop;
import agh.ics.oop.OptionsParser;
import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartowal");

        run(OptionsParser.parserChange(args));

        System.out.println("system zakonczyl dzialanie");
    }

    public static void run(MoveDirection[] args) {
        System.out.println("Start");
        for (MoveDirection arg : args) {
            switch (arg) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
                case RIGHT -> System.out.println("Zwierzak skreca w prawo");
                case LEFT -> System.out.println("Zwierzak skreca w lewo");
            }
        }
        System.out.println("Stop");
    }
}
