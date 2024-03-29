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
    /// The name of the animation for this player
    private String animationName;
    /// The halo around this player
    private ImageGraphics halo;
    /// Whether or not to display the halo
    private boolean displayHalo;
    /// The scale associated with the halo
    private float haloScale;
    /// The dialog for other actors to fill
    private AdvanceDialog dialog;
    /// The last portal this player passed through, null if none
    private Portal lastPortal;
    /// Whether or not we advanced dialog on the previous frame
    /// This is necessary to make sure that closing dialog doesn't
    /// reopen it with the actor.
    private boolean advancedDialog;
    /// The Interaction Handler for this player
    private final PlayerHandler handler;
    /// The HUD the player is interacting with
    private Hud hud;
    /// Whether or not the player has finished the game
    /// after collecting the final antidote
    private boolean finished;
    /// Whether or not we're currently slipping
    /// Used to not play moving frames
    private boolean slipping;
    /// Whether or not we're running
    private boolean running;
    /// The amount of frames per move
    private final int FRAMES_PER_MOVE = 8;

    public Player(Hud hud, Area area, String animationName, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        this.animation = new Animation(
                animationName, this, orientation,
                1.f, 1.3f, 16, 21, 4, 2
        );
        this.animationName = animationName;
        this.handler = new PlayerHandler();
        this.halo = new ImageGraphics(ResourcePath.getForegrounds("lightHalo"), 30.f, 30.f);
        this.halo.setParent(this);
        this.displayHalo = false;
        this.haloScale = 1.f;
        this.dialog = new AdvanceDialog("dialog.1", area);
        this.hud = hud;
    }

    private Button getKey(int code) {
        return getOwnerArea().getKeyboard().get(code);
    }

    public Portal getLastPortal() {
        return lastPortal;
    }

    public boolean isFinished() {
        return finished;
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
    private void leaveCurrentArea() {
        haloScale = 1.f;
        getOwnerArea().unregisterActor(this);
        hud.getWeightSack().empty();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        advancedDialog = false;
        lastPortal = null;
        if (dialog.isOpen()) {
            if (getKey(Keyboard.L).isPressed()) {
                dialog.advance();
                advancedDialog = true;
            }
        } else {
            // Hud interaction
            // J key
            if (getKey(74).isPressed()) {
                hud.getWeightSack().incrementCursor();
            }
            Orientation orientation = null;
            running = !slipping && getKey(Keyboard.K).isDown();
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
                    int frames = FRAMES_PER_MOVE / (running ? 2 : 1);
                    move(frames);
                } else {
                    setOrientation(orientation);
                }
            }
            if (!slipping && isMoving()) {
                animation.updateCycle();
                if (running) {
                    animation.updateCycle();
                }
            } else {
                animation.resetOrientation(getOrientation());
                slipping = false;
            }
            // this works, but I don't really know why
            Vector translate = new Vector(-14.5f, -14.5f).sub(new Vector(.1f, .1f).mul(haloScale));
            halo.setRelativeTransform(Transform.I.translated(translate).scaled(haloScale));
        }
    }

    @Override
    public void draw(Canvas canvas) {
        dialog.draw(canvas);
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
            animationName = giver.getAnimationName();
            animation = Animation.from4x4(animationName, Player.this);
            animation.resetOrientation(getOrientation());
        }

        @Override
        public void interactWith(Talkable entity) {
            dialog.setTalker(entity, getOrientation());
        }

        @Override
        public void interactWith(Potion potion) {
            Orientation orientation = getOrientation();
            DiscreteCoordinates behindMe = getCurrentMainCellCoordinates().jump(orientation.opposite().toVector());
            Area thisArea = getOwnerArea();
            if (thisArea.canEnter(Player.this, Collections.singletonList(behindMe))) {
                Player clone = new Player(hud, thisArea, animationName, orientation, behindMe);
                // We can't reuse enterArea
                thisArea.registerActor(clone);
                clone.setOwnerArea(thisArea);
                potion.consume();
            }
        }

        @Override
        public void interactWith(WiredLever lever) {
            lever.toggle();
        }

        @Override
        public void interactWith(Portal portal) {
            leaveCurrentArea();
            lastPortal = portal;
        }

        @Override
        public void interactWith(Orb orb) {
            orb.collect(hud.getHolder());
        }

        @Override
        public void interactWith(Toggleable toggle) {
            toggle.toggle();
        }

        @Override
        public void interactWith(Pedestal pedestal) {
            if (pedestal.isEmpty()) {
                Weight w = hud.getWeightSack().take();
                // This is fine, it just means we have no weights to place
                if (w != null) {
                    pedestal.place(w);
                }
            } else {
                hud.getWeightSack().addWeight(pedestal.take());
            }
        }

        @Override
        public void interactWith(LightToggler toggler) {
            displayHalo = toggler.isDark();
        }

        @Override
        public void interactWith(Light light) {
            haloScale += .3f;
            light.collect();
        }

        @Override
        public void interactWith(FinalAntidote antidote) {
            antidote.collect();
            finished = true;
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
        return !advancedDialog && getKey(Keyboard.L).isPressed();
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
