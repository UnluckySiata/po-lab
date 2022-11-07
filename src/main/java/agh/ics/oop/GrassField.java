package agh.ics.oop;

public class GrassField extends AbstractWorldMap {

    private int n, maxX, maxY;

    public GrassField(int n) {
        this.n = n;

        double boundary = Math.sqrt(n * 10);
        int x, y;

        for (int i = 0; i < n; ++i) {
            x = (int) (Math.random() *  boundary);
            y = (int) (Math.random() *  boundary);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);

            Vector2d position = new Vector2d(x, y);
            boolean free = true;

            for (AbstractWorldMapElement e: elements) {
                if (e.getPosition().equals(position)) {
                    free = false;
                    break;
                }
            }

            if (free) {
                Grass grassI = new Grass(position);
                elements.add(grassI);
            } else --i;

        }

        lowerLeft = new Vector2d(0, 0);
        upperRight = new Vector2d(maxX, maxY);

    }

    public boolean canMoveTo(Vector2d position) {
        return (!isOccupied(position) || this.objectAt(position) instanceof Grass);
    }

    public void calculateUpperRight() {
        for (AbstractWorldMapElement e: elements) {
            Vector2d pos = e.getPosition();
            maxX = Math.max(maxX, pos.x);
            maxY = Math.max(maxY, pos.y);

        }
    }

}
