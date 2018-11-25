package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

/**
 * Represents a collectable apple in an area
 */
public class Apple extends AreaEntity {
    /// The sprite for this apple
    private Sprite sprite;

    /**
     * Construct a new apple.
     * Unlike EnigmePlayer, this will also register the apple in the area
     * passed to the constructor
     * @param area the area this apple will live in
     * @param orientation the initial orientation of this apple
     * @param position the position of this apple in the area
     */
    public Apple(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        area.registerActor(this);
        sprite = new Sprite("apple.1", 1.f, 1.f, this);
    }

    /**
     * Have this apple get eaten by somebody,
     * making it leave the area permanently.
     */
    public void eat() {
        getOwnerArea().unregisterActor(this);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    /// Apple implements Interactable


    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
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
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }
}
