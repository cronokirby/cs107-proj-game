package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.*;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
    /**
     * Simulate an interaction between Enigme Interactors and Apple
     * @param apple apple to interact with
     */
    default void interactWith(Apple apple) {
    }

    /**
     * Simulate an interaction between Enigme Interactors and Door
     * @param door the door to interact with
     */
    default void interactWith(Door door) {
    }

    /**
     * Simulate an interaction between an interactor and a key
     */
    default void interactWith(Key key) {
    }

    /**
     * Simulate an interaction between an interactor and a toggleable entity
     */
    default void interactWith(Toggleable entity) {
    }

    /**
     * Simulate an interaction between Enigme Interactors and EnigmeCell
     * @param cell the cell to interact with
     */
    default void interactWith(EnigmeBehavior.EnigmeCell cell) {
    }

    /**
     * Simulate an interaction with a player
     * @param player the player to interact with
     */
    default void interactWith(EnigmePlayer player) {
    }
}
