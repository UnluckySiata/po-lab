package agh.ics.oop;

import java.util.ArrayList;

public class Animal extends AbstractWorldMapElement {

    private MapDirection orientation;
    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    private IWorldMap map;

    public Animal(IWorldMap map) {
        position = new Vector2d(2, 2);
        orientation = MapDirection.NORTH;
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        position = initialPosition;
        orientation = MapDirection.NORTH;
        this.map = map;
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

        Vector2d newPosition = this.position.add(new Vector2d(deltaX, deltaY));

        if (map.canMoveTo(newPosition)) {
            this.positionChanged(this.position, newPosition);
            this.position = newPosition;
        }

    }

    void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer) {
        //for (int i = 0; i < observers.size(); ++i) {
            //if (observers.get(i) == observer) observers.re
        //}
        observers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver o: observers) {
            o.positionChanged(oldPosition, newPosition);
        }
    }

    @Override
    public String toString() {
        //return "Zwierzak znajduje się na pozycji: " + this.position.toString() + ", a jego orientacja to: " + this.orientation.toString() + ".";
        return switch (this.orientation) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
        };
    }

    public MapDirection getOrientation() {
        return orientation;
    }

}
