package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * A wrapper over the Enigme pressure switch to work in Octo
 * This switch makes the player reset when it gets walked over twice
 */
public class ResetSwitch extends ch.epfl.cs107.play.game.enigme.actor.PressureSwitch implements Toggleable, Portal {
    /// The coordinates this switch will reset the player to
    private DiscreteCoordinates destinationPosition;
    /// Whether or not we've been stepped on before
    private boolean hasBeenOn;

    public ResetSwitch(Area area, DiscreteCoordinates destination, DiscreteCoordinates position) {
        super("neon.on", "neon.off", area, position);
        destinationPosition = destination;
    }


    @Override
    public String getDestinationArea() {
        return getOwnerArea().getTitle();
    }

    @Override
    public DiscreteCoordinates getDestinationPosition() {
        return destinationPosition;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((OctoInteractionVisitor)v).interactWith((Toggleable)this);
        if (hasBeenOn && !isOn()) {
            ((OctoInteractionVisitor)v).interactWith((Portal)this);
        }
        if (isOn()) {
            hasBeenOn = true;
        }
    }
}
