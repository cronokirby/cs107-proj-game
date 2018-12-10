package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * A wrapper over the Enigme pressure switch to work in Octo
 */
public class PressureSwitch extends ch.epfl.cs107.play.game.enigme.actor.PressureSwitch implements Toggleable {
    public PressureSwitch(Area area, DiscreteCoordinates position) {
        super("neon.on", "neon.off", area, position);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((OctoInteractionVisitor)v).interactWith((Toggleable)this);
    }
}
