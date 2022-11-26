package agh.ics.oop;

public class Grass extends AbstractWorldMapElement {

    public Grass(Vector2d position) {
        this.position = position;
    }

    public String getImageName() {
        return "grass.png";
    }

    public String getLabelName() {
        return "Trawa";
    }

    public String toString() {
        return "*";
    }

}
