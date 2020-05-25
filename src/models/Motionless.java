package models;

import java.util.ArrayList;

public class Motionless extends Projectile {

    public Motionless(Location location, Node sender, int damage, int range) {
        super(location, sender, damage, range);
    }

    public boolean isInRange(Virus virus){
        int nearestX = Math.max(virus.getLocation().getCol(), Math.min(this.getLocation().getCol(),virus.getLocation().getCol()+virus.getSizeW()));
        int nearestY = Math.max(virus.getLocation().getRow(), Math.min(this.getLocation().getRow(),virus.getLocation().getRow()+virus.getSizeH()));

        int deltaX = this.getLocation().getCol() - Math.max(virus.getLocation().getCol(), Math.min(this.getLocation().getCol(),virus.getLocation().getCol()+virus.getSizeW()));
        int deltaY = this.getLocation().getRow() - Math.max(virus.getLocation().getRow(), Math.min(this.getLocation().getRow(),virus.getLocation().getRow()+virus.getSizeH()));

        return (Math.pow(deltaX,2) + Math.pow(deltaY,2)) < Math.pow(this.getRange(),2);
    }

    @Override
    public void hit(ArrayList<Virus> inRangeVirus) {
        for (int i = 0 ; i < this.getInRangeVirus().size() ; i++) {
            this.explode(this.getInRangeVirus().get(i));
        }
    }
}
