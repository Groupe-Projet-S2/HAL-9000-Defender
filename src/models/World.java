package models;

import javafx.collections.ObservableList;

public class World {

    private ObservableList<Entity> Nodes;
    private ObservableList<Entity> Virus;

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
            this.Virus.get(i).setLocation(this.Virus.get(i).getLocation().getX()+1, this.Virus.get(i).getLocation().getY()+1);
        }
    }




}
