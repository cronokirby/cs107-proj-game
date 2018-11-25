package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * Represents the first level in the Enigme game
 */
public class Level1 extends EnigmeArea {
    /**
     * Construct a new first level
     */
    public Level1() {
        super("Level1");

    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        DiscreteCoordinates dest = new DiscreteCoordinates(1, 6);
        DiscreteCoordinates start = new DiscreteCoordinates(5, 0);
        Door door = new Door(this, "LevelSelector", dest, start);
        return superOK;
    }
}
