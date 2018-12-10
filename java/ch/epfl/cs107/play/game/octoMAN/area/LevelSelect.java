package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.LightToggler;
import ch.epfl.cs107.play.game.octoMAN.actor.StandardDoor;
import ch.epfl.cs107.play.game.octoMAN.actor.TalkingMob;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.XMLTexts;
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
        int[] xs = { 2, 5, 8, 11, 15, 18, 21, 24 };
        for (int i = 0; i < destinations.length; ++i) {
            DiscreteCoordinates position = new DiscreteCoordinates(xs[i], 9);
            DiscreteCoordinates destination = destinationPositions[i];
            Logic open = destination == null ? Logic.FALSE : Logic.TRUE;
            new StandardDoor(open, destinations[i], destination, this, position);
        }
        new StandardDoor(
                Logic.TRUE, "Final", new DiscreteCoordinates(2, 1),
                this, new DiscreteCoordinates(13, 19)
        );
        // remove the light when we exit the electricity area
        new LightToggler(false, this, new DiscreteCoordinates(15, 8));
        new TalkingMob(
                XMLTexts.getText("levelselect"), "mob.1", this,
                Orientation.DOWN, new DiscreteCoordinates(13, 6)
        );
        return superOK;
    }
}
