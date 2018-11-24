package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;


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

    @Override
    protected void setCurrentPosition(Vector v) {
        Vector rounded = v.round();
        int x = (int)rounded.x;
        int y = (int)rounded.y;
        currentMainCellCoordinates = new DiscreteCoordinates(x, y);
    }
}
