package models;

public class Entity {

    private int range;
    private Location location;
    private Location target;
    private String id;
    public static int count = 0;

    public Entity(int range, Location location) {
        this.range=range;
        this.location=location;
        this.id="E"+count;
        count++;
    }

    public Location getLocation() {
        return this.location;
    }
    void setLocation(int x, int y) {
        this.location.setCol(x);
        this.location.setRow(y);
    }

    public static boolean isVirus(Entity ent){
        return (ent instanceof Virus);
    }

    public static boolean isNode(Entity ent){
        return (ent instanceof Node);
    }

    /*public static boolean isProjectile(Entity ent){
        return (ent instanceof Projectile);
    }*/

    public String getId(){
        return this.id;
    }

    public int getRange(){
        return this.range;
    }

}
