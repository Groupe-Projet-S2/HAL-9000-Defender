package controllers.listeners;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import models.entities.projectile.Projectile;
import views.ProjectileView;

public class ProjectileListener implements ListChangeListener<Projectile> {

    private Pane world;
    public ProjectileListener(Pane world) {
        this.world = world;
    }

    @Override
    public void onChanged(Change<? extends Projectile> c) {
        while (c.next()) {
            for (Projectile projectile : c.getAddedSubList()) {
                world.getChildren().add(ProjectileView.renderProjectile(projectile));
            }
            for (Projectile projectile : c.getRemoved()) {
                world.getChildren().remove(world.lookup("#P" + (projectile).getId()));
            }
        }
    }
}
