package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.entities.projectile.Projectile;
import models.environment.Location;

public class ProjectileView {
    public static Circle renderProjectile(Projectile projectile) {
        Circle p = new Circle(5, Color.BLUE);
        Location pLoc = projectile.getPosition();
        p.setCenterX(pLoc.getCol());
        p.setCenterY(pLoc.getRow());
        pLoc.getRowProperty().addListener((obs, prev, next) -> p.setCenterY(next.intValue()));
        pLoc.getColProperty().addListener((obs, prev, next) -> p.setCenterX(next.intValue()));
        p.setId("P" + projectile.getId());
        return p;
    }
}
