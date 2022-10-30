package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {

    private ArrayList<Animal> animals = new ArrayList<>();
    final Vector2d lowerLeft, upperRight;

    public RectangularMap(int width, int height) {
        this.upperRight = new Vector2d(width - 1, height - 1);
        this.lowerLeft = new Vector2d(0, 0);
    }

    public boolean canMoveTo(Vector2d position){
        return (!isOccupied(position) && position.follows(this.lowerLeft) && position.precedes(this.upperRight));
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.add(animal);
            return true;

        } else return false;

    }

    public boolean isOccupied(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.getPosition().equals(position)) return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.getPosition().equals(position)) return animal;
        }
        return null;
    }

    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(lowerLeft, upperRight);
    }
}
