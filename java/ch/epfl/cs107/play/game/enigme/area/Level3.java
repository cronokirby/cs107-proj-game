package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Not;
import ch.epfl.cs107.play.window.Window;

/**
 * Represents the 3rd level for part6,
 * showing off various signal entities;
 */
public class Level3 extends EnigmeArea {
    /**
     * Construct a new level3
     */
    public Level3() {
        super("Level3");
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        Logic key = new Key(this, new DiscreteCoordinates(1, 3));
        new Torch(this, new DiscreteCoordinates(7, 5));
        Logic plate = new PressurePlate(0.5f, this, new DiscreteCoordinates(9, 8));
        int[] switchXs = {4, 5, 6, 5, 4, 5, 6};
        int[] switchYs = {4, 4, 4, 5, 6, 6, 6};
        Logic[] switches = new Logic[switchXs.length];
        for (int i = 0; i < switchXs.length; ++i) {
            int x = switchXs[i];
            int y = switchYs[i];
            switches[i] = new PressureSwitch(this, new DiscreteCoordinates(x, y));
        }
        Logic allSwitches = new MultipleAnd(switches);
        int[] leverXs = {10, 9, 8};
        Logic[] levers = new Logic[leverXs.length];
        levers[0] = new Lever(this, new DiscreteCoordinates(8, 5));
        levers[1] = new Not(new Lever(this, new DiscreteCoordinates(9, 5)));
        levers[2] = new Lever(this, new DiscreteCoordinates(10, 5));
        Logic rightLevers = new MultipleAnd(levers);
        new SignalDoor(key, this, "LevelSelector", new DiscreteCoordinates(3, 6),
                       new DiscreteCoordinates(5, 9));
        new SignalRock(plate, this, new DiscreteCoordinates(6, 8));
        new SignalRock(allSwitches, this, new DiscreteCoordinates(5, 8));
        new SignalRock(rightLevers, this, new DiscreteCoordinates(4, 8));
        return superOK;
    }
}
