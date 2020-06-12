package models.entities.virus.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import models.entities.virus.Ransomware;
import models.entities.virus.Virus;

public class RansomwareListener implements ChangeListener {

    private ObservableMap<String, Virus> boxes;
    private boolean hasRansom;
    private Virus virus;

    public RansomwareListener(ObservableMap<String, Virus> boxes, boolean hasRansom, Virus virus) {
        this.boxes = boxes;
        this.hasRansom = hasRansom;
        this.virus = virus;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        if ((Boolean) newValue && !hasRansom) {
            boxes.put(virus.getId(), virus);
            hasRansom = true;
        }
        else if (!(Boolean) newValue) {
            boxes.remove(virus.getId());
            hasRansom = false;
            virus.getWorld().getVirusList().removeIf(v -> v instanceof Ransomware);
        }
    }
}
