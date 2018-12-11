package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.Collections;
import java.util.List;

/**
 * Represents a wire between on the corner between 2 lines
 */
public class CornerWire extends Wire {
    /// The orientation this wire accepts
    private Orientation accepted;
    /// The orientation this points to
    private Orientation pointed;
    /// The sprite to use for this wire when on
    private Sprite onSprite;
    /// The sprite to sue for this wiere when off
    private Sprite offSprite;

    public CornerWire(Orientation accepted, boolean clockwise, Area area, DiscreteCoordinates position) {
        super(area, accepted, position);
        this.accepted = accepted;
        String spriteName = "";
        switch (accepted) {
            case LEFT:
                if (clockwise) {
                    pointed = Orientation.DOWN;
                    spriteName = "wire.dr";
                } else {
                    pointed = Orientation.UP;
                    spriteName = "wire.ru";
                }
                break;
            case RIGHT:
                if (clockwise) {
                    pointed = Orientation.UP;
                    spriteName = "wire.lu";
                } else {
                    pointed = Orientation.DOWN;
                    spriteName = "wire.dl";
                }
                break;
            case DOWN:
                if (clockwise) {
                    pointed = Orientation.RIGHT;
                    spriteName = "wire.ru";
                } else {
                    pointed = Orientation.LEFT;
                    spriteName = "wire.lu";
                }
                break;
            case UP:
                if (clockwise) {
                    pointed = Orientation.LEFT;
                    spriteName = "wire.dl";
                } else {
                    pointed = Orientation.RIGHT;
                    spriteName = "wire.dr";
                }
                break;
        }
        onSprite = new Sprite(spriteName + ".on", 1.f, 1.f, this);
        offSprite = new Sprite(spriteName + ".off", 1.f, 1.f, this);
    }

    @Override
    protected Sprite getChargedSprite() {
        return onSprite;
    }

    @Override
    protected Sprite getUnChargedSprite() {
        return offSprite;
    }

    @Override
    protected boolean holdsCharge() {
        return false;
    }

    @Override
    public boolean accept(Orientation o) {
        return o == accepted;
    }

    @Override
    protected List<DiscreteCoordinates> connectedCells() {
        DiscreteCoordinates here = getCurrentMainCellCoordinates();
        DiscreteCoordinates next = here.jump(pointed.toVector());
        return Collections.singletonList(next);
    }
}
