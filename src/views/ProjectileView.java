package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.entities.Projectile;
import models.environment.Location;

public class ProjectileView {
    public static Circle renderProjectile(Projectile projectile) {
        Circle p = new Circle(5, Color.BLUE);
        Location pLoc = projectile.getLocation();
        p.setCenterX(pLoc.getCol());
        p.setCenterY(pLoc.getRow());
        pLoc.getRowProperty().addListener((obs, prev, next) -> p.setCenterY(next.intValue()));
        pLoc.getColProperty().addListener((obs, prev, next) -> p.setCenterX(next.intValue()));
        return p;
    }
}
