package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class TestArea extends OctoArea {
    @Override
    public String getTitle() {
        return "OctoTest";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        new Boulder(this, new DiscreteCoordinates(6, 10));
        new SpriteGiver("girl.1", this, new DiscreteCoordinates(4, 8));
        new SpriteGiver("girl.2", this, new DiscreteCoordinates(6, 8));
        new SpriteGiver("boy.1", this, new DiscreteCoordinates(8, 8));
        new SpriteGiver("boy.2", this, new DiscreteCoordinates(10, 8));
        new TalkingMob("Je m'apelle Fürbringer, j'ammène le Für", this, Orientation.DOWN, new DiscreteCoordinates(12, 8));
        new Potion(this, new DiscreteCoordinates(10, 10));
        Wire wire1 = new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(9, 2));
        for (int y = 3; y < 11; ++y) {
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(9, y));
        }
        for (int x = 9; x < 18; ++x) {
            new DirectedWire(this, Orientation.RIGHT, new DiscreteCoordinates(x, 11));
        }
        for (int y = 11; y > 1; --y) {
            new DirectedWire(this, Orientation.DOWN, new DiscreteCoordinates(18, y));
        }
        new WiredLever(wire1, this, Orientation.UP, new DiscreteCoordinates(9, 1));
        return superOK;
    }
}
