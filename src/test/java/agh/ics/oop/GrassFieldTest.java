package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void testMoving1() {

        int initialN = 20;
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(initialN);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Vector2d firstExpected = new Vector2d(2, -1);
        Vector2d secondExpected = new Vector2d(3, 7);
        assertEquals(positions[0], firstExpected);
        assertEquals(positions[1], secondExpected);

        for (Vector2d p: positions) {
            assert(map.objectAt(p) instanceof Animal);
            assert(!map.canMoveTo(p));
        }

        assert(map.canMoveTo(new Vector2d(1000, 1000)));

        int n = 0;
        GrassField gMap = (GrassField)map;
        for (Vector2d pos: gMap.elements.keySet()) {
            AbstractWorldMapElement e = gMap.elements.get(pos);
            if (e instanceof Grass) ++n;
        }

        assertEquals(n, initialN);

    }

    @Test
    void testMoving2() {
        int initialN = 10;
        String[] args = {"r", "l", "r", "f", "f", "b", "r", "r", "f", "b", "b", "f", "b", "b", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(initialN);
        Vector2d[] positions = {new Vector2d(1, 1), new Vector2d(2, 1), new Vector2d(0, 0)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Vector2d firstExpected = new Vector2d(1, 3);


        Vector2d secondExpected = new Vector2d(2, -1);
        Vector2d thirdExpected = new Vector2d(2, 0);
        assertEquals(positions[0], firstExpected);
        assertEquals(positions[1], secondExpected);
        assertEquals(positions[2], thirdExpected);

        for (Vector2d p: positions) {
            assert(map.objectAt(p) instanceof Animal);
            assert(!map.canMoveTo(p));
        }

        assert(map.canMoveTo(new Vector2d(1000, 1000)));

        int n = 0;
        GrassField gMap = (GrassField)map;
        for (Vector2d pos: gMap.elements.keySet()) {
            AbstractWorldMapElement e = gMap.elements.get(pos);
            if (e instanceof Grass) ++n;
        }

        assertEquals(n, initialN);
    }

    @Test
    void testChange() {
        GrassField g = new GrassField(10);
        Vector2d v1 = new Vector2d(10, 10);
        Vector2d v2 = new Vector2d(20, 10);
        Animal a = new Animal(g, v1);
        g.elements.put(a.position, a);
        g.positionChanged(v1, v2);
        assert(g.canMoveTo(v1));
        assert(!g.canMoveTo(v2));

    }

}
