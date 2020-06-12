package views;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.media.AudioClip;
import java.util.HashMap;

public class AudioMenu {

    private final HashMap<Integer, AudioClip> clips;

    public AudioMenu(HashMap<Integer, AudioClip> clips) {
        this.clips = clips;
    }

    public MenuBar create() {
        MenuBar menu = new MenuBar();
        menu.setStyle("-fx-font-size: 10");
        Menu audio = new Menu("Audio");
        MenuItem soundOne = new MenuItem("Step into space");
        soundOne.setOnAction(e -> {
            if(clips.get(2).isPlaying())
                clips.get(2).stop();
            if (!clips.get(1).isPlaying())
                clips.get(1).play(0.1);
        });
        MenuItem soundTwo = new MenuItem("Wake up");
        soundTwo.setOnAction(e -> {
            if (clips.get(1).isPlaying())
                clips.get(1).stop();
            if (!clips.get(2).isPlaying())
                clips.get(2).play(0.1);
        });
        MenuItem mute = new MenuItem("Mute");
        mute.setOnAction(e -> clips.forEach((i, clip) -> clip.stop()));
        audio.getItems().addAll(soundOne, soundTwo, mute);
        menu.getMenus().add(audio);
        return menu;
    }
}
