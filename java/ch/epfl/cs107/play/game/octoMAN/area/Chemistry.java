package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.Orb;
import ch.epfl.cs107.play.game.octoMAN.actor.StandardDoor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a sub room in the Chemistry area
 */
public abstract class Chemistry extends SubRoom {
    private Chemistry(String title) {
        super(title);
    }

    private static class Chemistry1 extends Chemistry {
        private Chemistry1() {
            super("Chemistry1");
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "LevelSelect", new DiscreteCoordinates(17, 8),
                    this, new DiscreteCoordinates(3, 0)
            );
            new StandardDoor(
                    Logic.TRUE, "Chemistry9", new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(6, 7)
            );
        }
    }

    private static class Chemistry9 extends Chemistry {
        private Chemistry9() {
            super("Chemistry9");
        }

        @Override
        protected void addActors() {
            new Orb(Orb.Type.CHEMISTRY, this, new DiscreteCoordinates(2, 6));
        }
    }

    public static List<Chemistry> subRooms() {
        List<Chemistry> subRooms = new LinkedList<>();
        subRooms.add(new Chemistry1());
        subRooms.add(new Chemistry9());
        return subRooms;
    }
}