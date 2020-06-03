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


    /*    Entity spawnEntity(Entity entity, Location location) {

    }

    Entity spawnEntity(Entity entity, Location location, Vector direction) {

    }

    Entity spawnEntity(Entity entity, Location location, Entity target) {

    }

    void removeEntity(Entity entity, ObservableList<Entity> liste) {
        liste.remove(entity);
    }
*/
    public void nextRound() {
        for (Virus virus:getVirusList()){
            virus.move();

            for (Tower tower : getNodeList()) {
                if (virus.isInRange(tower)) {
                    virus.addTarget(tower);
                }
            }

            virus.act();
        }

        for (Projectile projectile : getProjectileList())
            projectile.move();

        for(int i = getNodeList().size()-1; i>=0; i--){  // Browse the node list
            for (int j = getVirusList().size()-1; j>=0; j--){  // Browse the virus list

                // Virus j is in range of node i and not yet in its range list so we add it to the list
                if (getNodeList().get(i).isInRange(getVirusList().get(j)) && !getNodeList().get(i).getInRangeVirus().contains(getVirusList().get(j))) {
                    getNodeList().get(i).addRangedVirus(getVirusList().get(j));
                }

                // Virus j is not in range of node i and is in its range list so we remove it from the list
                else if (!getNodeList().get(i).isInRange(getVirusList().get(j)) && getNodeList().get(i).getInRangeVirus().contains(getVirusList().get(j))) {
                    getNodeList().get(i).delRangedVirus(getVirusList().get(j));
                }

                if (!getVirusList().get(j).isAlive())
                    getVirusList().remove(j);
            }

            // Setting node target
            if(!getNodeList().get(i).getInRangeVirus().isEmpty()) {
                getNodeList().get(i).setTarget(getNodeList().get(i).getInRangeVirus().get(0));

            }
            else {
                getNodeList().get(i).setTarget(null);
            }
        }
    }




}
