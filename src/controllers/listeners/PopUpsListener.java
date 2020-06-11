package controllers.listeners;

import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.scene.layout.Pane;
import models.entities.virus.Virus;
import models.environment.Location;
import views.AlertBox;

public class PopUpsListener implements MapChangeListener<Location, Virus> {

    private Pane environment;
    private AlertBox alertBox;
    public PopUpsListener(Pane environment) {
        this.environment = environment;
        this.alertBox = new AlertBox();
    }

    @Override
    public void onChanged(Change<? extends Location, ? extends Virus> change) {
        if (change.wasAdded()) {
            alertBox.setVirus(change.getValueAdded());
            environment.getChildren().add(alertBox.popUp(change.getKey()));
        }
        if (change.wasRemoved()) {
            environment.getChildren().remove(environment.lookup("#P" + change.getValueRemoved().getId()));
        }
    }
}
