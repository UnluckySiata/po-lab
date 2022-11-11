package agh.ics.oop;

public class GrassField extends AbstractWorldMap {

    private int n, maxX, maxY, minX, minY;

    public GrassField(int n) {
        this.n = n;

        lowerLeft = new Vector2d(0, 0);
        spawnGrass(n);

    }

    public boolean canMoveTo(Vector2d position) {
        return (!isOccupied(position) || this.objectAt(position) instanceof Grass);
    }

    private void spawnGrass(int quantity) {

        double boundary = Math.sqrt(n * 10);
        int x, y;
        int placed = 0;

        while (placed < quantity) {
            x = (int) (Math.random() *  boundary);
            y = (int) (Math.random() *  boundary);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);

            Vector2d position = new Vector2d(x, y);

            if (!isOccupied(position)) {
                Grass grassI = new Grass(position);
                elements.put(position, grassI);
                ++placed;
            }

        }

        upperRight = new Vector2d(maxX, maxY);
    }

    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            while (isOccupied(position)) {
                elements.remove(position);
                this.spawnGrass(1);
            }
            elements.put(position, animal);
            animal.addObserver(this);
            return true;

        } else return false;

    }

    public void calculateBounds() {
        for (Vector2d pos: elements.keySet()) {
            maxX = Math.max(maxX, pos.x);
            maxY = Math.max(maxY, pos.y);

            minX = Math.min(minX, pos.x);
            minY = Math.min(minY, pos.y);

        }
        lowerLeft = new Vector2d(minX, minY);
        upperRight = new Vector2d(maxX, maxY);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement e = elements.get(oldPosition);
        AbstractWorldMapElement unknownE = elements.get(newPosition);

        if (unknownE instanceof Grass) {
            elements.remove(newPosition);
            this.spawnGrass(1);
        }

        elements.remove(oldPosition);
        elements.put(newPosition, e);
    }

}
