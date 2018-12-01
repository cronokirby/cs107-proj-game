package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Represents an entity wandering around at random
 */
public abstract class WanderingEntity extends MovableAreaEntity {
    private Random rand;

    public WanderingEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        rand = new Random();
    }

    /**
     * Get access to the animation of this entity
     */
    protected abstract Animation getAnimation();

    /**
     * This returns true if this entity can currently move
     */
    protected boolean canMove() {
        return true;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (canMove()) {
            double random = rand.nextDouble();
            if (random < 1. / 48) {
                List<Orientation> orientations = Arrays.asList(Orientation.values());
                int next = rand.nextInt(orientations.size());
                Orientation orientation = orientations.get(next);
                setOrientation(orientation);
                getAnimation().resetOrientation(orientation);
                move(12);
            }
        }
        if (isMoving()) {
            getAnimation().updateCycle();
        }
    }
}