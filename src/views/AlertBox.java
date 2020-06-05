package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.entities.Adware;
import models.entities.Ransomware;
import models.entities.Virus;
import models.environment.Location;

public class AlertBox {

    private Virus virus;
    public AlertBox(Virus virus) {
        this.virus = virus;
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
        close.setOnAction(e -> ((Adware) virus).close(layout));
        layout.getChildren().addAll(title, close);
        return layout;
    }

    public VBox ransom() {
        VBox layout = new VBox();
        Label title = new Label("Pay now if you want to live.");
        title.styleProperty().setValue("-fx-text-fill: #ffffff");
        layout.setAlignment(Pos.CENTER);
        layout.setLayoutX(0);
        layout.setLayoutY(0);
        layout.setPrefHeight(800);
        layout.setPrefWidth(800);
        layout.styleProperty().setValue("-fx-background-color: #0000FF");
        layout.setOpacity(0.7);
        Button pay = new Button("Pay");
        pay.setOnAction(e -> ((Ransomware) virus).close());
        layout.getChildren().addAll(title, pay);
        return layout;
    }
}
