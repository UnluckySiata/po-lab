package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        Direction directions[] = convert(args);
        run(directions);
        System.out.println("System zakończył działanie");
        Vector2d position1 = new Vector2d(1, 2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2, 1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection d = MapDirection.NORTH;

        for (int i = 0; i < 4; ++i) {
            System.out.println(d.toString() + d.toUnitVector());
            d = d.next();
        }

        for (int i = 0; i < 4; ++i) {
            System.out.println(d.toString() + d.toUnitVector());
            d = d.previous();
        }
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
