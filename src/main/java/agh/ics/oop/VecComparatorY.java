package agh.ics.oop;

import java.util.Comparator;

import java.util.HashMap;

public class VecComparatorY implements Comparator<Vector2d> {
    private HashMap<Vector2d, AbstractWorldMapElement> elements;

    public VecComparatorY(HashMap<Vector2d, AbstractWorldMapElement> elements) {
        this.elements = elements;
    }

    public int compare(Vector2d p1, Vector2d p2) {
        if (p1.y == p2.y) {
            if (p1.x == p2.x) {
                if (elements.get(p1) instanceof Animal) return 1;
                else return -1;
            }

            if (p1.x > p2.x) return 1;
            else return -1;
        }

        if (p1.y > p2.y) return 1;
        else return -1;
    }


}
