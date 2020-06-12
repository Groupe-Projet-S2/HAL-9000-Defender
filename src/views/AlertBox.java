package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.entities.virus.Adware;
import models.entities.virus.Ransomware;
import models.entities.virus.Virus;
import models.environment.Location;

public class AlertBox {

    public AlertBox() {}

    public VBox popUp(Adware virus, String id) {
        VBox layout = new VBox();
        Label title = new Label("It's an ad ! - #P" + id);
        layout.setId("P" + id);

        layout.toFront();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        layout.setPrefWidth(200);
        layout.setPrefHeight(100);
        layout.setLayoutX((int) (Math.random() * 500));
        layout.setLayoutY((int) (Math.random() * 700));
        layout.styleProperty().setValue("-fx-background-color: white");
        layout.setOpacity(0.7);

        Button close = new Button("OK");
        close.setOnAction(e -> virus.close(id));
        layout.getChildren().addAll(title, close);
        return layout;
    }

    public VBox ransom(Ransomware virus, String id) {
        VBox layout = new VBox();
        Label title = new Label("Pay now if you want to live.");
        title.styleProperty().setValue("-fx-text-fill: #ffffff");

        layout.setId("P" + id);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefHeight(800);
        layout.setPrefWidth(800);
        layout.styleProperty().setValue("-fx-background-color: #0000FF");
        layout.setOpacity(0.7);

        Button pay = new Button("Pay");
        pay.setOnAction(e -> virus.close());
        layout.getChildren().addAll(title, pay);
        return layout;
    }
}
