package models.entities.tower;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import models.entities.Entity;
import models.environment.Location;
import models.environment.World;

public class CPU extends Tower {

    private IntegerProperty overHeatedRate;

    public CPU(Location pos, World env) {
        super(25,pos,env);
        this.overHeatedRate = new SimpleIntegerProperty(0);
    }

    public int getOverHeatedRate() {
        return overHeatedRate.getValue();
    }

    public void setOverHeatedRate(int rate) {
        this.overHeatedRate.setValue(rate);
    }

    public IntegerProperty getOverHeatedRateProperty() {
        return overHeatedRate;
    }

    @Override
    public void act() {
    }
}