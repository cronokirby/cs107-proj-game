package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.OctoBehavior;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

public class Player extends MovableAreaEntity implements Interactor {
    /// The sprite for this player
    private Animation animation;
    /// The Interaction Handler for this player
    private final PlayerHandler handler;
    /// The amount of frames per move
    private final int FRAMES_PER_MOVE = 8;

    public Player(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        this.animation = new Animation(
                "boy.1", this, orientation,
                1.f, 1.3f, 16, 21, 4, 2
        );
        this.handler = new PlayerHandler();
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
        if (isMoving()) {
            animation.updateCycle();
        } else {
            animation.resetOrientation(getOrientation());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        animation.getSprite().draw(canvas);
    }

    /**
     * The handler for interactions from this player
     */
    private class PlayerHandler implements OctoInteractionVisitor {
        @Override
        public void interactWith(OctoBehavior.OctoCell cell) {
            System.out.println(cell.type);
        }
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((OctoInteractionVisitor)v).interactWith(this);
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
