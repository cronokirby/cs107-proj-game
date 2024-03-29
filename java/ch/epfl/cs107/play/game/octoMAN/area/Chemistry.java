package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.Orb;
import ch.epfl.cs107.play.game.octoMAN.actor.StandardDoor;
import ch.epfl.cs107.play.game.octoMAN.actor.StaticMob;
import ch.epfl.cs107.play.io.XMLTexts;
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
                    Logic.TRUE, "LevelSelect", new DiscreteCoordinates(18, 8),
                    this, new DiscreteCoordinates(3, 0)
            );
            String mobText = XMLTexts.getText("chemistry-intro");
            new StaticMob(mobText, "mob.4", this, Orientation.DOWN, new DiscreteCoordinates(5, 1));
            new StandardDoor(
                    Logic.TRUE, "Chemistry2", new DiscreteCoordinates(3, 1),
                    this, new DiscreteCoordinates(6, 7)
            );
        }
    }

    private static class Chemistry2 extends Chemistry {
        private Chemistry2() {
            super("Chemistry2");
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "Chemistry3", new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(4, 15)
            );
        }
    }

    private static class Chemistry3 extends Chemistry {
        private Chemistry3() {
            super("Chemistry3");
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "Chemistry4", new DiscreteCoordinates(5, 1),
                    this, new DiscreteCoordinates(5, 13)
            );
        }
    }

    private static class Chemistry4 extends Chemistry {
        private Chemistry4() {
            super("Chemistry4");
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                Logic.TRUE, "Chemistry5", new DiscreteCoordinates(6, 1),
                this, new DiscreteCoordinates(13, 9)
            );
        }
    }

    private static class Chemistry5 extends Chemistry {
        private Chemistry5() {
            super("Chemistry5");
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "Chemistry9", new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(9, 16)
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

        @Override
        protected boolean reset() {
            return false;
        }
    }

    public static List<Chemistry> subRooms() {
        List<Chemistry> subRooms = new LinkedList<>();
        subRooms.add(new Chemistry1());
        subRooms.add(new Chemistry2());
        subRooms.add(new Chemistry3());
        subRooms.add(new Chemistry4());
        subRooms.add(new Chemistry5());
        subRooms.add(new Chemistry9());
        return subRooms;
    }
}
