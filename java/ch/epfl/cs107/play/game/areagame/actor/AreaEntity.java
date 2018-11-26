package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

import java.util.Collections;
import java.util.List;


/**
 * Actors leaving in a grid
 */
public abstract class AreaEntity extends Entity implements Interactable {
    /// The Area this entity is in
    private Area ownerArea;
    /// Orientation of this entity in the area
    private Orientation orientation;
    /// Coordinate of the main Cell linked to this entity
    private DiscreteCoordinates currentMainCellCoordinates;


    /**
     * Default AreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(position.toVector());
        ownerArea = area;
        this.orientation = orientation;
        currentMainCellCoordinates = position;
    }

    /**
     * @return the area this entity is in
     */
    protected Area getOwnerArea() {
        return ownerArea;
    }

    /**
     * Change the area this entity is in
     */
    protected void setOwnerArea(Area area) {
        ownerArea = area;
    }

    /**
     * @return the current orientation of this entity
     */
    protected Orientation getOrientation() {
        return orientation;
    }

    /**
     * Set the orientation of this entity
     * @param orientation the new orientation for this entity, not null
     */
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    /**
     * Getter for the coordinates of the main cell occupied by the AreaEntity
     * @return (DiscreteCoordinates)
     */
    protected DiscreteCoordinates getCurrentMainCellCoordinates(){
        return currentMainCellCoordinates;
    }

    // This is usually the default behavior we want for an entity.
    // Occupying more than the main cell is an exception.
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    protected void setCurrentPosition(Vector v) {
        super.setCurrentPosition(v);
        Vector rounded = v.round();
        int x = (int)rounded.x;
        int y = (int)rounded.y;
        currentMainCellCoordinates = new DiscreteCoordinates(x, y);
    }
}
