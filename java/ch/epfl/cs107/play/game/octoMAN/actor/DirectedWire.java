package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.Collections;
import java.util.List;

/**
 * Represents a wire that carries it's charge the direction
 * of its orientation
 */
public class DirectedWire extends Wire {
    /// The sprite associated with this wire when live
    private Sprite chargedSprite;
    /// The sprite associated with this wire when not live
    private Sprite unChargedSprite;


    public DirectedWire(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        chargedSprite = new Sprite("GroundLightOn", 1.f, 1.f, this);
        unChargedSprite = new Sprite("GroundLightOff", 1.f, 1.f, this);
    }

    @Override
    public Sprite getChargedSprite() {
        return chargedSprite;
    }

    @Override
    public Sprite getUnChargedSprite() {
        return unChargedSprite;
    }

    @Override
    protected boolean holdsCharge() {
        return false;
    }

    @Override
    protected List<DiscreteCoordinates> connectedCells() {
        DiscreteCoordinates here = getCurrentMainCellCoordinates();
        DiscreteCoordinates next = here.jump(getOrientation().toVector());
        return Collections.singletonList(next);
    }
}
