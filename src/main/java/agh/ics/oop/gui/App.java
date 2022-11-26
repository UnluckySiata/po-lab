package agh.ics.oop.gui;

import javafx.application.*;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

import agh.ics.oop.*;

public class App extends Application implements IGridPaneChangeObserver {
    private Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
    private AbstractWorldMap map = new GrassField(10);
    private SimulationEngine engine;
    private GridPane grid = new GridPane();
    private TextField text = new TextField();
    private Button button = new Button("Start");
    private VBox vBox = new VBox(button, text);
    private HBox hBox = new HBox(grid, vBox);
    private Scene scene = new Scene(hBox, 1000, 1000);

    public void start(Stage primaryStage) {

            //List<String> args = getParameters().getRaw();
            //MoveDirection[] directions = OptionsParser.parse(args);
            engine = new SimulationEngine(map, positions, this);

            button.setOnAction(actionEvent -> {
                MoveDirection[] directions = OptionsParser.parse(Arrays.asList(text.getText().split(" ")));
                engine.setMoves(directions);
                Thread engineThread = new Thread(engine);
                engineThread.start();
            });

            vBox.setAlignment(Pos.TOP_CENTER);
            hBox.setAlignment(Pos.CENTER);
            drawGrid();
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public void drawGrid() {
            int col = 1, row = 1, minC, maxC, minR, maxR;

            map.calculateBounds();
            Vector2d lowerLeft = map.getLowerLeft();
            Vector2d upperRight = map.getUpperRight();

            minC = lowerLeft.x;
            maxC = upperRight.x;
            minR = lowerLeft.y;
            maxR = upperRight.y;

            grid.getChildren().clear();

            ColumnConstraints colC = new ColumnConstraints(50);
            RowConstraints rowC = new RowConstraints(50);
            grid.getColumnConstraints().clear();
            grid.getRowConstraints().clear();

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
                    if (map.isOccupied(pos)) {
                        GuiElementBox e = new GuiElementBox((AbstractWorldMapElement) (map.objectAt(pos)));
                        grid.add(e.vBox, col++, row);
                    } else {
                        Label currL = new Label("");
                        GridPane.setHalignment(currL, HPos.CENTER);
                        grid.add(currL, col++, row);
                    }

                }
                ++row;
            }

            grid.setGridLinesVisible(false);
            grid.setGridLinesVisible(true);

        }


        public void gridPaneChanged() {
            Platform.runLater( () -> {
                    drawGrid();
                });
        }

    }
