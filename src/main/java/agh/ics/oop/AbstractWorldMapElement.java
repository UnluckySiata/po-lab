package agh.ics.oop;

public abstract class AbstractWorldMapElement {
    protected Vector2d position;

    public abstract String getImageName();

    public abstract String getLabelName();

    public Vector2d getPosition() {
        return position;
    }
}
