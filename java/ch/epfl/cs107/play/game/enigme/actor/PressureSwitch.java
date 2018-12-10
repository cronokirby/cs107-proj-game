package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents a switch that gets activated when stepped on
 */
public class PressureSwitch extends AreaEntity implements Logic, Toggleable {
    /// Whether or not this was being pressed last frame
    private boolean lastPressed;
    /// Whether or not someone is pressing us now
    private boolean pressed;
    /// Whether or not this switch is on or off
    private boolean switchedOn;
    /// The sprite for when this has not been switched
    private Sprite unSwitchedSprite;
    /// The sprite for when this has been switched
    private Sprite switchedSprite;

    /**
     * Create a new pressure switch in an area at a certain position,
     * automatically registering it.
     */
    public PressureSwitch(Area area, DiscreteCoordinates position) {
        this("GroundLightOn", "GroundLightOff", area, position);
        /*

        */
    }

    /**
     * Create a new pressure switch with
     * @param onSpriteName the sprite to display when this is on
     * @param offSpriteName the sprite to display when this is off
     * @param area the area to put this in (automatically registered)
     * @param position the position this will be at
     */
    public PressureSwitch(String onSpriteName, String offSpriteName, Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        area.registerActor(this);
        lastPressed = false;
        pressed = false;
        switchedOn = false;
        unSwitchedSprite = new Sprite(
                offSpriteName, 1.f, 1.f, this,
                null, Vector.ZERO, 1.f, -10f
        );
        switchedSprite = new Sprite(
                onSpriteName, 1.f, 1.f, this,
                null, Vector.ZERO, 1.f, -10f
        );
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        // Only toggle if we've changed to being pressed
        if (!lastPressed && pressed) {
            switchedOn = !switchedOn;
        }
        lastPressed = pressed;
        pressed = false;
    }

    @Override
    public void draw(Canvas canvas) {
        if (switchedOn) {
            switchedSprite.draw(canvas);
        } else {
            unSwitchedSprite.draw(canvas);
        }
    }

    @Override
    public void toggle() {
        pressed = true;
    }

    @Override
    public boolean isOn() {
        return switchedOn;
    }


    /// PressureSwitch implements Interactable

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith((Toggleable)this);
    }
}
