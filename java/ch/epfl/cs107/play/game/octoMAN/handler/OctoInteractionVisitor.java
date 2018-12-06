package ch.epfl.cs107.play.game.octoMAN.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.OctoBehavior;
import ch.epfl.cs107.play.game.octoMAN.actor.*;

public interface OctoInteractionVisitor extends AreaInteractionVisitor {
    /**
     * Simulate an interaction with a boulder
     */
    default void interactWith(Boulder boulder) {
    }

    /**
     * Simulate an interaction with a sprite giver
     */
    default void interactWith(SpriteGiver giver) {
    }

    /**
     * Simulate an interaction with a talkable entity
     */
    default void interactWith(Talkable entity) {
    }

    /**
     * Simulate an interaction with a potion
     */
    default void interactWith(Potion potion) {
    }

    /**
     * Simulate an interaction with a wire
     */
    default void interactWith(Wire wire) {
    }

    /**
     * Simulate an interaction with a WiredLever
     */
    default void interactWith(WiredLever lever) {
    }

    /**
     * Simulate an interaction with a portal
     */
    default void interactWith(Portal portal) {
    }

    /**
     * Simulate an interaction with an orb
     */
    default void interactWith(Orb orb) {
    }

    /**
     * Simulate an interaction with a cell
     */
    default void interactWith(OctoBehavior.OctoCell cell) {
    }

    /**
     * Simulate an interaction with a player
     */
    default void interactWith(Player player) {

    }
}
