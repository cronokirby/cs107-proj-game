package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.FinalAntidote;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * Represents the final area in the game
 */
public class FinalArea extends OctoArea {
    @Override
    public String getTitle() {
        return "Final";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        new FinalAntidote(this, new DiscreteCoordinates(2, 70));
        return superOK;
    }
}
