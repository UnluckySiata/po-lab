package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class OptionsParserTest {
    @Test
    void parseTest1() {
        String[] args = {"f", "f", "b", "a"};
        Boolean passed = false;
        try {
            MoveDirection[] moves = OptionsParser.parse(Arrays.asList(args));
            passed = true;
        } catch (IllegalArgumentException ex) {
            assertEquals(ex.getMessage(), "a is not a legal move specification");
        }
        assert(!passed);
    }

    @Test
    void parseTest2() {
        String[] args = {"f", "f", "b"};
        Boolean passed = true;
        try {
            MoveDirection[] moves = OptionsParser.parse(Arrays.asList(args));
        } catch (IllegalArgumentException ex) {
            passed = false;
        }
        assert(passed);
    }
}
