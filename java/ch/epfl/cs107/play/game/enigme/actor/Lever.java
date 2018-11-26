package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Represents a lever that can be switch left or right.
 */
public class Lever extends SwitchActor {
    /// The sprite for the left (default) case
    private Sprite leftSprite;
    /// The sprite for the right case
    private Sprite rightSprite;

    public Lever(Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        leftSprite = new Sprite("lever.big.left", 1.f, 1.f, this);
        rightSprite = new Sprite("lever.big.right", 1.f, 1.f, this);
    }

    @Override
    protected Sprite getOnSprite() {
        return rightSprite;
    }

    @Override
    protected Sprite getOffSprite() {
        return leftSprite;
    }

    /// Lever implements interactable

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
}
