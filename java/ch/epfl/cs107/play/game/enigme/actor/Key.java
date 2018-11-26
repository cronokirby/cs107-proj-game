package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

/**
 * Represents a collectable key in an area.
 */
public class Key extends AreaEntity implements Logic {
    /// Whether or not this key has been collected
    private boolean collected;
    /// The sprite associated with this key
    private Sprite sprite;

    /**
     * Create a new key registered in an area at a position.
     * @param area the area to create and register the key in
     * @param position the position of this key
     */
    public Key(Area area, DiscreteCoordinates position) {
        // We just pass in a dummy orientation
        super(area, Orientation.DOWN, position);
        collected = false;
        sprite = new Sprite("key.1", 1.f, 1.f, this);
    }

    /**
     * Have this key be scooped up by someone
     */
    public void collect() {
        collected = true;
        getOwnerArea().unregisterActor(this);
    }

    @Override
    public void draw(Canvas canvas) {
        if (!collected) {
            sprite.draw(canvas);
        }
    }

    /// Key implements LogicSignal

    @Override
    public boolean isOn() {
        return collected;
    }

    /// Key implements Interactable

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return super.getCurrentCells();
    }

    @Override
    public boolean takeCellSpace() {
        return true;
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
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }
}
