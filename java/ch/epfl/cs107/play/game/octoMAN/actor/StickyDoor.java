package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

/**
 * An extension of a standard door that stays open
 * as soon as the door stays open
 */
public class StickyDoor extends StandardDoor {
    private boolean hasBeenOn;

    public StickyDoor(Logic signal, String destinationArea, DiscreteCoordinates destinationPosition,
                      Area area, DiscreteCoordinates position) {
        super(signal, destinationArea, destinationPosition, area, position);
    }

    @Override
    protected boolean isOpen() {
        if (super.isOpen()) {
            hasBeenOn = true;
        }
        return hasBeenOn;
    }
}
