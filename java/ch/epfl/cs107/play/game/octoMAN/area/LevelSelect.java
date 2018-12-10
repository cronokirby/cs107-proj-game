package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.LightToggler;
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
        String[] destinations = {
                "Physics1", "Environment", "Mathematics1", "LifeSciences1",
                "Electricity1", "Chemistry1", "Microtech1", ""
        };
        DiscreteCoordinates[] destinationPositions = {
                new DiscreteCoordinates(4, 1), new DiscreteCoordinates(5, 2),
                new DiscreteCoordinates(5, 1), new DiscreteCoordinates(4, 1),
                new DiscreteCoordinates(2, 1), new DiscreteCoordinates(3, 1),
                new DiscreteCoordinates(4, 1), null
        };
        int x = 2;
        for (int i = 0; i < destinations.length; ++i) {
            DiscreteCoordinates position = new DiscreteCoordinates(x, 9);
            DiscreteCoordinates destination = destinationPositions[i];
            Logic open = destination == null ? Logic.FALSE : Logic.TRUE;
            new StandardDoor(open, destinations[i], destination, this, position);
            x += 3;
        }
        // remove the light when we exit the electricity area
        new LightToggler(false, this, new DiscreteCoordinates(14, 8));
        return superOK;
    }
}
