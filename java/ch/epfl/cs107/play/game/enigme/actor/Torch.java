package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Represents a torch that can be toggled on or off
 */
public class Torch extends SwitchActor {
    /// The sprite to display when this is lit
    private Sprite litSprite;
    /// The sprite to display when this is not lit
    private Sprite unLitSprite;

    /**
     * Construct a new torch in a certain area at a certain position,
     * automatically registering it.
     */
    public Torch(Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        litSprite = new Sprite("torch.ground.on.1", 1.f, 1.f, this);
        unLitSprite = new Sprite("torch.ground.off", 1.f, 1.f, this);
    }

    @Override
    protected Sprite getOnSprite() {
        return litSprite;
    }

    @Override
    protected Sprite getOffSprite() {
        return unLitSprite;
    }

    /// Torch implements Interactable

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
