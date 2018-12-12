package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.Orb;
import ch.epfl.cs107.play.game.octoMAN.actor.ResetSwitch;
import ch.epfl.cs107.play.game.octoMAN.actor.StandardDoor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;

import java.util.LinkedList;
import java.util.List;

public abstract class Computer extends SubRoom {
    private Computer(String title) {
        super(title);
    }

    private static class Computer1 extends Computer {
        private Computer1() {
            super("Computer1");
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                Logic.TRUE, "LevelSelect", new DiscreteCoordinates(24, 8),
                this, new DiscreteCoordinates(5, 0)
            );
            DiscreteCoordinates start = new DiscreteCoordinates(5, 1);
            List<Logic> buttons = new LinkedList<>();
            for (int x = 1; x <= 9; ++x) {
                for (int y = 1; y <= 7; ++y) {
                    buttons.add(new ResetSwitch(this, start, new DiscreteCoordinates(x, y)));
                }
            }
            Logic allButtons = new MultipleAnd(buttons);
            new StandardDoor(
                    allButtons, "Computer2", new DiscreteCoordinates(5, 1),
                    this, new DiscreteCoordinates(5, 8)
            );
        }
    }

    private static class Computer2 extends Computer {
        private Computer2() {
            super("Computer2");
        }

        @Override
        protected void addActors() {
            DiscreteCoordinates start = new DiscreteCoordinates(5, 1);
            int[][] xs = {
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 1, 2, 8, 9 },
                { 1, 2, 8, 9 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
            };
            List<Logic> buttons = new LinkedList<>();
            for (int i = 0; i < xs.length; ++i) {
                int y = 8 - i;
                for (int j = 0; j < xs[i].length; ++j) {
                    buttons.add(new ResetSwitch(this, start, new DiscreteCoordinates(xs[i][j], y)));
                }
            }
             Logic allButtons = new MultipleAnd(buttons);
             new StandardDoor(
                     allButtons, "Computer3", new DiscreteCoordinates(6, 1),
                    this, new DiscreteCoordinates(5, 9)
            );
        }
    }

    private static class Computer3 extends Computer {
         private Computer3() {
             super("Computer3");
         }

        @Override
        protected void addActors() {
             DiscreteCoordinates start = new DiscreteCoordinates(6, 1);
            int[][] xs = {
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
                { 1, 2, 5, 6, 9, 10, 11 },
                { 1, 2, 5, 6, 9, 10, 11 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
                { 1, 2, 5, 6, 7, 8, 9, 10, 11 },
                { 1, 2, 5, 6, 7, 10, 11 },
                { 1, 2, 3, 4, 5, 6, 7, 10, 11 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 },
            };
            List<Logic> buttons = new LinkedList<>();
            for (int i = 0; i < xs.length; ++i) {
                int y = 10 - i;
                for (int j = 0; j < xs[i].length; ++j) {
                    buttons.add(new ResetSwitch(this, start, new DiscreteCoordinates(xs[i][j], y)));
                }
            }
            Logic allButtons = new MultipleAnd(buttons);
            new StandardDoor(
                    allButtons, "Computer9", new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(6, 11)
            );
        }
    }

    private static class Computer4 extends Computer {
        private Computer4() {
            super("Computer4");
        }

        @Override
        protected void addActors() {
            int[][] xs = {

            };
        }
    }

    private static class Computer9 extends Computer {
        private Computer9() {
            super("Computer9");
        }

        @Override
        protected void addActors() {
            new Orb(Orb.Type.COMPUTERSCIENCE, this, new DiscreteCoordinates(2, 6));
        }

        @Override
        protected boolean reset() {
            return false;
        }
    }

    public static List<Computer> subRooms() {
        List<Computer> subRooms = new LinkedList<>();
        subRooms.add(new Computer1());
        subRooms.add(new Computer2());
        subRooms.add(new Computer3());
        subRooms.add(new Computer9());
        return subRooms;
    }
}
