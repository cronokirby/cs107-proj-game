package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.octoMAN.OctoBehavior;
import ch.epfl.cs107.play.game.octoMAN.actor.Animation;
import ch.epfl.cs107.play.game.octoMAN.actor.Boulder;
import ch.epfl.cs107.play.game.octoMAN.actor.SpriteGiver;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class TestArea extends Area {
    @Override
    public String getTitle() {
        return "OctoTest";
    }

    @Override
    public float getCameraScaleFactor() {
        return 22.f;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        OctoBehavior behavior = new OctoBehavior(window, getTitle());
        setBehavior(behavior);
        registerActor(new Background(this));
        new Boulder(this, new DiscreteCoordinates(6, 10));
        new SpriteGiver("girl.1", this, new DiscreteCoordinates(4, 8));
        new SpriteGiver("girl.2", this, new DiscreteCoordinates(6, 8));
        new SpriteGiver("boy.1", this, new DiscreteCoordinates(8, 8));
        new SpriteGiver("boy.2", this, new DiscreteCoordinates(10, 8));
        new SpriteGiver("old.man.1", this, new DiscreteCoordinates(12, 8));
        return superOK;
    }
}
