package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Represents a bird that flies around and annnoys you
 */
public class Bird extends TalkingMob {
    /**
     * Construct a new bird in a certain area and with a certain sprite
     * @param sheetName the name of the sprite sheet to use
     */
    public Bird(String sheetName, Area area, DiscreteCoordinates position) {
        super("KWAAAAA", sheetName, area, Orientation.DOWN, position);
    }

    @Override
    protected int getRarity() {
        return 6;
    }

    @Override
    protected int framesPerMove() {
        return 8;
    }
}