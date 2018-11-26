package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Represents a switch that gets activated when stepped on
 */
public class PressureSwitch extends SwitchActor {
    /// The sprite for when this has not been switched
    private Sprite unSwitchedSprite;
    /// The sprite for when this has been switched
    private Sprite switchedSprite;

    /**
     * Create a new pressure switch in an area at a certain position,
     * automatically registering it.
     */
    public PressureSwitch(Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        unSwitchedSprite = new Sprite("GroundLightOff", 1.f, 1.f, this);
        switchedSprite = new Sprite("GroundLightOn", 1.f, 1.f, this);
    }

    @Override
    protected Sprite getOffSprite() {
        return unSwitchedSprite;
    }

    @Override
    protected Sprite getOnSprite() {
        return switchedSprite;
    }

    /// PressureSwitch implements Interactable

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }
}
