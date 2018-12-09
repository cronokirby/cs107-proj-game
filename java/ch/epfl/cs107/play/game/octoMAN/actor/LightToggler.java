package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents an entity that can make an area dark
 */
public class LightToggler extends AreaEntity {
    /// Whether or not this tile makes the room dark or light
    private boolean dark;

    /**
     * Construct a new toggler
     * @param dark if true, this tile should make things dark
     */
    public LightToggler(boolean dark, Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        area.registerActor(this);
        this.dark = dark;
    }

    public boolean isDark() {
        return dark;
    }

    @Override
    public void draw(Canvas canvas) {
        // nothing to draw here
    }

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

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((OctoInteractionVisitor)v).interactWith(this);
    }
}
