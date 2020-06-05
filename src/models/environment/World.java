package models.environment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.layout.HBox;
import models.entities.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class World {

    private ObservableList<Entity> entities;
    private Set<HBox> overlays;

    public World() {
        this.entities = FXCollections.observableArrayList();
        this.overlays = new HashSet<>();
    }

    public void addToList(Entity e) {
        this.entities.add(e);
    }

    public ObservableList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Tower> getNodeList(){
        ArrayList<Tower> towerList = new ArrayList<>();
        for (Entity e:entities){
            if (Entity.isNode(e))
                towerList.add((Tower) e);
        }
        return towerList;
    }

    public Set<HBox> getOverlays() {
        return this.overlays;
    }

    public ArrayList<Virus> getVirusList() {
        ArrayList<Virus> virusList = new ArrayList<>();
        for (Entity e:entities){
            if (Entity.isVirus(e))
                virusList.add((Virus)e);
        }
        return virusList;
    }

    public ArrayList<Projectile> getProjectileList(){
        ArrayList<Projectile> projectileList = new ArrayList<>();
        // projectileList.addAll((Collection<? extends Projectile>) entities.stream().filter(entity -> Entity.isProjectile(entity)).collect(Collectors.toList()));
        for (Entity p : entities)
            if (Entity.isProjectile(p))
                projectileList.add((Projectile) p);
        return projectileList;
    }

    public Virus getVirus(String id){
        for (Virus virus:getVirusList()){
            if (virus.getId().equals(id))
                return virus;
        }
        return null;
    }

    public Tower getNode(String id){
        for (Tower tower :getNodeList()){
            if (tower.getId().equals(id))
                return tower;
        }
        return null;
    }

    /*public Projectile getProjectile(String id){
        for (Projectile projectile:this.projectiles){
            if (projectile.getId().equals(id))
                return projectile;
        }
        return null;
    }*/

   /* void removeEntity(Entity entity, ObservableList<Entity> liste) {
        liste.remove(entity);
    }*/

    public void nextRound() {
        for (Virus virus:getVirusList()) {
            virus.move();
            virus.act();
        }
        for (Tower tower:getNodeList()) {
            tower.detection();
            tower.act();
        }
        for (Projectile projectile : getProjectileList()) {
            projectile.move();
        }

    }
}
