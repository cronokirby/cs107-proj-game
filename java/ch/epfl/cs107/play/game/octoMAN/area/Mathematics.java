package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.Pedestal;
import ch.epfl.cs107.play.game.octoMAN.actor.Scale;
import ch.epfl.cs107.play.game.octoMAN.actor.StandardDoor;
import ch.epfl.cs107.play.game.octoMAN.actor.Weight;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a sub room of the mathematics area
 */
public abstract class Mathematics extends SubRoom {
    private Mathematics(String title) {
        super(title);
    }

    private static class Mathematics1 extends Mathematics {
        private Mathematics1() {
            super("Mathematics1");
        }

        @Override
        protected void addActors() {
            new Pedestal(this, new DiscreteCoordinates(4, 2))
                    .place(new Weight("weight.square.1", 1));
            new Pedestal(this, new DiscreteCoordinates(3, 2))
                    .place(new Weight("weight.square.1", 1));
            new Pedestal(this, new DiscreteCoordinates(2, 2))
                    .place(new Weight("weight.square.2", 2));
            new Pedestal(this, new DiscreteCoordinates(1, 2))
                    .place(new Weight("weight.square.2", 2));
            List<Pedestal> left = new LinkedList<>();
            left.add(new Pedestal(this, new DiscreteCoordinates(4, 5)));
            left.add(new Pedestal(this, new DiscreteCoordinates(3, 5)));
            List<Pedestal> right = new LinkedList<>();
            right.add(new Pedestal(this, new DiscreteCoordinates(6, 5)));
            Logic scale = new Scale(left, right, this, new DiscreteCoordinates(5, 5));
            new StandardDoor(
                    scale, "LevelSelect", new DiscreteCoordinates(8,8),
                    this, new DiscreteCoordinates(5, 0)
            );
        }
    }

    /**
     * Get a list of all the sub rooms in this area
     */
    public static List<Mathematics> subRooms() {
        List<Mathematics> subRooms = new LinkedList<>();
        subRooms.add(new Mathematics1());
        return subRooms;
    }
}
