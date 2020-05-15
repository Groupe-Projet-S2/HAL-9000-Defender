package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class World {

    private ObservableList<Node> Nodes;
    private ObservableList<Virus> Virus;

    public World() {
        this.Nodes = FXCollections.observableArrayList();
        this.Virus = FXCollections.observableArrayList();
    }

    public void addToList(Entity e) {
        if(e instanceof Virus)
            this.Virus.add((Virus) e);
        if (e instanceof Node)
            this.Nodes.add((Node) e);
    }

    public ObservableList<Node> getNodeList(){
        return this.Nodes;
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
            this.Virus.get(i).setLocation(this.Virus.get(i).getLocation().getX()+1, this.Virus.get(i).getLocation().getY()+1);
        }
    }




}
