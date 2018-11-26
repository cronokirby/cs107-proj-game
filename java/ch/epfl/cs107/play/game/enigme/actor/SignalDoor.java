package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents a door connected to a signal. When that
 * signal is on, the door is open, otherwise it's closed.
 */
public class SignalDoor extends Door {
    /// When this signal is on, the door is open
    private Logic signal;
    /// The sprite to display when the door is open
    private Sprite openSprite;
    /// The sprite to display when the door is closed
    private Sprite closedSprite;


    public SignalDoor(Logic signal, Area area, String destinationArea,
                DiscreteCoordinates destination, DiscreteCoordinates mainPosition,
                DiscreteCoordinates ...otherPositions) {
        super(area, destinationArea, destination, mainPosition, otherPositions);
        this.signal = signal;
        openSprite = new Sprite("door.open.1", 1.f, 1.f, this);
        closedSprite = new Sprite("door.close.1", 1.f, 1.f, this);
    }

    @Override
    public void draw(Canvas canvas) {
        if (signal.isOn()) {
            openSprite.draw(canvas);
        } else {
            closedSprite.draw(canvas);
        }
    }

    @Override
    public boolean takeCellSpace() {
        return !signal.isOn();
    }
}
