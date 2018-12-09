package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.Orb;
import ch.epfl.cs107.play.game.octoMAN.actor.StandardDoor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

import java.util.LinkedList;
import java.util.List;

/**
 * A class representing the sub rooms for the electricity area
 */
public abstract class Electricity extends SubRoom {
    private Electricity(String title) {
        super(title);
    }

    private static class Electricity1 extends Electricity {
        private Electricity1() {
            super("Electricity1");
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "LevelSelect", new DiscreteCoordinates(14, 8),
                    this, new DiscreteCoordinates(2, 0)
            );
            new StandardDoor(
                    Logic.TRUE, "Electricity9", new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(17, 19)
            );
        }
    }

    private static class Electricity9 extends Electricity {
        private Electricity9() {
            super("Electricity9");
        }


        @Override
        protected void addActors() {
            new Orb(Orb.Type.ELECTRICITY, this, new DiscreteCoordinates(2, 6));
        }

        @Override
        protected boolean reset() {
            return false;
        }
    }

    public static List<Electricity> subRooms() {
        List<Electricity> subRooms = new LinkedList<>();
        subRooms.add(new Electricity1());
        subRooms.add(new Electricity9());
        return subRooms;
    }
}