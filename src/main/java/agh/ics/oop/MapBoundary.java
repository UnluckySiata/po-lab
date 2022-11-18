package agh.ics.oop;

import java.util.HashMap;
import java.util.TreeSet;

class MapBoundary implements IPositionChangeObserver {

    TreeSet<Vector2d> setX, setY;

    public MapBoundary(HashMap<Vector2d, AbstractWorldMapElement> elements) {
        setX = new TreeSet<>(new VecComparatorX(elements));
        setY = new TreeSet<>(new VecComparatorY(elements));
    }

    boolean insert(Vector2d pos) {
        boolean success = setX.add(pos);
        setY.add(pos);
        return success;
    }

    boolean pop(Vector2d pos) {
        boolean success = setX.remove(pos);
        setY.remove(pos);
        return success;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        setX.remove(oldPosition);
        setY.remove(oldPosition);

        setX.add(newPosition);
        setY.add(newPosition);

    }


}
