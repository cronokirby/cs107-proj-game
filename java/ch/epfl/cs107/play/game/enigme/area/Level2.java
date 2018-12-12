package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * Represents the second of level of the enigme game
 */
public class Level2 extends EnigmeArea {
    /**
     * Construct a new second level
     */
    public Level2() {
        super("Level2");

    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        DiscreteCoordinates doorDest = new DiscreteCoordinates(2, 6);
        DiscreteCoordinates doorStart = new DiscreteCoordinates(5, 0);
        Door door = new Door(this, "LevelSelector", doorDest, doorStart);
        Apple apple = new Apple(this, new DiscreteCoordinates(5, 6));
        return superOK;
    }
}
