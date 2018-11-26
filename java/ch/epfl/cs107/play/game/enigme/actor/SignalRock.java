package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents a rock that disappears when it's signal is activated
 */
public class SignalRock extends AreaEntity {
    /// The signal to activate to make this rock disappear
    private Logic signal;
    /// The sprite to draw when this rock is present
    private Sprite rockSprite;

    public SignalRock(Logic signal, Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        area.registerActor(this);
        this.signal = signal;
        rockSprite = new Sprite("rock.3", 1.f, 1.f, this);
    }

    @Override
    public void draw(Canvas canvas) {
        if (!signal.isOn()) {
            rockSprite.draw(canvas);
        }
    }

    /// SignalRock implements Interactable


    @Override
    public boolean takeCellSpace() {
        return !signal.isOn();
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }
}
