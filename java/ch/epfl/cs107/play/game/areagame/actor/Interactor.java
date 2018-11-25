package ch.epfl.cs107.play.game.areagame.actor;


import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.List;

/**
 * Models objects asking for interaction (i.e. can interact with some Interactable)
 * @see Interactable
 * This interface makes sense only in the "Area Context" with Actor contained into Area Cell
 */
public interface Interactor {

    /**
     * @return the current cells this entity is on
     */
    List<DiscreteCoordinates> getCurrentCells();

    /**
     * @return the cells this entity can see
     */
    List<DiscreteCoordinates> getFieldOfViewCells();

    /**
     * @return true if this entity wants direct interaction
     */
    boolean wantsCellInteraction();

    /**
     * @return true if this entity wants indirect interaction
     */
    boolean wantsViewInteraction();

    /**
     * Have this entity interact with another
     */
    void interactWith(Interactable other);
}
