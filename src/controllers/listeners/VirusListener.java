package controllers.listeners;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import models.entities.virus.Virus;
import views.VirusView;

public class VirusListener implements ListChangeListener<Virus> {

    private final Pane world;
    public VirusListener(Pane world) {
        this.world = world;
    }

    @Override
    public void onChanged(Change<? extends Virus> c) {
        while (c.next()) {
            if (c.wasAdded()) {
                for (Virus virus : c.getAddedSubList()) {
                    world.getChildren().add(VirusView.renderVirus(virus));
                }
            } else if (c.wasRemoved()) {
                for (Virus virus : c.getRemoved()) {
                    world.getChildren().remove(world.lookup("#S" + (virus).getId()));
                }
            }
        }
    }
}
