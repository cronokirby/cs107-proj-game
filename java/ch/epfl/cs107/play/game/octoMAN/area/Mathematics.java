package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.*;
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
                    Logic.TRUE, "LevelSelect", new DiscreteCoordinates(8,8),
                    this, new DiscreteCoordinates(5, 0)
            );
            new StandardDoor(
                   scale, "Mathematics9" , new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(5, 10)
            );
        }
    }

    private static class Mathematics9 extends Mathematics {
        private Mathematics9() {
            super("Mathematics9");
        }

        @Override
        protected void addActors() {
            new Orb(Orb.Type.MATHEMATICS, this, new DiscreteCoordinates(2, 6));
        }

        @Override
        protected boolean reset() {
            return false;
        }
    }

    /**
     * Get a list of all the sub rooms in this area
     */
    public static List<Mathematics> subRooms() {
        List<Mathematics> subRooms = new LinkedList<>();
        subRooms.add(new Mathematics1());
        subRooms.add(new Mathematics9());
        return subRooms;
    }
}
