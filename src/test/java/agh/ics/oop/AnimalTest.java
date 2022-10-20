package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void testMoving() {
        Animal a = new Animal();
        assert(a.isAt(new Vector2d(2, 2)));
        String[] array1 = {"r", "forward", "aa", "f", "f"};
        MoveDirection[] moves1 = OptionsParser.parse(array1);
        String[] array2 = {"l", "f", "f", "f", "f", "es", "backward", "b", "b", "left", "left", "right", "f"};
        MoveDirection[] moves2 = OptionsParser.parse(array2);

        for (int i = 0; i < moves1.length; ++i) {
            a.move(moves1[i]);
        }

        assert(a.isAt(new Vector2d(4, 2)));
        assertEquals(a.getOrientation(), MapDirection.EAST);

        for (int i = 0; i < moves2.length; ++i) {
            a.move(moves2[i]);
        }

        assert(a.isAt(new Vector2d(3, 1)));
        assertEquals(a.getOrientation(), MapDirection.WEST);

    }

}