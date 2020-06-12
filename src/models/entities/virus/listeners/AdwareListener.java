package models.entities.virus.listeners;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import models.entities.virus.Virus;

public class AdwareListener implements MapChangeListener<String, Virus> {

    private ObservableMap<String, Virus> boxes;
    private Virus virus;

    public AdwareListener(ObservableMap<String, Virus> boxes, Virus virus) {
        this.boxes = boxes;
        this.virus = virus;
    }

    @Override
    public void onChanged(Change<? extends String, ? extends Virus> change) {
        if (change.wasAdded()) {
            boxes.put(change.getKey(), virus);
        }
        if (change.wasRemoved()) {
            boxes.remove(change.getKey());
        }
    }
}
