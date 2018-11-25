package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

/**
 * Represents the different areas in the Enigme game.
 * Instead of subclassing just to change the title of the area,
 * I've chosen to make it an attribute of the class instead.
 */
public class EnigmeArea extends Area {
    /// The title associated with this area
    private String title;

    /**
     * Initialiase an EnigmeArea with a certain title.
     * Note that the title also determines the background
     * image for this area implicitly.
     * @param title the title this area should have
     */
    public EnigmeArea(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public float getCameraScaleFactor() {
        return 22.0f;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        EnigmeBehavior behavior = new EnigmeBehavior(window, getTitle());
        setBehavior(behavior);
        registerActor(new Background(this));
        return superOK;
    }
}
