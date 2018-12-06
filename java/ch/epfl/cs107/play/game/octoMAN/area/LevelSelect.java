package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.StandardDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class LevelSelect extends OctoArea {
    @Override
    public String getTitle() {
        return "LevelSelect";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        String[] destinations = {"Physics1", "Environment", "", "LifeSciences1", "", "", "", ""};
        DiscreteCoordinates[] destinationPositions = {
                new DiscreteCoordinates(4, 1), new DiscreteCoordinates(5, 2),
                null, new DiscreteCoordinates(4, 1),
                null, null, null, null
        };
        int x = 2;
        for (int i = 0; i < destinations.length; ++i) {
            DiscreteCoordinates position = new DiscreteCoordinates(x, 9);
            DiscreteCoordinates destination = destinationPositions[i];
            Logic open = destination == null ? Logic.FALSE : Logic.TRUE;
            new StandardDoor(open, destinations[i], destination, this, position);
            x += 3;
        }
        return superOK;
    }
}
