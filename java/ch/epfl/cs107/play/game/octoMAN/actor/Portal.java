package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * A contract for portals to another area
 */
public interface Portal {
    /**
     * Return the name of the area this leads to
     */
    String getDestinationArea();

    /**
     * Return the position this portal leads to
     */
    DiscreteCoordinates getDestinationPosition();
}
