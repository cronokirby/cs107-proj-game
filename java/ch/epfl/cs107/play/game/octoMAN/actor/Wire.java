package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

/**
 * Represents an actor that can be charged, and then carry current
 * into the cells to which it's connected.
 */
public abstract class Wire extends AreaEntity implements Interactor {
    /// Whether or not this wire is charged
    private boolean charged;
    /// The handler for interactions
    private final OctoInteractionVisitor handler = new WireHandler();

    /**
     * Check whether or not this wire is currently charged.
     * This can be overriden for custom logic
     */
    public boolean isCharged() {
        return charged;
    }

    /**
     * Charge this wire
     */
    public void charge() {
        charged = true;
    }

    /**
     * Uncharge this wire
     */
    public void unCharge() {
        charged = false;
    }

    public Wire(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        area.registerActor(this);
    }

    /**
     * Return the sprite associated with this wire when on
     */
    protected abstract Sprite getChargedSprite();

    /**
     * Return the sprite associated with this wire when off
     */
    protected abstract Sprite getUnChargedSprite();

    /**
     * Whether or not this wire will hold a charge
     */
    protected abstract boolean holdsCharge();

    /**
     * Return a list of cells this wire is connecting to
     */
    protected abstract List<DiscreteCoordinates> connectedCells();

    @Override
    public void draw(Canvas canvas) {
        if (isCharged()) {
            getChargedSprite().draw(canvas);
        } else {
            getUnChargedSprite().draw(canvas);
        }
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((OctoInteractionVisitor)v).interactWith(this);
    }

    public class WireHandler implements OctoInteractionVisitor {
        @Override
        public void interactWith(Wire wire) {
            if (isCharged()) {
                wire.charge();
            } else {
                wire.unCharge();
            }
        }
    }

    @Override
    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return connectedCells();
    }

    @Override
    public boolean wantsCellInteraction() {
        return false;
    }

    @Override
    public boolean wantsViewInteraction() {
        return true;
    }
}
