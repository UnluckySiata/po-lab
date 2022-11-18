package agh.ics.oop;

public class GrassField extends AbstractWorldMap {

    private int n;
    MapBoundary boundedField = new MapBoundary(elements);

    public GrassField(int n) {
        this.n = n;

        lowerLeft = new Vector2d(0, 0);
        Vector2d grassPos;
        for (int i = 0; i < n; ++i) {
            grassPos = spawnGrass();
            boundedField.insert(grassPos);
        }
        calculateBounds();
    }

    public boolean canMoveTo(Vector2d position) {
        return (!isOccupied(position) || this.objectAt(position) instanceof Grass);
    }

    private Vector2d spawnGrass() {

        double boundary = Math.sqrt(n * 10);
        int x, y;

        while (true) {
            x = (int) (Math.random() *  boundary);
            y = (int) (Math.random() *  boundary);

            Vector2d position = new Vector2d(x, y);

            if (!isOccupied(position)) {
                Grass grassI = new Grass(position);
                elements.put(position, grassI);
                return position;
            }

        }

    }

    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        Vector2d newGrassPos;
        if (canMoveTo(position)) {
            while (isOccupied(position)) {
                elements.remove(position);
                newGrassPos = spawnGrass();
                boundedField.pop(position);
                boundedField.insert(newGrassPos);
            }
            elements.put(position, animal);
            animal.addObserver(this);
            animal.addObserver(boundedField);
            return true;

        } else {
            throw new IllegalArgumentException("Animal couldn't be placed");
        }

    }

    public void calculateBounds() {
        int maxX = boundedField.setX.last().x;
        int maxY = boundedField.setY.last().y;
        int minX = boundedField.setX.first().x;
        int minY = boundedField.setY.first().y;
        lowerLeft = new Vector2d(minX, minY);
        upperRight = new Vector2d(maxX, maxY);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement e = elements.get(oldPosition);
        AbstractWorldMapElement unknownE = elements.get(newPosition);

        if (unknownE instanceof Grass) {
            elements.remove(newPosition);
            Vector2d newGrassPos = spawnGrass();
            boundedField.pop(newPosition);
            boundedField.insert(newGrassPos);
        }

        elements.remove(oldPosition);
        elements.put(newPosition, e);
    }

}
