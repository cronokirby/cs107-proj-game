package ch.epfl.cs107.play.game.enigme.actor;

/**
 * Represents some kind of entity that can be toggled.
 * This encapsulates the common behavior to pressure plates,
 * switches, levers, etc
 *
 * The only contract is that this entity can be toggled.
 * It might automatically switch itself back off, or it might
 * stay on once toggled, or whatever it wants.
 */
public interface Toggleable {
    /**
     * Make this entity toggle
     */
    void toggle();
}
