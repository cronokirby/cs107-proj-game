package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * Represents the level selectors in an Enigme game
 */
public class LevelSelector extends EnigmeArea {
    /**
     * Create a new Level Selector
     */
    public LevelSelector() {
        super("LevelSelector");
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        for (int i = 1; i <= 8; ++i) {
            String doorTitle = "LevelSelector";
            DiscreteCoordinates doorDestination = new DiscreteCoordinates(5, 5);
            DiscreteCoordinates doorLocation = new DiscreteCoordinates(i, 7);
            if (i == 1) {
                doorTitle = "Level1";
                doorDestination = new DiscreteCoordinates(5, 1);
            }
            if (i == 2) {
                doorTitle = "Level2";
                doorDestination = new DiscreteCoordinates(5, 1);
            }
            Door door = new Door(this, doorTitle, doorDestination, doorLocation);
        }
        return superOK;
    }
}
