package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.entities.Adware;
import models.environment.Location;

public class AlertBox {

    private Adware adware;
    public AlertBox(Adware adware) {
        this.adware = adware;
    }

    public VBox popUp(Location location) {
        VBox layout = new VBox();
        Label title = new Label("It's an ad !");
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        layout.setPrefWidth(200);
        layout.setPrefHeight(100);
        layout.setLayoutX(location.getCol() - 100);
        layout.setLayoutY(location.getRow() - 50);
        layout.styleProperty().setValue("-fx-background-color: white");
        //layout.styleProperty().setValue("-fx-border: black");
        layout.setOpacity(0.5);
        Button close = new Button("OK");
        close.setOnAction(e -> adware.close(layout));
        layout.getChildren().addAll(title, close);
        return layout;
    }
}
