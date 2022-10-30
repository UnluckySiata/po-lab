package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void testMoving1() {

        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Vector2d firstExpected = new Vector2d(2, 0);
        Vector2d secondExpected = new Vector2d(3, 4);
        assertEquals(positions[0], firstExpected);
        assertEquals(positions[1], secondExpected);
    }

    @Test
    void testMoving2() {
        String[] args = {"r", "l", "r", "f", "f", "b", "r", "r", "f", "b", "b", "f", "b", "b", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(3, 3);
        Vector2d[] positions = {new Vector2d(1, 1), new Vector2d(2, 1), new Vector2d(0, 0)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Vector2d firstExpected = new Vector2d(1, 2);
        Vector2d secondExpected = new Vector2d(2, 0);
        Vector2d thirdExpected = new Vector2d(1, 0);
        assertEquals(positions[0], firstExpected);
        assertEquals(positions[1], secondExpected);
        assertEquals(positions[2], thirdExpected);
    }

}