package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionsParser {

    public static MoveDirection[] parserChange(String[] args) {
        MoveDirection[] pot_directions = new MoveDirection[args.length];
        int cnt = 0;
        for (String arg : args) {
            switch (arg) {
                case "l" -> pot_directions[cnt++] = MoveDirection.LEFT;
                case "r" -> pot_directions[cnt++] = MoveDirection.RIGHT;
                case "f" -> pot_directions[cnt++] = MoveDirection.FORWARD;
                case "b" -> pot_directions[cnt++] = MoveDirection.BACKWARD;
            }
        }

        return Arrays.copyOfRange(pot_directions, 0, cnt);
    }

    public void main(String[] args) {
        parserChange(args);
    }
}