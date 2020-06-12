package controllers.listeners;

import javafx.collections.MapChangeListener;
import javafx.scene.layout.Pane;
import models.entities.tower.Tower;
import models.entities.virus.Adware;
import models.entities.virus.Ransomware;
import models.entities.virus.Virus;
import views.AlertBox;

public class HostileBoxesListener implements MapChangeListener<String, Virus> {

    private final Pane environment;
    private final AlertBox alertBox;
    public HostileBoxesListener(Pane environment) {
        this.environment = environment;
        this.alertBox = new AlertBox();
    }

    @Override
    public void onChanged(Change<? extends String, ? extends Virus> change) {
        if (change.wasAdded()) {
            if (change.getValueAdded() instanceof Adware)
                environment.getChildren().add(alertBox.popUp((Adware) change.getValueAdded(), change.getKey()));
            else if (change.getValueAdded() instanceof Ransomware) {
                environment.getChildren().add(alertBox.ransom((Ransomware) change.getValueAdded(), change.getValueAdded().getId()));
                change.getValueAdded().getWorld().getNodeList().forEach(Tower::disable);
            }
        }
        if (change.wasRemoved()) {
            environment.getChildren().remove(environment.lookup("#P" + change.getKey()));

            if (change.getValueRemoved() instanceof Ransomware) {
                change.getValueRemoved().getWorld().getNodeList().forEach(Tower::enable);
            }
        }
    }
}
