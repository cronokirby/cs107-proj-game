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
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(4, 6));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(4, 7));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(4, 8));
            Wire lastWire = new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(4, 9));
            new StandardDoor(
                    lastWire, "Microtech2", new DiscreteCoordinates(8, 1),
                    this, new DiscreteCoordinates(4, 10)
            );
        }
    }

    private static class Microtech2 extends Microtech {
        private Microtech2() {
            super("Microtech2");
        }

        @Override
        protected void addActors() {
            Wire start1 = new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(7, 4));
            new WiredLever(start1, this, Orientation.UP, new DiscreteCoordinates(7, 3));
            Wire start2 = new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(9, 4));
            new WiredLever(start2, this, Orientation.UP, new DiscreteCoordinates(9, 3));
            new CornerWire(Orientation.UP, true, this, new DiscreteCoordinates(9, 5));
            new DirectedWire(this, Orientation.LEFT, new DiscreteCoordinates(8, 5));
            new CrossWire(Orientation.UP, Orientation.LEFT, this, new DiscreteCoordinates(7, 5));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(7, 6));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(7, 7));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(7, 8));
            new CornerWire(Orientation.UP, false, this, new DiscreteCoordinates(7, 9));
            new CrossWire(Orientation.UP, Orientation.RIGHT, this, new DiscreteCoordinates(8, 9));
            new DirectedWire(this, Orientation.RIGHT, new DiscreteCoordinates(9, 9));
            new CornerWire(Orientation.RIGHT, true, this, new DiscreteCoordinates(10, 9));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(10, 10));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(10, 11));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(10, 12));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(10, 13));
            new DirectedWire(this, Orientation.LEFT, new DiscreteCoordinates(6, 5));
            new DirectedWire(this, Orientation.LEFT, new DiscreteCoordinates(5, 5));
            new DirectedWire(this, Orientation.LEFT, new DiscreteCoordinates(4, 5));
            new DirectedWire(this, Orientation.LEFT, new DiscreteCoordinates(3, 5));
            new CornerWire(Orientation.LEFT, false, this, new DiscreteCoordinates(2, 5));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(2, 6));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(2, 7));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(2, 8));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(2, 9));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(2, 10));
            new CornerWire(Orientation.UP, false, this, new DiscreteCoordinates(2, 11));
            new DirectedWire(this, Orientation.RIGHT, new DiscreteCoordinates(3, 11));
            new DirectedWire(this, Orientation.RIGHT, new DiscreteCoordinates(4, 11));
            new DirectedWire(this, Orientation.RIGHT, new DiscreteCoordinates(5, 11));
            new CornerWire(Orientation.RIGHT, true, this, new DiscreteCoordinates(6, 11));
            new CrossWire(Orientation.LEFT, Orientation.UP, this, new DiscreteCoordinates(6, 12));
            new CornerWire(Orientation.UP, false, this, new DiscreteCoordinates(6, 13));
            new DirectedWire(this, Orientation.RIGHT, new DiscreteCoordinates(7, 13));
            new DirectedWire(this, Orientation.RIGHT, new DiscreteCoordinates(8, 13));
            Wire lastWire = new CornerWire(Orientation.RIGHT, true, this, new DiscreteCoordinates(9, 13));
            new StandardDoor(
                    lastWire, "Microtech3", new DiscreteCoordinates(5, 1),
                    this, new DiscreteCoordinates(9, 14)
            );
        }
    }

    private static class Microtech3 extends Microtech {
        private Microtech3() {
            super("Microtech3");
        }

        @Override
        protected void addActors() {
            Wire start1 = new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(4, 4));
            new WiredLever(start1, this, Orientation.UP, new DiscreteCoordinates(4, 3));
            Wire start2 = new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(6,4));
            new WiredLever(start2, this, Orientation.UP, new DiscreteCoordinates(6, 3));
            Wire start3 = new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(8, 4));
            new WiredLever(start3, this, Orientation.UP, new DiscreteCoordinates(8, 3));
            // Make the columns
            for (int i = 5; i <= 16; ++i) {
                Orientation o = i == 7 || i == 11 || i == 15 ? Orientation.RIGHT : Orientation.LEFT;
                new CrossWire(Orientation.UP, o, this, new DiscreteCoordinates(4, i));
                new CrossWire(Orientation.UP, o, this, new DiscreteCoordinates(6, i));
            }
            int[] xs = { 3, 5, 7 };
            int[] leftYs = { 5, 9, 13 };
            int[] rightYs = { 7, 11 };
            for (int x : xs) {
                for (int y : leftYs) {
                    new DirectedWire(this, Orientation.LEFT, new DiscreteCoordinates(x, y));
                }
                for (int y : rightYs) {
                    new DirectedWire(this, Orientation.RIGHT, new DiscreteCoordinates(x, y));
                }
            }
            new DirectedWire(this, Orientation.RIGHT, new DiscreteCoordinates(3, 15));
            new CornerWire(Orientation.LEFT, false, this, new DiscreteCoordinates(2, 5));
            new CornerWire(Orientation.LEFT, false, this, new DiscreteCoordinates(2, 9));
            new CornerWire(Orientation.LEFT, false, this, new DiscreteCoordinates(2, 13));
            new CornerWire(Orientation.RIGHT, true, this, new DiscreteCoordinates(8, 7));
            new CornerWire(Orientation.RIGHT, true, this, new DiscreteCoordinates(8, 11));
            new CornerWire(Orientation.RIGHT, true, this, new DiscreteCoordinates(5, 15));
            new CornerWire(Orientation.UP, true, this, new DiscreteCoordinates(8, 5));
            new CornerWire(Orientation.UP, true, this, new DiscreteCoordinates(8, 9));
            new CornerWire(Orientation.UP, true, this, new DiscreteCoordinates(8, 13));
            new CornerWire(Orientation.UP, false, this, new DiscreteCoordinates(2, 7));
            new CornerWire(Orientation.UP, false, this, new DiscreteCoordinates(2, 11));
            new CornerWire(Orientation.UP, false, this, new DiscreteCoordinates(2, 15));
            // filling in the middle gaps when turning corners
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(2, 6));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(2, 10));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(2, 14));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(8, 8));
            new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(8, 12));
            Wire lastWire = new DirectedWire(this, Orientation.UP, new DiscreteCoordinates(5, 16));
            new StandardDoor(
                    lastWire, "Microtech9", new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(5, 17)
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
        subRooms.add(new Microtech2());
        subRooms.add(new Microtech3());
        subRooms.add(new Microtech9());
        return subRooms;
    }
}
