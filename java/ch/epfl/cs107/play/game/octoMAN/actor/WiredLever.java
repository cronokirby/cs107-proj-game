package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class WiredLever extends AreaEntity {
    /// The sprite associated with this lever when not on
    private Sprite leftSprite;
    /// The sprite associated with this lever when on
    private Sprite rightSprite;
    /// Whether or not this lever is left
    private boolean isLeft;
    /// The wire this is connected to
    private Wire wire;


    public WiredLever(Wire wire, Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        area.registerActor(this);
        leftSprite = new Sprite("lever.big.left", 1.f, 1.f, this);
        rightSprite = new Sprite("lever.big.right", 1.f, 1.f, this);
        isLeft = true;
        this.wire = wire;
    }


    /**
     * Toggle this lever
     */
    public void toggle() {
        isLeft = !isLeft;
        if (!isLeft) {
           wire.charge();
        } else {
            wire.unCharge();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (isLeft) {
            leftSprite.draw(canvas);
        } else {
            rightSprite.draw(canvas);
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
