package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.Orb;
import ch.epfl.cs107.play.game.octoMAN.actor.StandardDoor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

import java.util.LinkedList;
import java.util.List;

public abstract class Microtech extends SubRoom {
    private Microtech(String title) {
        super(title);
    }

    private static class Microtech1 extends Microtech {
        private Microtech1() {
            super("Microtech1");
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "LevelSelect", new DiscreteCoordinates(20, 8),
                    this, new DiscreteCoordinates(4, 0)
            );
            new StandardDoor(
                    Logic.TRUE, "Microtech9", new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(4, 10)
            );
        }
    }

    private static class Microtech9 extends Microtech {
        private Microtech9() {
            super("Microtech9");
        }

        @Override
        protected void addActors() {
            new Orb(Orb.Type.MICROTECHNICS, this, new DiscreteCoordinates(2, 6));
        }
    }

    public static List<Microtech> subRooms() {
        List<Microtech> subRooms = new LinkedList<>();
        subRooms.add(new Microtech1());
        subRooms.add(new Microtech9());
        return subRooms;
    }
}
