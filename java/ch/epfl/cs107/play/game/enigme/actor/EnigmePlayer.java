package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.demo2.Room;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

/**
 * Represents the main player of the demo2 game
 */
public class EnigmePlayer extends MovableAreaEntity {
    /// Whether or not we're going through a door
    private boolean inDoor;
    /// The sprite for this player
    private Sprite sprite;
    /// The keyboard the player needs to listen to controls from
    private Keyboard controller;
    /// The amount of frames per move
    private final int FRAMES_PER_MOVE = 8;

    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position, Keyboard controller) {
        super(area, orientation, position);
        this.sprite = new Sprite("ghost.1", 1, 1.f, this);
        this.controller = controller;
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
        if (controller.get(Keyboard.LEFT).isDown()) {
            orientation = Orientation.LEFT;
        } else if (controller.get(Keyboard.UP).isDown()) {
            orientation = Orientation.UP;
        } else if (controller.get(Keyboard.RIGHT).isDown()) {
            orientation = Orientation.RIGHT;
        } else if (controller.get(Keyboard.DOWN).isDown()) {
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
}
