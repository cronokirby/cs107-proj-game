package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

import java.util.LinkedList;
import java.util.List;


/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {
    /// Whether or not this entity is moving
    private boolean isMoving;
    /// How many frames the current move is supposed to take
    private int framesForCurrentMove;
    /// The target cell for this movement
    private DiscreteCoordinates targetMainCellCoordinates;

    /**
     * Default MovableAreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     */
    public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        resetMotion();
    }

    /**
     * Initialize or reset the current motion information
     */
    protected void resetMotion(){
        framesForCurrentMove = 0;
        isMoving = false;
        targetMainCellCoordinates = getCurrentMainCellCoordinates();
        setCurrentPosition(getCurrentMainCellCoordinates().toVector());
    }

    /**
     * Check if we're less than a frame away from the target.
     * This is necessary because just checking our rounded position
     * will cause us to jump to the target.
     *
     * Only call this when moving!
     * @return whether the target coordinates are close to our position
     */
    private boolean targetClose() {
        Vector diff = getPosition().sub(targetMainCellCoordinates.toVector());
        return diff.getLength() < (1.0f / framesForCurrentMove);
    }

    /**
     * 
     * @param framesForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */
    protected boolean move(int framesForMove){
        if (!isMoving || targetClose()) {
            Area owner = getOwnerArea();
            if (owner.transitionAreaCells(this, getLeavingCells(), getEnteringCells())) {
                framesForCurrentMove = framesForMove;
                isMoving = true;
                Vector orientation = getOrientation().toVector();
                targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
                return true;
            }
        }
        return false;
    }

    /**
     * Get the list of cells this entity will be leaving upon moving
     * @return the corresponding list of coordinates
     */
    protected final List<DiscreteCoordinates> getLeavingCells() {
        return getCurrentCells();
    }

    /**
     * Get the list of cells this entity will be entering.
     * Calculates the list by looking at each of the current cells,
     * and looking at where they'll move to.
     * @return a list of coordinates for each cell that will be reached.
     */
    protected final List<DiscreteCoordinates> getEnteringCells() {
        List<DiscreteCoordinates> ret = new LinkedList<>();
        Area owner = getOwnerArea();
        for (DiscreteCoordinates coords : getCurrentCells()) {
            DiscreteCoordinates nextCoords = coords.jump(getOrientation().toVector());
            if (owner.contains(nextCoords)) {
                ret.add(nextCoords);
            }
        }
        return ret;
    }

    /// MovableAreaEntity extends AreaEntity

    @Override
    public void setOrientation(Orientation orientation) {
        if (!isMoving) {
            super.setOrientation(orientation);
        }
    }

    /// MovableAreaEntity implements Actor

    @Override
    public void update(float deltaTime) {
        if (isMoving) {
            if (targetClose()) {
                resetMotion();
            } else {
                Vector distance = getOrientation().toVector();
                distance = distance.mul(1.0f / framesForCurrentMove);
                setCurrentPosition(getPosition().add(distance));
            }
        }
    }

    /// Implements Positionable

    @Override
    public Vector getVelocity() {
        // the velocity must be computed as the orientation vector (getOrientation().toVector() mutiplied by
    	// framesForCurrentMove
        return getOrientation().toVector().mul(framesForCurrentMove);
    }
}
