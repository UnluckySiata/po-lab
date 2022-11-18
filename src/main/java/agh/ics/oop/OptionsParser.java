package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] moves) {

        MoveDirection[] result = new MoveDirection[moves.length];

        for (int i = 0; i < moves.length; ++i) {
            switch (moves[i]) {
                case "f", "forward" -> result[i] = MoveDirection.FORWARD;
                case "b", "backward" -> result[i] = MoveDirection.BACKWARD;
                case "r", "right" -> result[i] = MoveDirection.RIGHT;
                case "l", "left" -> result[i] = MoveDirection.LEFT;
                default -> throw new IllegalArgumentException(moves[i] + " is not a legal move specification");
            };
        }

        return result;
    }
}
