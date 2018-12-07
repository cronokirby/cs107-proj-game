package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;

/**
 * Represents a weight that can be picked up into an inventory,
 * and placed on pedestals
 */
public class Weight {
    /// The type of weight of this class
    private String type;
    /// How heavy this weight is
    private int weight;

    public Weight(String type, int weight) {
        this.type = type;
        this.weight = weight;
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
}
