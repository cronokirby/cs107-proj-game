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
 * Represents a pressure plate that automatically switches itself off
 */
public class PressurePlate extends AreaEntity implements Logic, Toggleable {
    /// The time it takes for this plate to reset
    private float resetTime;
    /// The time left until a reset
    /// If this is greater than 0, then this plate is on
    private float timeLeft;
    /// The sprite to display when this is on
    private Sprite onSprite;
    /// The sprite to display when this is off
    private Sprite offSprite;

    public PressurePlate(float resetTime, Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        area.registerActor(this);
        this.resetTime = resetTime;
        this.timeLeft = -1.f;
        this.onSprite = new Sprite("GroundLightOn", 1.f, 1.f, this);
        this.offSprite = new Sprite("GroundPlateOff", 1.f, 1.f, this);
    }

    @Override
    public void toggle() {
        timeLeft = resetTime;
    }

    @Override
    public void draw(Canvas canvas) {
        if (isOn()) {
            onSprite.draw(canvas);
        } else {
            offSprite.draw(canvas);
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        timeLeft -= deltaTime;
    }

    /// PressurePlate implements Logic

    @Override
    public boolean isOn() {
        return timeLeft > 0.f;
    }

    /// PressurePlate implements Interactable

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
