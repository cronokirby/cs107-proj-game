package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Represents a class of mobs you can talk to,
 * but that will never move from their initial position.
 * This is useful when the mobs wandering around could mess up the puzzle.
 */
public class StaticMob extends TalkingMob {

    public StaticMob(String text, String sheetName, Area area, Orientation orientation, DiscreteCoordinates position) {
        super(text, sheetName, area, orientation, position);
    }

    @Override
    protected boolean canMove() {
        return false;
    }
}
