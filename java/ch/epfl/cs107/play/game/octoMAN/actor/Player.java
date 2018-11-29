package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.octoMAN.OctoBehavior;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

public class Player extends MovableAreaEntity implements Interactor {
    /// The sprite for this player
    private Animation animation;
    /// The halo around this player
    private ImageGraphics halo;
    /// Whether or not to display the halo
    private boolean displayHalo;
    /// The Interaction Handler for this player
    private final PlayerHandler handler;
    /// Whether or not we're currently slipping
    /// Used to not play moving frames
    private boolean slipping;
    /// Whether or not we're running
    private boolean running;
    /// The amount of frames per move
    private final int FRAMES_PER_MOVE = 8;

    public Player(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        this.animation = new Animation(
                "boy.1", this, orientation,
                1.f, 1.3f, 16, 21, 4, 2
        );
        this.handler = new PlayerHandler();
        this.halo = new ImageGraphics(ResourcePath.getForegrounds("lightHalo"), 10.f, 10.f);
        this.halo.setParent(this);
        this.halo.setAnchor(new Vector(-4.5f, -4.5f));
        this.displayHalo = false;
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
        running = getKey(Keyboard.K).isDown();
        if (getKey(Keyboard.LEFT).isDown() || getKey(Keyboard.A).isDown()) {
            orientation = Orientation.LEFT;
        } else if (getKey(Keyboard.UP).isDown() || getKey(Keyboard.W).isDown()) {
            orientation = Orientation.UP;
        } else if (getKey(Keyboard.RIGHT).isDown() || getKey(Keyboard.D).isDown()) {
            orientation = Orientation.RIGHT;
        } else if (getKey(Keyboard.DOWN).isDown() || getKey(Keyboard.S).isDown()) {
            orientation = Orientation.DOWN;
        }
        if (orientation != null) {
            if (getOrientation().equals(orientation)) {
                int frames = running ? 4 : 8;
                move(frames);
            } else {
                setOrientation(orientation);
            }
        }
        if (!slipping && isMoving()) {
            animation.updateCycle();
        } else {
            animation.resetOrientation(getOrientation());
            slipping = false;
        }
        halo.setRelativeTransform(Transform.I.scaled(1.f));
    }

    @Override
    public void draw(Canvas canvas) {
        animation.getSprite().draw(canvas);
        if (displayHalo) {
            halo.draw(canvas);
        }
    }

    /**
     * The handler for interactions from this player
     */
    private class PlayerHandler implements OctoInteractionVisitor {
        @Override
        public void interactWith(Boulder boulder) {
            boulder.push(getOrientation());
        }

        @Override
        public void interactWith(SpriteGiver giver) {
            animation = giver.getAnimation();
            animation.resetOrientation(getOrientation());
            animation.setParent(Player.this);
        }

        @Override
        public void interactWith(OctoBehavior.OctoCell cell) {
            if (cell.type == OctoBehavior.OctoCellType.SLIPPERY) {
                slipping = true;
                move(FRAMES_PER_MOVE);
            }
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
