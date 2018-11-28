package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents a class of boulders that can be pushed around
 */
public class Boulder extends MovableAreaEntity {
    /// The sprite associated with this boulder
    private Sprite sprite;
    /// The time it takes to move this
    private final int FRAMES_FOR_MOVE = 8;

    /**
     * Construct a boulder in an area at a certain position
     */
    public Boulder(Area area, DiscreteCoordinates position) {
        // We just pass a dummy orientation, since we set the orientation
        // before every move anyways
        super(area, Orientation.DOWN, position);
        area.registerActor(this);
        sprite = new Sprite("rock.2", 1.f, 1.f, this);
    }


    /**
     * Push this in a certain direction.
     */
    public void push(Orientation orientation) {
        setOrientation(orientation);
        move(FRAMES_FOR_MOVE);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    /// Boulder implements interactable


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
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((OctoInteractionVisitor)v).interactWith(this);
    }
}
