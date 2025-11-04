package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static agh.ics.oop.OptionsParser.parserChange;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsParserTest {

    @ParameterizedTest
    @CsvSource({
            "f, FORWARD",
            "b, BACKWARD",
            "l, LEFT",
            "r, RIGHT"
    })
    void parserChangeValidation(String input, MoveDirection expected) {
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
            "'f', FORWARD",
            "'b', BACKWARD",
            "'l', LEFT",
            "'r', RIGHT"
    })
    void parserChangeSingleCommand(String input, MoveDirection expected) {
        assertEquals(List.of(expected), parserChange(new String[]{input}));
    }

    @ParameterizedTest
    @CsvSource({
            "'c w dfvsb 352345 ,,,', ''",
            "'r l f b l b', 'RIGHT LEFT FORWARD BACKWARD LEFT BACKWARD'",
            "'r rl fb b rlfb l', 'RIGHT BACKWARD LEFT'",
            "'r l 1r 0 FORWARD b LEFT', 'RIGHT LEFT BACKWARD'",
            "'', ''"
    })
    void parserChangeMultipleCommands(String input, String expected) {
        String[] inputArr = input.isEmpty() ? new String[]{} : input.split(" ");

        List<MoveDirection> expectedList = expected.isEmpty()
                ? List.of()
                : Arrays.stream(expected.split(" "))
                .map(MoveDirection::valueOf)
                .toList();

        assertEquals(expectedList, parserChange(inputArr));
    }
}
