package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class World {

    private ObservableList<Entity> Nodes;
    private ObservableList<Entity> Virus;

    public World() {
        this.Nodes = FXCollections.observableArrayList();
        this.Virus = FXCollections.observableArrayList();
    }

    public void addToList(Entity e) {
        this.Virus.add(e);
    }

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
    void nextRound() {
        for(int i=0;i<Virus.size(); i++){
            this.Virus.get(i).setLocation(this.Virus.get(i).getLocation().getX()+1, this.Virus.get(i).getLocation().getY());
        }
    }




}
