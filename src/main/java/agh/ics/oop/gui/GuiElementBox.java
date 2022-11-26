package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class GuiElementBox {
    VBox vBox;
    public GuiElementBox(AbstractWorldMapElement e) {
        try {
            String name = e.getImageName();
            Label label = new Label(e.getLabelName());
            Image image = new Image(new FileInputStream("src/main/resources/" + name));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            vBox = new VBox();
            vBox.getChildren().addAll(imageView, label);
            vBox.setAlignment(Pos.CENTER);
            //Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1)));
            //vBox.setBorder(border);
        } catch(FileNotFoundException ex) {
            System.out.println("es");
            System.out.println(ex);
            System.exit(1);
        }
    }
}
