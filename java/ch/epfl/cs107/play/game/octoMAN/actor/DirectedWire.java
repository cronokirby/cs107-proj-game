package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

import java.util.Collections;
import java.util.List;

/**
 * Represents a wire that carries it's charge the direction
 * of its orientation
 */
public class DirectedWire extends Wire {
    /// Up/Down and on
    private Sprite udOn;
    /// Up/Down and off
    private Sprite udOff;
    /// Left/Right and on
    private Sprite lrOn;
    /// Left/Right and off
    private Sprite lrOff;


    public DirectedWire(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        String wireName = orientation == Orientation.DOWN || orientation == Orientation.UP
                ? "wire.ud" : "wire.lr";
        udOn = new Sprite("wire.ud.on", 1.f, 1.f, this, null, Vector.ZERO, 1.f, -10f);
        udOff = new Sprite("wire.ud.off", 1.f, 1.f, this, null, Vector.ZERO, 1.f, -10f);
        lrOn = new Sprite("wire.lr.on", 1.f, 1.f, this, null, Vector.ZERO, 1.f, -10f);
        lrOff = new Sprite("wire.lr.off", 1.f, 1.f, this, null, Vector.ZERO, 1.f, -10f);
    }


    @Override
    public boolean accept(Orientation o) {
        return o == getOrientation();
    }

    @Override
    public Sprite getChargedSprite() {
        Orientation o = getOrientation();
        if (o == Orientation.DOWN || o == Orientation.UP) {
            return udOn;
        } else {
           return lrOn;
        }
    }

    @Override
    public Sprite getUnChargedSprite() {
        Orientation o = getOrientation();
        if (o == Orientation.DOWN || o == Orientation.UP) {
            return udOff;
        } else {
           return lrOff;
        }
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
