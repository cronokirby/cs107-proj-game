package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents an entity that can transfer
 * it's sprite to another entity
 */
public class SpriteGiver extends AreaEntity {
    /// The animation this entity will transfer
    private Animation animation;
    /// The name of this animation
    private String animationName;

    /**
     * Construct a sprite giver from the name of a 4x4 16x21 sprite sheet,
     * an area, and a position.
     *
     * This restricted form is general enough for all the animation
     * sheets we're interested in.
     */
    public SpriteGiver(String animationSheet, Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        area.registerActor(this);
        Orientation orientation = Orientation.DOWN;
        Animation animation = Animation.from4x4(animationSheet, this);
        this.animation = animation;
        this.animationName = animationSheet;
    }

    /**
     * Return the name of the animation this entity can transfer.
     */
    public String getAnimationName() {
        return animationName;
    }

    @Override
    public void draw(Canvas canvas) {
        animation.getSprite().draw(canvas);
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
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((OctoInteractionVisitor)v).interactWith(this);
    }
}
