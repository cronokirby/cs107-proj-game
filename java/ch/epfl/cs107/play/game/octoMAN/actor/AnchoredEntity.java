package ch.epfl.cs107.play.game.octoMAN.actor;


import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.math.Vector;

/**
 * Represents a class that is anchored to another entity,
 * e.g. a player, and thus displays at a fixed point relative
 * to that actor
 */
public abstract class AnchoredEntity extends Entity {
    /// The offset of this entity from the anchor
    private Vector offset;
    /// The entity this is anchored to
    private Actor anchor;

    /**
     * Construct a new anchored entity with an initial offset
     */
    public AnchoredEntity(Vector position) {
        super(position);
        offset = position;
    }

    /**
     * Set the anchor for this entity
     */
    public void setAnchor(Actor anchor) {
        this.anchor = anchor;
    }

    @Override
    public void update(float deltaTime) {
        setCurrentPosition(anchor.getPosition().add(offset));

    }
}
