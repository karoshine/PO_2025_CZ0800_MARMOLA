package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static agh.ics.oop.OptionsParser.parserChange;
import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void forwardValidation() {
        String[] arr = {"f"};
        MoveDirection[] dir = {MoveDirection.FORWARD};
        assertArrayEquals(dir, parserChange(arr));
    }

    @Test
    void backwardValidation() {
        String[] arr = {"b"};
        MoveDirection[] dir = {MoveDirection.BACKWARD};
        assertArrayEquals(dir, parserChange(arr));
    }

    @Test
    void leftValidation() {
        String[] arr = {"l"};
        MoveDirection[] dir = {MoveDirection.LEFT};
        assertArrayEquals(dir, parserChange(arr));
    }

    @Test
    void rightValidation() {
        String[] arr = {"r"};
        MoveDirection[] dir = {MoveDirection.RIGHT};
        assertArrayEquals(dir, parserChange(arr));
    }

    @Test
    void emptyArray() {
        String[] arr = {};
        MoveDirection[] dir = {};
        assertArrayEquals(dir, parserChange(arr));
    }

    void consolidatedParserChangeTests() {

        String[] invalidArr = {"c", "w", "dfvsb", "352345", ",,,"};
        MoveDirection[] expectedEmpty = {};
        assertArrayEquals(expectedEmpty, parserChange(invalidArr));

        String[] validArr = {"r", "l", "f", "b", "l", "b"};
        MoveDirection[] expectedValid = {
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD
        };
        assertArrayEquals(expectedValid, parserChange(validArr));

        String[] mixedArr1 = {"r", "rl", "fb", "b", "rlfb", "l"};
        MoveDirection[] expectedMixed1 = {
                MoveDirection.RIGHT,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT
        };
        assertArrayEquals(expectedMixed1, parserChange(mixedArr1));


        String[] mixedArr2 = {"r", "l", "1r", "0", "FORWARD", "b", "LEFT"};
        MoveDirection[] expectedMixed2 = {
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD
        };
        assertArrayEquals(expectedMixed2, parserChange(mixedArr2));


    }

}