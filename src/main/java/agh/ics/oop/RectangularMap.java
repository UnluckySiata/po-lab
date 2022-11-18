package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        this.upperRight = new Vector2d(width - 1, height - 1);
        this.lowerLeft = new Vector2d(0, 0);
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            elements.put(animal.getPosition(), animal);
            animal.addObserver(this);
            return true;

        } else {
            throw new IllegalArgumentException("Animal couldn't be placed");
        }

    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement e = elements.get(oldPosition);
        elements.remove(oldPosition);
        elements.put(newPosition, e);
    }

    public boolean canMoveTo(Vector2d position){
        return (!isOccupied(position) && position.follows(this.lowerLeft) && position.precedes(this.upperRight));
    }

}
