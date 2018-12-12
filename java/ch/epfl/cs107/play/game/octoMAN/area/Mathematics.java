package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.*;
import ch.epfl.cs107.play.io.XMLTexts;
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
            new TalkingMob(
                    XMLTexts.getText("math-intro"), "mob.3", this,
                    Orientation.DOWN, new DiscreteCoordinates(8, 2)
            );
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
                   scale, "Mathematics2" , new DiscreteCoordinates(5, 1),
                    this, new DiscreteCoordinates(5, 10)
            );
        }
    }

    private static class Mathematics2 extends Mathematics {
        private Mathematics2() {
            super("Mathematics2");
        }

        @Override
        protected void addActors() {
            new Pedestal(this, new DiscreteCoordinates(3, 1))
                    .place(new Weight("weight.circle.1", 1));
            new Pedestal(this, new DiscreteCoordinates(3, 2))
                    .place(new Weight("weight.circle.2", 4));
            new Pedestal(this, new DiscreteCoordinates(7, 1))
                    .place(new Weight("weight.circle.3", 5));
            new Pedestal(this, new DiscreteCoordinates(7, 2))
                    .place(new Weight("weight.circle.4", 2));
            List<Pedestal> left = new LinkedList<>();
            left.add(new Pedestal(this, new DiscreteCoordinates(4, 4)));
            left.add(new Pedestal(this, new DiscreteCoordinates(3, 4)));
            List<Pedestal> right = new LinkedList<>();
            right.add(new Pedestal(this, new DiscreteCoordinates(6, 4)));
            right.add(new Pedestal(this, new DiscreteCoordinates(7, 4)));
            Logic scale = new Scale(left, right, this, new DiscreteCoordinates(5, 4));
            new StandardDoor(
                scale, "Mathematics3", new DiscreteCoordinates(6, 1),
                    this, new DiscreteCoordinates(5, 7)
            );
        }
    }

    private static class Mathematics3 extends Mathematics {
        private Mathematics3() {
            super("Mathematics3");
        }

        @Override
        protected void addActors() {
            new Pedestal(this, new DiscreteCoordinates(4, 1))
                    .place(new Weight("weight.square.1", 1));
            new Pedestal(this, new DiscreteCoordinates(4, 2))
                    .place(new Weight("weight.square.2", 2));
            new Pedestal(this, new DiscreteCoordinates(4, 3))
                    .place(new Weight("weight.square.3", 8));
            new Pedestal(this, new DiscreteCoordinates(8, 1))
                    .place(new Weight("weight.square.4", 5));
            new Pedestal(this, new DiscreteCoordinates(8, 2))
                    .place(new Weight("weight.square.5", 6));
            List<Pedestal> left = new LinkedList<>();
            left.add(new Pedestal(this, new DiscreteCoordinates(5, 5)));
            left.add(new Pedestal(this, new DiscreteCoordinates(4, 5)));
            List<Pedestal> right = new LinkedList<>();
            right.add(new Pedestal(this, new DiscreteCoordinates(7, 5)));
            right.add(new Pedestal(this, new DiscreteCoordinates(8, 5)));
            right.add(new Pedestal(this, new DiscreteCoordinates(9, 5)));
            Logic scale = new Scale(left, right, this, new DiscreteCoordinates(6, 5));
            new StandardDoor(
                   scale, "Mathematics4" , new DiscreteCoordinates(6, 1),
                    this, new DiscreteCoordinates(6, 8)
            );
        }
    }

    private static class Mathematics4 extends Mathematics {
        private Mathematics4() {
            super("Mathematics4");
        }

        @Override
        protected void addActors() {
            new Pedestal(this, new DiscreteCoordinates(4, 1))
                    .place(new Weight("weight.tri.1", 3));
            new Pedestal(this, new DiscreteCoordinates(4, 2))
                    .place(new Weight("weight.tri.2", 2));
            new Pedestal(this, new DiscreteCoordinates(4, 3))
                    .place(new Weight("weight.tri.3", 9));
            new Pedestal(this, new DiscreteCoordinates(8, 1))
                    .place(new Weight("weight.tri.4", 1));
            new Pedestal(this, new DiscreteCoordinates(8, 2))
                    .place(new Weight("weight.tri.5", 4));
            new Pedestal(this, new DiscreteCoordinates(8, 3))
                    .place(new Weight("weight.square.1",5));
            List<Pedestal> left = new LinkedList<>();
            left.add(new Pedestal(this, new DiscreteCoordinates(5, 5)));
            left.add(new Pedestal(this, new DiscreteCoordinates(4, 5)));
            left.add(new Pedestal(this, new DiscreteCoordinates(3, 5)));
            List<Pedestal> right = new LinkedList<>();
            right.add(new Pedestal(this, new DiscreteCoordinates(7, 5)));
            right.add(new Pedestal(this, new DiscreteCoordinates(8, 5)));
            right.add(new Pedestal(this, new DiscreteCoordinates(9, 5)));
            Logic scale = new Scale(left, right, this, new DiscreteCoordinates(6, 5));
            new StandardDoor(
                    scale, "Mathematics9" , new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(6, 8)
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
        subRooms.add(new Mathematics2());
        subRooms.add(new Mathematics3());
        subRooms.add(new Mathematics4());
        subRooms.add(new Mathematics9());
        return subRooms;
    }
}
