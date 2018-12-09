package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents a weight that can be picked up into an inventory,
 * and placed on pedestals
 */
public class Weight implements Graphics {
    /// The type of weight of this class
    private String type;
    /// The sprite associated with this weight
    /// Note that this isn't attached to a parent
    private Sprite sprite;
    /// How heavy this weight is
    private int weight;

    public Weight(String type, int weight) {
        this.type = type;
        this.weight = weight;
    }

    public Weight(Weight other) {
        type = other.type;
        weight = other.weight;
    }

    /**
     * Get the type of this object
     */
    public String getType() {
        return type;
    }

    /**
     * Return true if this weight is the same type as another
     */
    public boolean sameType(Weight that) {
        return this.type.equals(that.type);
    }

    /**
     * Get the weight of this object
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Attach the sprite associated with this weight to a parent.
     * This should be done at least once before this sprite can be drawn
     */
    public void attachSprite(Positionable parent) {
        sprite = new Sprite(type, 1.f, 1.f, parent);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }
}
