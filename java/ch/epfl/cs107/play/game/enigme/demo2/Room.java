package ch.epfl.cs107.play.game.enigme.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

/**
 * Represents a Room in the 2nd demo game
 */
public class Room extends Area {
    private String title;

    /**
     * Construct a new room with a given title
     */
    public Room(String title) {
        this.title = title;
    }

    @Override
    public float getCameraScaleFactor() {
        return 24.0f;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        setBehavior(new Demo2Behavior(window, getTitle()));
        registerActor(new Background(this));
        return superOK;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
