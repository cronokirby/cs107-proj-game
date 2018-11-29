package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.Boulder;
import ch.epfl.cs107.play.game.octoMAN.actor.Potion;
import ch.epfl.cs107.play.game.octoMAN.actor.TalkingMob;
import ch.epfl.cs107.play.game.octoMAN.actor.SpriteGiver;
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
        return superOK;
    }
}
