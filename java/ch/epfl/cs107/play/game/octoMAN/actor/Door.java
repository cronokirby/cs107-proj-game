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

public abstract class Door extends AreaEntity implements Portal {
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
     * @param area the area this door is in
     * @param position the position this door is in
     */
    public Door(Logic signal,
                String destinationArea, DiscreteCoordinates destinationPosition,
                Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        area.registerActor(this);
        this.signal = signal;
        this.destinationArea = destinationArea;
        this.destinationPosition = destinationPosition;
    }

    protected abstract Sprite getClosedSprite();

    protected abstract Sprite getOpenSprite();

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
            getOpenSprite().draw(canvas);
        } else {
            getClosedSprite().draw(canvas);
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
        ((OctoInteractionVisitor)v).interactWith((Portal)this);
    }
}
