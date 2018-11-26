package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Door extends AreaEntity {
    /// The title of the area this door connects to
    private String destinationArea;
    /// The coordinates where this door leads to
    private DiscreteCoordinates destination;
    /// The list of cells this door occupies
    private List<DiscreteCoordinates> currentCells;

    public Door(Area area, String destinationArea,
                DiscreteCoordinates destination, DiscreteCoordinates mainPosition,
                DiscreteCoordinates ...otherPositions) {
        // We just pass a dummy orientation
        super(area, Orientation.DOWN, mainPosition);
        area.registerActor(this);
        this.destinationArea = destinationArea;
        this.destination = destination;
        this.currentCells = Collections.singletonList(mainPosition);
        for (DiscreteCoordinates position : otherPositions) {
            this.currentCells.add(position);
        }
    }

    /**
     * @return the name of the area this door points to
     */
    public String getDestinationArea() {
        return destinationArea;
    }

    /**
     * @return the coordinates of the door's destination
     */
    public DiscreteCoordinates getDestinationPosition() {
        return destination;
    }

    @Override
    public void draw(Canvas canvas) {
        // The background holds the door sprite
    }

    /// Door implements interactable

    @Override
    public boolean takeCellSpace() {
        return false;
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
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }
}
