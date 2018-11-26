package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
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
        new Key(this, new DiscreteCoordinates(1, 3));
        new Torch(this, new DiscreteCoordinates(7, 5));
        new PressurePlate(0.5f, this, new DiscreteCoordinates(9, 8));
        int[] switchXs = {4, 5, 6, 5, 4, 5, 6};
        int[] switchYs = {4, 4, 4, 5, 6, 6, 6};
        for (int i = 0; i < switchXs.length; ++i) {
            int x = switchXs[i];
            int y = switchYs[i];
            new PressureSwitch(this, new DiscreteCoordinates(x, y));
        }
        int[] leverXs = {10, 9, 8};
        for (int x : leverXs) {
            new Lever(this, new DiscreteCoordinates(x, 5));
        }
        return superOK;
    }
}
