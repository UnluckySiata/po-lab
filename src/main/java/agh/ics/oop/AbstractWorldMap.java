package agh.ics.oop;

import java.util.ArrayList;

abstract class AbstractWorldMap implements IWorldMap {

    protected ArrayList<AbstractWorldMapElement> elements = new ArrayList<>();
    protected Vector2d lowerLeft, upperRight;

    public boolean isOccupied(Vector2d position) {
        for (AbstractWorldMapElement e: elements) {
            if (e.getPosition().equals(position)) return true;
        }
        return false;
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            elements.add(animal);
            return true;

        } else return false;

    }

    public Object objectAt(Vector2d position) {
        Object result = null;
        for (AbstractWorldMapElement e: elements) {
            if (e.getPosition().equals(position))  {
                result = e;
                if (e instanceof Animal) break;
            }
        }
        return result;
    }

    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(lowerLeft, upperRight);
    }
}
