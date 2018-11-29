package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.octoMAN.OctoBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

/**
 * The super class of all areas in the Octo game
 */
public abstract class OctoArea extends Area {
    @Override
    public float getCameraScaleFactor() {
        return 22f;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        OctoBehavior behavior = new OctoBehavior(window, getTitle());
        setBehavior(behavior);
        registerActor(new Background(this));
        return superOK;
    }
}
