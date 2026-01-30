package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {


    public static List<MoveDirection> parserChange(String[] args){
        List<MoveDirection> directions = new ArrayList<>();
        for(String arg : args){
            switch (arg) {
                case "l","left" -> directions.add(MoveDirection.LEFT);
                case "r","right" -> directions.add(MoveDirection.RIGHT);
                case "f","forward" -> directions.add(MoveDirection.FORWARD);
                case "b","backward" -> directions.add(MoveDirection.BACKWARD);
                default -> throw new IllegalArgumentException("%s is not legal move".formatted(arg));
            }

        }
        return directions;
    }
    public void main(String[] args) {
        parserChange(args);
    }
}