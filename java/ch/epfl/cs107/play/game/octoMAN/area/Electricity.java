package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.*;
import ch.epfl.cs107.play.io.XMLTexts;
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
                    Logic.TRUE, "LevelSelect", new DiscreteCoordinates(15, 8),
                    this, new DiscreteCoordinates(2, 0)
            );
            String mobText = XMLTexts.getText("electricity-intro");
            new StaticMob(mobText, "mob.1", this, Orientation.DOWN, new DiscreteCoordinates(2, 2));
            new LightToggler(true, this, new DiscreteCoordinates(2, 1));
            new Light(this, new DiscreteCoordinates(3, 1));
            new Light(this, new DiscreteCoordinates(14, 6));
            new Light(this, new DiscreteCoordinates(5, 7));
            new Light(this, new DiscreteCoordinates(3, 12));
            new Light(this, new DiscreteCoordinates(5, 14));
            new Light(this, new DiscreteCoordinates(9, 10));
            new Light(this, new DiscreteCoordinates(18, 14));
            new StandardDoor(
                    Logic.TRUE, "Electricity2", new DiscreteCoordinates(12, 1),
                    this, new DiscreteCoordinates(17, 19)
            );
        }
    }

    private static class Electricity2 extends Electricity {
        private Electricity2() {
            super("Electricity2");
        }

        @Override
        protected void addActors() {
            new Light(this, new DiscreteCoordinates(21, 6));
            new Light(this, new DiscreteCoordinates(21, 8));
            new Light(this, new DiscreteCoordinates(1, 3));
            new Light(this, new DiscreteCoordinates(4, 6));
            new Light(this, new DiscreteCoordinates(1, 10));
            new Light(this, new DiscreteCoordinates(2, 12));
            new Light(this, new DiscreteCoordinates(1, 22));
            new Light(this, new DiscreteCoordinates(10, 22));
            new Light(this, new DiscreteCoordinates(21, 17));
            new StandardDoor(
                    Logic.TRUE, "Electricity9", new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(4, 23)
            );
        }
    }

    private static class Electricity9 extends Electricity {
        private Electricity9() {
            super("Electricity9");
        }


        @Override
        protected void addActors() {
            new LightToggler(false, this, new DiscreteCoordinates(2, 1));
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
        subRooms.add(new Electricity2());
        subRooms.add(new Electricity9());
        return subRooms;
    }
}
