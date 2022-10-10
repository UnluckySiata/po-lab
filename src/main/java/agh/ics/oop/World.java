package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        Direction directions[] = convert(args);
        run(directions);
        System.out.println("System zakończył działanie");
    }

    static void run(Direction[] directions) {

        for (int i = 0; i < directions.length; ++i) {
            if (i == directions.length - 1) {
                System.out.println(directions[i]);
            } else {
                System.out.print(directions[i] + ", ");
            }

        }

        for (Direction direction : directions) {
            switch (direction) {
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;

                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;

                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;

                case LEFT:
                    System.out.println("Zwierzak skręca w lewo");
                    break;
            }
        }

    }

    static Direction[] convert(String[] args) {
        Direction moves[] = new Direction [args.length];

        for (int i = 0; i < args.length; ++i) {
            moves[i] = switch (args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> Direction.INVALID;
            };
        }

        return moves;

    }

}
