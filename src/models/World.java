package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class World {

    private ObservableList<Entity> entities;

    public World() {
        this.entities = FXCollections.observableArrayList();

    }

    public void addToList(Entity e) {
        this.entities.add(e);
    }

    public ObservableList<Node> getNodeList(){
        ObservableList<Node> nodeList = FXCollections.observableArrayList();
        for (Entity e:entities){
            if (Entity.isNode(e))
                nodeList.add((Node)e);
        }
        return nodeList;
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

    public Node getNode(String id){
        for (Node node:getNodeList()){
            if (node.getId().equals(id))
                return node;
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
