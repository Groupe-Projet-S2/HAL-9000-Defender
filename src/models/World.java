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
        for(int i = this.Nodes.size()-1; i>=0; i--){  // Browse the node list
            for (int j = this.Virus.size()-1; j>=0; i--){  // Browse the virus list

                // Virus j is in range of node i and not yet in its range list so we add it to the list
                if (this.Nodes.get(i).isInRange(this.Virus.get(j)) && !this.Nodes.get(i).getInRangeVirus().contains(this.Virus.get(j)))
                    this.Nodes.get(i).addRangedVirus(this.Virus.get(j));

                // Virus j is not in range of node i and is in its range list so we remove it from the list
                else if (!this.Nodes.get(i).isInRange(this.Virus.get(j)) && this.Nodes.get(i).getInRangeVirus().contains(this.Virus.get(j))) {
                    this.Nodes.get(i).delRangedVirus(this.Virus.get(j));
                }

                // Virus j is in range of node i and is in its range list but its dead so we remove it from both range list and virus list
                else if (this.Nodes.get(i).isInRange(this.Virus.get(j)) && this.Nodes.get(i).getInRangeVirus().contains(this.Virus.get(j)) && !this.Virus.get(j).isAlive()){
                    this.Nodes.get(i).delRangedVirus(this.Virus.get(i));
                    this.Virus.remove(i);
                }
            }

            // Setting node target
            if(!this.Nodes.get(i).getInRangeVirus().isEmpty())
                this.Nodes.get(i).setTarget(this.Nodes.get(i).getInRangeVirus().get(0));
            else
                this.Nodes.get(i).setTarget(null);
        }
    }




}
