package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {

    public static List<MoveDirection> parserChange(String[] args) {
        List<MoveDirection> potDirections = new ArrayList<>();

        for (String arg : args) {
            switch (arg) {
                case "l" -> potDirections.add(MoveDirection.LEFT);
                case "r" -> potDirections.add(MoveDirection.RIGHT);
                case "f" -> potDirections.add(MoveDirection.FORWARD);
                case "b" -> potDirections.add(MoveDirection.BACKWARD);
            }
        }

        return potDirections;
    }

    public static void main(String[] args) {
        parserChange(args);
    }
}
