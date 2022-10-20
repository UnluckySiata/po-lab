package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] moves) {
        int validLength = 0;
        boolean[] isValid = new boolean[moves.length];

        for (int i = 0; i < moves.length; ++i) {
            switch (moves[i]) {
                case "f", "forward", "b", "backward", "r", "right", "l", "left":
                    ++validLength;
                    isValid[i] = true;
                    break;

                default:
                    isValid[i] = false;
                    break;
            }
        }

        MoveDirection[] result = new MoveDirection[validLength];

        int currIndex = 0;
        for (int i = 0; i < moves.length; ++i) {

            if (isValid[i]) {
                result[currIndex++] = switch (moves[i]) {
                    case "f", "forward" -> MoveDirection.FORWARD;
                    case "b", "backward" -> MoveDirection.BACKWARD;
                    case "r", "right" -> MoveDirection.RIGHT;
                    case "l", "left" -> MoveDirection.LEFT;
                    default -> null;
                };
            }
        }

        return result;
    }
}
