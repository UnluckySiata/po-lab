package agh.ics.oop;

/*
Aby upewnić się, że każdy zwierzak znajduje się na innym polu,
można by zastosować dwuwymiarową tablicę typu bool reprezentującą naszą planszę i
zmienić metodę move dla klasy Animal tak, aby uniemożliwiała ruch na zajęte już pole
oraz modyfikowała wartość tablicy na true kiedy zwierzak stanie na pozycji oraz na false, kiedy
zwierzak pozycję opuści. Pozostaje tylko rozwiązać problem globalnego dostępu każdego obiektu
klasy Animal do tej samej tablicy - można to rozwiązać np. dodając atrybut private bool[] isTaken do klasy Animal,
a tablicę stworzyć podczas wykonywania programu i przekazać ją przez referencję każdemu zwierzęciu.
 */
public class Animal {

    private Vector2d position;
    private MapDirection orientation;

    public Animal() {
        position = new Vector2d(2, 2);
        orientation = MapDirection.NORTH;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {

        int deltaX = 0;
        int deltaY = 0;

        switch (direction) {
            case RIGHT:
                this.orientation = this.orientation.next();
                break;

            case LEFT:
                this.orientation = this.orientation.previous();
                break;

            case FORWARD:

                switch (this.orientation) {
                    case NORTH:
                        deltaY = 1;
                        break;
                    case SOUTH:
                        deltaY = -1;
                        break;
                    case EAST:
                        deltaX = 1;
                        break;
                    case WEST:
                        deltaX = -1;
                        break;
                }

                break;

            case BACKWARD:

                switch (this.orientation) {
                    case NORTH:
                        deltaY = -1;
                        break;
                    case SOUTH:
                        deltaY = 1;
                        break;
                    case EAST:
                        deltaX = -1;
                        break;
                    case WEST:
                        deltaX = 1;
                        break;
                }

                break;
        }

        if (deltaX != 0 && this.position.x + deltaX >= 0 && this.position.x + deltaX <= 4) {
            this.position = this.position.add(new Vector2d(deltaX, 0));
        }

        if (deltaY != 0 && this.position.y + deltaY >= 0 && this.position.y + deltaY <= 4) {
            this.position = this.position.add(new Vector2d(0, deltaY));
        }

    }

    @Override
    public String toString() {
        return "Zwierzak znajduje się na pozycji: " + this.position.toString() + ", a jego orientacja to: " + this.orientation.toString() + ".";
    }

    public MapDirection getOrientation() {
        return orientation;
    }
}
