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
 * Represents an entity that can switch between 2 states
 */
public abstract class SwitchActor extends AreaEntity implements Logic, Toggleable {
    /// Whether or not this actor is switched on
    private boolean on;

    /**
     * Create a new SwitchActor, and registers it in it
     * @param area the area to register this sprite in
     * @param orientation the initial orientatioe
        public void interactWith(ResetSwitch pressureSwitch) {
            pressureSwitch.toggle();
        }n of this entity
     * @param position the initial position of this entity
     */
    public SwitchActor(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        area.registerActor(this);
        this.on = false;
    }

    /**
     * Toggle the activation of this actor
     */
    public void toggle() {
        on = !on;
    }

    /**
     * @return the sprite to display when this is on (not null)
     */
    protected abstract Sprite getOnSprite();

    /**
     * @return the sprite to display when this is off (not null)
     */
    protected abstract Sprite getOffSprite();



    @Override
    public void draw(Canvas canvas) {
        if (on) {
            getOnSprite().draw(canvas);
        } else {
            getOffSprite().draw(canvas);
        }
    }

    @Override
    public boolean isOn() {
        return on;
    }

    // This logic is common amongst all switch actors
    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith((Toggleable)this);
    }
}
