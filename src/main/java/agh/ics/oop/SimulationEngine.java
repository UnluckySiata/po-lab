package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {
    private IWorldMap map;
    final MoveDirection[] moves;
    final Vector2d[] positions;
    private ArrayList<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions) {
        this.map = map;
        this.moves = moves;
        this.positions = positions;

        for (Vector2d position: positions) {
            Animal newAnimal = new Animal(this.map, position);
            this.map.place(newAnimal);
            animals.add(newAnimal);
        }

    }

    public void run() {
        int a_i;
        Animal currA;

        for (int i = 0; i < moves.length; ++i) {
            a_i = i % positions.length;
            currA = animals.get(a_i);
            // if isOcupied and is grass -> spawnGrass
            currA.move(moves[i]);
            positions[a_i] = currA.getPosition();
        }
    }
}
