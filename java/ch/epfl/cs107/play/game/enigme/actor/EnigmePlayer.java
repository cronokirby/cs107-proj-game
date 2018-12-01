package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

/**
 * Represents the main player of the demo2 game
 */
public class EnigmePlayer extends MovableAreaEntity implements Interactor {
    /// Null if the player has never passed a door
    private Door lastDoor;
    /// The sprite for this player
    private Sprite sprite;
    /// The Interaction Handler for this player
    private final EnigmePlayerHandler handler;
    /// The amount of frames per move
    private final int FRAMES_PER_MOVE = 8;

    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        this.sprite = new Sprite("ghost.1", 1.f, 1.f, this);
        this.handler = new EnigmePlayerHandler();
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
     * Set the current door the player is passing through
     * @param door the door the player should pass through
     */
    public void setIsPassingDoor(Door door) {
        handler.interactWith(door);
    }

    /**
     * @return the last door the player passed through, can be null
     */
    public Door passedDoor() {
        return lastDoor;
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
        lastDoor = null;
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    /**
     * The handler for interactions from this player
     */
    private class EnigmePlayerHandler implements EnigmeInteractionVisitor {
        @Override
        public void interactWith(Door door) {
            lastDoor = door;
        }

        @Override
        public void interactWith(Apple apple) {
            apple.eat();
        }

        @Override
        public void interactWith(Key key) {
            key.collect();
        }

        @Override
        public void interactWith(Toggleable entity) {
            entity.toggle();
        }
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }

    /// EnigmePlayer implements Interactor

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        DiscreteCoordinates here = getCurrentMainCellCoordinates();
        DiscreteCoordinates nextCell = here.jump(getOrientation().toVector());
        return Collections.singletonList(nextCell);
    }

    @Override
    public boolean wantsCellInteraction() {
        // The player always wants cell interaction
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        return getKey(Keyboard.L).isPressed();
    }

    @Override
    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }

    /// Demo2Player implements Interactable

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
