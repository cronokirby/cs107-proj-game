package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.*;
import ch.epfl.cs107.play.io.XMLTexts;
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
            new TalkingMob(
                    XMLTexts.getText("micro-intro"), "mob.4", this,
                    Orientation.DOWN, new DiscreteCoordinates(6, 2)
            );
            Wire start = new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(4, 4));
            new WiredLever(start, this, Orientation.UP, new DiscreteCoordinates(4, 3));
            new CrossWire(Orientation.LEFT, Orientation.UP, this, new DiscreteCoordinates(4, 5));
            new StandardDoor(
                    Logic.TRUE, "LevelSelect", new DiscreteCoordinates(21, 8),
                    this, new DiscreteCoordinates(4, 0)
            );
            new CornerWire(Orientation.UP, false, this, new DiscreteCoordinates(4, 6));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(4, 7));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(4, 8));
            Wire lastWire = new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(4, 9));
            new StandardDoor(
                    lastWire, "Microtech9", new DiscreteCoordinates(2, 1),
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

        @Override
        protected boolean reset() {
            return false;
        }
    }

    public static List<Microtech> subRooms() {
        List<Microtech> subRooms = new LinkedList<>();
        subRooms.add(new Microtech1());
        subRooms.add(new Microtech9());
        return subRooms;
    }
}
