package agh.ics.oop;

import java.util.HashMap;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected HashMap<Vector2d, AbstractWorldMapElement> elements = new HashMap<>();
    protected Vector2d lowerLeft, upperRight;

    public boolean isOccupied(Vector2d position) {
        return elements.containsKey(position);
    }

    public Object objectAt(Vector2d position) {
        return elements.get(position);
    }

    public void calculateBounds() {
        return;
    }

    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        this.calculateBounds();
        return map.draw(lowerLeft, upperRight);
    }
}
