package ch.epfl.cs107.play.game.octoMAN.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.OctoBehavior;
import ch.epfl.cs107.play.game.octoMAN.actor.Boulder;
import ch.epfl.cs107.play.game.octoMAN.actor.Player;
import ch.epfl.cs107.play.game.octoMAN.actor.SpriteGiver;
import ch.epfl.cs107.play.game.octoMAN.actor.Talkable;

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
