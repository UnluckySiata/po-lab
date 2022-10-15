package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testToString() {
        Vector2d v = new Vector2d(10, 11);
        assertEquals(v.toString(), "(10,11)");
    }

    @Test
    void precedes() {
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(2, 4);
        assert(v1.precedes(v2));
        assert(!v2.precedes(v1));
    }

    @Test
    void follows() {
        Vector2d v1 = new Vector2d(5, 11);
        Vector2d v2 = new Vector2d(6, 12);
        Vector2d v3 = new Vector2d(4, 13);
        assert(v2.follows(v1));
        assert(!v1.follows(v2));
        assert(!v3.follows(v1));
        assert(!v2.follows(v3));
    }

    @Test
    void upperRight() {
        Vector2d v1 = new Vector2d(4, 3);
        Vector2d v2 = new Vector2d(1, 5);
        Vector2d res = new Vector2d(4, 5);

        assertEquals(v1.upperRight(v2), res);
    }

    @Test
    void lowerLeft() {
        Vector2d v1 = new Vector2d(4, 3);
        Vector2d v2 = new Vector2d(1, 5);
        Vector2d res = new Vector2d(1, 3);

        assertEquals(v1.lowerLeft(v2), res);
    }

    @Test
    void add() {
        Vector2d v1 = new Vector2d(4, 3);
        Vector2d v2 = new Vector2d(1, 5);
        Vector2d res = new Vector2d(5, 8);

        assertEquals(v1.add(v2), res);
    }

    @Test
    void substract() {
        Vector2d v1 = new Vector2d(4, 3);
        Vector2d v2 = new Vector2d(1, 5);
        Vector2d res = new Vector2d(3, -2);

        assertEquals(v1.substract(v2), res);
    }

    @Test
    void opposite() {
        Vector2d v1 = new Vector2d(1, 0);
        Vector2d v2 = new Vector2d(-5, -20);
        Vector2d res1 = new Vector2d(-1, 0);
        Vector2d res2 = new Vector2d(5, 20);

        assertEquals(v1.opposite(), res1);
        assertEquals(v2.opposite(), res2);

    }

    @Test
    void testEquals() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 4);
        Vector2d v3 = new Vector2d(1, 2);

        assert(v1.equals(v1));
        assert(v1.equals(v3));
        assert(!v2.equals(v1));
        assert(!v3.equals(v2));
    }
}