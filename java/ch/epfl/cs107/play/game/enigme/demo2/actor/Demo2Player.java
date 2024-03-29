package ch.epfl.cs107.play.game.enigme.demo2.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.demo2.Room;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

/**
 * Represents the main player of the demo2 game
 */
public class Demo2Player extends MovableAreaEntity {
    /// Whether or not we're going through a door
    private boolean inDoor;
    /// The sprite for this player
    private Sprite sprite;
    /// The keyboard the player needs to listen to controls from
    private Keyboard controller;
    /// The amount of frames per move
    private final int FRAMES_PER_MOVE = 8;

    public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        this.sprite = new Sprite("ghost.1", 1, 1.f, this);
    }

    private Button getKey(int code) {
        return getOwnerArea().getKeyboard().get(code);
    }

    /**
     * Enter this player into a new area.
     * @param area the are to enter this player into
     * @param position the position at which to put the player there
     */
    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }

    /**
     * Make the player leave the area they are in
     */
    public void leaveCurrentArea() {
        getOwnerArea().unregisterActor(this);
    }


    /**
     * @return true if the player is in a door way
     */
    public boolean isInDoor() {
        return inDoor;
    }

    /**
     * Toggle whether or not the player is in a doorway
     * @param inDoor true if the player is in a doorway
     */
    public void toggleDoor(boolean inDoor) {
        this.inDoor = inDoor;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Orientation orientation = null;
        if (getKey(Keyboard.LEFT).isDown()) {
            orientation = Orientation.LEFT;
        } else if (getKey(Keyboard.UP).isDown()) {
            orientation = Orientation.UP;
        } else if (getKey(Keyboard.RIGHT).isDown()) {
            orientation = Orientation.RIGHT;
        } else if (getKey(Keyboard.DOWN).isDown()) {
            orientation = Orientation.DOWN;
        }
        if (orientation != null) {
            if (getOrientation().equals(orientation)) {
                move(FRAMES_PER_MOVE);
            } else {
                setOrientation(orientation);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    /// Demo2Player extends MovableAreaEntity

    @Override
    protected boolean move(int framesForMove) {
        boolean couldMove = super.move(framesForMove);
        boolean foundDoor = false;
        // TODO: make this logic less implementation dependent
        // We've implemented it this way to avoid polluting classes outside
        // this specific game with dirty logic
        Area area = getOwnerArea();
        if (couldMove && area instanceof Room) {
            Room room = (Room) area;
            for (DiscreteCoordinates position : getEnteringCells()) {
                if (room.isDoor(position)) {
                    foundDoor = true;
                    break;
                }
            }
        }
        inDoor = foundDoor;
        return couldMove;
    }

    /// Demo2Player implements Interactable


    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    // this is need to compile, for compatability with later parts

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {

    }
}
