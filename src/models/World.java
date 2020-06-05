package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class World {

    private ObservableList<Entity> entities;

    public World() {
        this.entities = FXCollections.observableArrayList();

    }

    public ObservableList<Entity> getEntities() {
        return entities;
    }

    public void addToList(Entity e) {
        this.entities.add(e);
    }

    public ObservableList<Tower> getNodeList(){
        ObservableList<Tower> towerList = FXCollections.observableArrayList();
        for (Entity e:entities){
            if (Entity.isNode(e))
                towerList.add((Tower)e);
        }
        return towerList;
    }

    public ObservableList<Virus> getVirusList() {
        ObservableList<Virus> virusList = FXCollections.observableArrayList();
        for (Entity e:entities){
            if (Entity.isVirus(e))
                virusList.add((Virus)e);
        }
        return virusList;
    }


    /*public ObservableList<Projectile> getProjectileList(){
        ObservableList<Projectile> projectileList = FXCollections.observableArrayList();
        projectileList.addAll((Collection<? extends Projectile>) entities.stream().filter(entity -> Entity.isProjectile(entity)).collect(Collectors.toList()));
        return projectileList;
    }*/

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
        }

        for (Tower tower:getNodeList()){
            tower.act();
        }


    }




}
