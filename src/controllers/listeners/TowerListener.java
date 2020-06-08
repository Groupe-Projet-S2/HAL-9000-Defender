package controllers.listeners;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import models.entities.tower.Tower;
import models.environment.World;
import views.TowerView;

public class TowerListener implements ListChangeListener<Tower> {

    private Pane pane;
    private World world;
    public TowerListener(Pane pane, World world) {
        this.pane = pane;
        this.world = world;
    }

    @Override
    public void onChanged(Change<? extends Tower> c) {
        while (c.next()) {
            if (c.wasAdded()) {
                for (Tower tower : c.getAddedSubList()) {
                    pane.getChildren().add(TowerView.drawRadius(100, world.getSelectedNodeLocation(), (tower).getId()));
                    pane.getChildren().add(TowerView.renderTower(tower, world.getSelectedNodeLocation(), world.getSelectedNodePreview().getImage()));
                }
            } else if (c.wasRemoved()) {
                for (Tower tower : c.getRemoved()) {
                    pane.getChildren().remove(pane.lookup("T" + (tower).getId()));
                    pane.getChildren().remove(pane.lookup("R" + (tower).getId()));
                }
            }
        }
    }
}
