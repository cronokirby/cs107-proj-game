package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents a pedestal onto which a weight can be placed
 */
public class Pedestal extends AreaEntity {
    /// The weight on this pedestal or none of no weight
    private Weight weight;
    /// The sprite for the underlying pedestal
    private Sprite sprite;

    public Pedestal(Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        area.registerActor(this);
        sprite = new Sprite("pedestal", 1.f, 1.f, this);
    }


    /**
     * Return true if this pedestal has no elements
     */
    public boolean isEmpty() {
        return weight == null;
    }

    /**
     * Place a weight on this pedestal
     */
    public void place(Weight weight) {
        this.weight = weight;
        this.weight.attachSprite(this);
    }

    /**
     * Take off a weight from this pedestal
     * @return null if no weight was on this pedestal
     */
    public Weight take() {
        Weight ret = weight;
        weight = null;
        return ret;
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
        if (weight != null) {
            weight.draw(canvas);
        }
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((OctoInteractionVisitor)v).interactWith(this);
    }
}
