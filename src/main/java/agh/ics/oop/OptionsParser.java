package agh.ics.oop;

import java.util.List;

public class OptionsParser {
    public static MoveDirection[] parse(List<String> moves) {

        MoveDirection[] result = new MoveDirection[moves.size()];

        for (int i = 0; i < moves.size(); ++i) {
            switch (moves.get(i)) {
                case "f", "forward" -> result[i] = MoveDirection.FORWARD;
                case "b", "backward" -> result[i] = MoveDirection.BACKWARD;
                case "r", "right" -> result[i] = MoveDirection.RIGHT;
                case "l", "left" -> result[i] = MoveDirection.LEFT;
                default -> throw new IllegalArgumentException(moves.get(i) + " is not a legal move specification");
            };
        }

        return result;
    }
}
