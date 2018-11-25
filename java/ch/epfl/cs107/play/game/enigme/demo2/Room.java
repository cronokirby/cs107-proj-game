package ch.epfl.cs107.play.game.enigme.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * Represents a Room in the 2nd demo game
 */
public class Room extends Area {
    private String title;
    /// This is a dirty hack to be able to let the Player
    /// check if a certain coordinate is a door.
    private Demo2Behavior behavior;

    /**
     * Construct a new room with a given title
     */
    public Room(String title) {
        this.title = title;
    }

    public boolean isDoor(DiscreteCoordinates position) {
        return behavior.isDoor(position);
    }

    @Override
    public float getCameraScaleFactor() {
        return 22.0f;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        behavior = new Demo2Behavior(window, getTitle());
        setBehavior(behavior);
        registerActor(new Background(this));
        return superOK;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
