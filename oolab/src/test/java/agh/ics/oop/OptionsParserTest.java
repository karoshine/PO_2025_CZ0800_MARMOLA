package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static agh.ics.oop.OptionsParser.parserChange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OptionsParserTest {

    @ParameterizedTest
    @CsvSource({
            "f, FORWARD",
            "forward, FORWARD",
            "b, BACKWARD",
            "backward, BACKWARD",
            "l, LEFT",
            "left, LEFT",
            "r, RIGHT",
            "right, RIGHT"
    })
    void parserChangeValidInputs(String input, MoveDirection expected) {
        List<MoveDirection> result = parserChange(new String[]{input});
        assertEquals(List.of(expected), result);
    }


    @Test
    void emptyArray() {
        String[] arr = {};
        List<MoveDirection> expected = List.of();
        assertEquals(expected, parserChange(arr));
    }

    @ParameterizedTest
    @CsvSource({
            "'r l f b', 'RIGHT LEFT FORWARD BACKWARD'",
            "'forward backward left right', 'FORWARD BACKWARD LEFT RIGHT'",
            "'f backward l right', 'FORWARD BACKWARD LEFT RIGHT'"
    })
    void parserChangeMultipleValidCommands(String input, String expected) {
        String[] inputArr = input.split(" ");

        List<MoveDirection> expectedList = Arrays.stream(expected.split(" "))
                .map(MoveDirection::valueOf)
                .toList();

        assertEquals(expectedList, parserChange(inputArr));
    }

    @ValueSource(strings = {
            "x",
            "try",
            "1r",
            "FORWARD",
            "Left"
    })
    void parserChangeInvalidInputThrowsException(String input) {
        String[] args = {input};
        assertThrows(IllegalArgumentException.class, () -> parserChange(args));
    }


    @Test
    void parserChangeMixedInputThrowsException() {
        String[] args = {"f", "l", "wrong_argument", "r"};
        assertThrows(IllegalArgumentException.class, () -> parserChange(args));
    }
}