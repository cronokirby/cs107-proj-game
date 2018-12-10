package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.Orb;
import ch.epfl.cs107.play.game.octoMAN.actor.SpriteGiver;
import ch.epfl.cs107.play.game.octoMAN.actor.StandardDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class CharacterSelect extends OctoArea {
    @Override
    public String getTitle() {
        return "CharacterSelect";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        new SpriteGiver("boy.1", this, new DiscreteCoordinates(1, 2));
        new SpriteGiver("boy.2", this, new DiscreteCoordinates(1, 4));
        new SpriteGiver("boy.4", this, new DiscreteCoordinates(3, 2));
        new SpriteGiver("girl.1", this, new DiscreteCoordinates(3, 4));
        new SpriteGiver("girl.2", this, new DiscreteCoordinates(9,2));
        new SpriteGiver("girl.3", this, new DiscreteCoordinates(9, 4));
        new SpriteGiver("girl.4", this, new DiscreteCoordinates(7, 4));
        new SpriteGiver("girl.5", this, new DiscreteCoordinates(7, 2));

        new StandardDoor(Logic.TRUE, "LevelSelect", new DiscreteCoordinates(13,4),
                    this, new DiscreteCoordinates(5, 6));
        return superOK;
    }
}
