package ch.epfl.cs107.play.game.areagame.actor;


import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.List;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with them)
 * @see Interactor
 * This interface makes sense only in the "AreaGame" context with Actor contained into Area Cell
 */
public interface Interactable {
    /**
     * Get the area of influence of an Interactable
     * @return a list of coordinates the Interactable is in
     */
    List<DiscreteCoordinates> getCurrentCells();

    /**
     * @return whether this interactable is blocking the cells it is on
     */
    boolean takeCellSpace();

    /**
     * @return true if this can be interacted with from a distance
     */
    boolean isViewInteractable();

    /**
     * @return true if this can be interacted with directly
     */
    boolean isCellInteractable();

    /**
     * Accept an interaction with a visitor
     */
    void acceptInteraction(AreaInteractionVisitor v);
}
