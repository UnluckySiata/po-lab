package agh.ics.oop;

import java.util.ArrayList;
import agh.ics.oop.gui.*;

public class SimulationEngine implements Runnable {
    private final int moveDelay = 1000;
    private IWorldMap map;
    MoveDirection[] moves = {};
    Vector2d[] positions;
    private ArrayList<Animal> animals = new ArrayList<>();
    IGridPaneChangeObserver observer;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions, IGridPaneChangeObserver observer) {
        this.map = map;
        this.moves = moves;
        this.positions = positions;
        this.observer = observer;

        for (Vector2d position: positions) {
            Animal newAnimal = new Animal(this.map, position);
            this.map.place(newAnimal);
            animals.add(newAnimal);
        }

    }

    public SimulationEngine(IWorldMap map, Vector2d[] positions, IGridPaneChangeObserver observer) {
        this.map = map;
        this.positions = positions;
        this.observer = observer;

        for (Vector2d position: positions) {
            Animal newAnimal = new Animal(this.map, position);
            this.map.place(newAnimal);
            animals.add(newAnimal);
        }

    }

    public void setMoves(MoveDirection[] moves) {
        this.moves = moves;
    }

    public void run() {
        int a_i;
        Animal currA;

        for (int i = 0; i < moves.length; ++i) {
            try {
                Thread.sleep(moveDelay);
                a_i = i % positions.length;
                currA = animals.get(a_i);
                currA.move(moves[i]);
                positions[a_i] = currA.getPosition();
                observer.gridPaneChanged();
            } catch (Exception ex) {
                System.out.println("Simulation finished with " + ex.getMessage());
                System.exit(0);
            }
        }
    }
}
