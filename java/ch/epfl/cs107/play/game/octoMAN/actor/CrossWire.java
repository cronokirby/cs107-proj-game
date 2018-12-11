package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Represents a wire that switches between 2 orientations
 */
public class CrossWire extends DirectedWire implements Toggleable {
    /// Which of the orientations this is in
    private boolean first;
    /// The first orientation this can be in
    private Orientation firstOrientation;
    /// The second orientation this can be in
    private Orientation secondOrientation;
    /// The number of frames in which to flip
    private int flip;

    public CrossWire(Orientation first, Orientation second, Area area, DiscreteCoordinates position) {
        super(area, first, position);
        this.first = true;
        firstOrientation = first;
        secondOrientation = second;
    }

    @Override
    public void update(float deltaTime) {
        if (flip > 0) {
            --flip;
        } else if (flip == 0) {
            flip = -1;
            if (first) {
                setOrientation(firstOrientation);
            } else {
                setOrientation(secondOrientation);
            }
        }
    }

    @Override
    public void toggle() {
        first = !first;
        removeCharge();
        flip = 1;
    }

    @Override
    public void charge(Orientation orientation) {
        if (flip < 0) {
            super.charge(orientation);
        }
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        super.acceptInteraction(v);
        ((OctoInteractionVisitor)v).interactWith((Toggleable)this);
    }
}
