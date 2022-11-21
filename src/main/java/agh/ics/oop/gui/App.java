package agh.ics.oop.gui;

import javafx.application.*;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

import agh.ics.oop.*;

public class App extends Application {
    public void start(Stage primaryStage) {

            List<String> args = getParameters().getRaw();
            MoveDirection[] directions = OptionsParser.parse(args);
            AbstractWorldMap map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            map.calculateBounds();
            GridPane grid = new GridPane();
            Scene scene = new Scene(grid, 400, 400);

            int col = 1, row = 1, minC, maxC, minR, maxR;

            Vector2d lowerLeft = map.getLowerLeft();
            Vector2d upperRight = map.getUpperRight();

            minC = lowerLeft.x;
            maxC = upperRight.x;
            minR = lowerLeft.y;
            maxR = upperRight.y;

            ColumnConstraints colC = new ColumnConstraints(20);
            RowConstraints rowC = new RowConstraints(20);

            for (int i = 0; i <= maxC - minC + 1; ++i) {
                grid.getColumnConstraints().add(colC);
            }

            for (int i = 0; i <= maxR - minR + 1; ++i) {
                grid.getRowConstraints().add(rowC);
            }

            Label first = new Label("y/x");
            GridPane.setHalignment(first, HPos.CENTER);
            grid.add(first, 0, 0);

            int i = 1;
            for (int c = minC; c <= maxC; ++c) {
                Label l = new Label(Integer.toString(c));
                GridPane.setHalignment(l, HPos.CENTER);
                grid.add(l, i++, 0);
            }

            i = 1;
            for (int r = maxR; r >= minR; --r) {
                Label l = new Label(Integer.toString(r));
                GridPane.setHalignment(l, HPos.CENTER);
                grid.add(l, 0, i++);
            }

            for (int r = maxR; r >= minR; --r) {
                col = 1;
                for (int c = minC; c <= maxC; ++c) {
                    Vector2d pos = new Vector2d(c, r);
                    String text;
                    if (map.isOccupied(pos)) text = map.objectAt(pos).toString();
                    else text = "";
                    Label currL = new Label(text);
                    GridPane.setHalignment(currL, HPos.CENTER);
                    grid.add(currL, col++, row);

                }
                ++row;
            }

            grid.setGridLinesVisible(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
