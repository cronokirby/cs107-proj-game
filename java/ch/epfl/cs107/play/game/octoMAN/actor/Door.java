package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Door extends AreaEntity {
    /// The sprite for this door when it's open
    private Sprite openSprite;
    /// The sprite for this door when it's closed
    private Sprite closedSprite;
    /// The signal that this door connects to
    private Logic signal;
    /// The name of the area this leads to
    private String destinationArea;
    /// The coordinates this door leads to
    private DiscreteCoordinates destinationPosition;


    /**
     * Construct a new door.
     * @param signal the signal that makes this door open
     * @param destinationArea the name of the area this door leads to
     * @param destinationPosition the position this door leads to
     * @param open the sprite to display when this door is open
     * @param closed the sprite to display when this door is closed
     * @param area the area this door is in
     * @param position the position this door is in
     */
    public Door(Logic signal, Sprite open, Sprite closed,
                String destinationArea, DiscreteCoordinates destinationPosition,
                Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        this.openSprite = open;
        this.closedSprite = closed;
        this.signal = signal;
        this.destinationArea = destinationArea;
        this.destinationPosition = destinationPosition;
    }

    /**
     * Returns true when this door is open.
     */
    private boolean isOpen() {
        return signal.isOn();
    }

    /**
     * @return the name of the area this leads to
     */
    public String getDestinationArea() {
        return destinationArea;
    }

    /**
     * @return the position this door leads to
     */
    public DiscreteCoordinates getDestinationPosition() {
        return destinationPosition;
    }

    @Override
    public void draw(Canvas canvas) {
        if (isOpen()) {
            openSprite.draw(canvas);
        } else {
            closedSprite.draw(canvas);
        }
    }

    @Override
    public boolean takeCellSpace() {
        return !isOpen();
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((OctoInteractionVisitor)v).interactWith(this);
    }
}
