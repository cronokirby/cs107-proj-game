package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * A PressurePlate compatible with octointeraction visitor
 */
public class PressurePlate extends ch.epfl.cs107.play.game.enigme.actor.PressurePlate implements Toggleable {
    public PressurePlate(float resetTime, Area area, DiscreteCoordinates position) {
        super(resetTime, area, position);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((OctoInteractionVisitor)v).interactWith((Toggleable)this);
    }
}
