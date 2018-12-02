package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class StandardDoor extends Door {
    /// The sprite to display when this door is open
    private Sprite openSprite;
    /// The sprite to display when this door is closed
    private Sprite closedSprite;

    public StandardDoor(Logic signal, String destinationArea, DiscreteCoordinates destinationPosition,
                        Area area, DiscreteCoordinates position) {
        super(signal, destinationArea, destinationPosition, area, position);
        openSprite = new Sprite("door.open.1", 1.f, 1.f, this);
        closedSprite = new Sprite("door.close.1", 1.f, 1.f, this);
    }

    @Override
    public Sprite getClosedSprite() {
        return closedSprite;
    }

    @Override
    public Sprite getOpenSprite() {
        return openSprite;
    }
}
