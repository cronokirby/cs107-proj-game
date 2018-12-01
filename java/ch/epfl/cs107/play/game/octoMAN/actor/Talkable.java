package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;

/**
 * An interface for entities that can be talked to
 */
public interface Talkable {
    /**
     * Get the message associated with this actor.
     * @param orientation the orientation of the entity this is talking to
     */
    String talk(Orientation orientation);

    /**
     * Tell this entity that you're doing talking
     */
    void doneTalking();
}
