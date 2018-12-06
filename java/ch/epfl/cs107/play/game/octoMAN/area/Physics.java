package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.Boulder;
import ch.epfl.cs107.play.game.octoMAN.actor.Orb;
import ch.epfl.cs107.play.game.octoMAN.actor.StandardDoor;
import ch.epfl.cs107.play.game.octoMAN.actor.TalkingMob;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

import java.util.LinkedList;
import java.util.List;

/**
 * Holds the logic for rooms in the physics area
 */
public abstract class Physics extends OctoArea {
    /// The title of this subroom
    private String title;

    private Physics(String title) {
        this.title = title;
    }

    /**
     * Get all the boulders in this area
     */
    protected abstract List<DiscreteCoordinates> getBoulders();

    /**
     * Add all the necessary actors (besides boulders) to this area
     */
    protected abstract void addActors();


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        for (DiscreteCoordinates coordinate : getBoulders()) {
            new Boulder(this, coordinate);
        }
        addActors();
        return superOK;
    }


    @Override
    public boolean resume(Window window, FileSystem fileSystem) {
        return begin(window, fileSystem);
    }


    private static class Physics1 extends Physics {
        private Physics1() {
            super("Physics1");
        }

        @Override
        protected List<DiscreteCoordinates> getBoulders() {
            List<DiscreteCoordinates> boulders1 = new LinkedList<>();
            for (int x = 1; x <= 7; ++x) {
                boulders1.add(new DiscreteCoordinates(x, 4));
            }
            return boulders1;
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "LevelSelect", new DiscreteCoordinates(2, 8),
                    this, new DiscreteCoordinates(4, 0)
            );
            new StandardDoor(
                    Logic.TRUE, "Physics2", new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(4, 8)
            );
            String helpText =
                    "La Physique...c'est physique!  "
                    + "Pousse les rochers pour avancer!"
                    + "Si tu fais une erreur, sort et recommence!";
            new TalkingMob(
                    helpText, "mob.2", this,
                    Orientation.DOWN, new DiscreteCoordinates(3, 2)
            );
        }
    }

    private static class Physics2 extends Physics {
        private Physics2() {
            super("Physics2");
        }

        @Override
        protected List<DiscreteCoordinates> getBoulders() {
            List<DiscreteCoordinates> boulders = new LinkedList<>();
            int[] xs = { 1, 2, 3, 2, 1, 3 };
            int[] ys = { 3, 3, 3, 4, 5, 5 };
            for (int i = 0; i < xs.length; ++i) {
                boulders.add(new DiscreteCoordinates(xs[i], ys[i]));
            }
            return boulders;
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "Physics1", new DiscreteCoordinates(4, 7),
                    this, new DiscreteCoordinates(2, 0)
            );
            new StandardDoor(
                    Logic.TRUE, "Physics3", new DiscreteCoordinates(3, 1),
                    this, new DiscreteCoordinates(2, 8)
            );
        }
    }

    private static class Physics3 extends Physics {
        private Physics3() {
            super("Physics3");
        }

        @Override
        protected List<DiscreteCoordinates> getBoulders() {
            List<DiscreteCoordinates> boulders = new LinkedList<>();
            int[] xs = { 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4 };
            int[] ys = { 7, 7, 6, 6, 6, 5, 5, 4, 4, 4, 3, 3 };
            for (int i = 0; i < xs.length; ++i) {
                boulders.add(new DiscreteCoordinates(xs[i], ys[i]));
            }
            return boulders;
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "Physics2", new DiscreteCoordinates(2, 7),
                    this, new DiscreteCoordinates(3, 0)
            );
            new StandardDoor(
                    Logic.TRUE, "Physics4", new DiscreteCoordinates(3, 1),
                    this, new DiscreteCoordinates(3, 10)
            );
        }
    }

    private static class Physics4 extends Physics {
        private Physics4() {
            super("Physics4");
        }

        @Override
        protected List<DiscreteCoordinates> getBoulders() {
            List<DiscreteCoordinates> boulders = new LinkedList<>();
            int[] xs = { 1, 4, 1, 3, 1, 2, 4, 3, 1, 2, 3, 4 };
            int[] ys = { 7, 7, 6, 6, 5, 5, 5, 4, 3, 3, 3, 3 };
            for (int i = 0; i < xs.length; ++i) {
                boulders.add(new DiscreteCoordinates(xs[i], ys[i]));
            }
            return boulders;
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "Physics3", new DiscreteCoordinates(3, 9),
                    this, new DiscreteCoordinates(3, 0)
            );
            new StandardDoor(
                    Logic.TRUE, "Physics5", new DiscreteCoordinates(3, 1),
                    this, new DiscreteCoordinates(2, 10)
            );
        }
    }

    private static class Physics5 extends Physics {
        private Physics5() {
            super("Physics5");
        }

        @Override
        protected List<DiscreteCoordinates> getBoulders() {
            List<DiscreteCoordinates> boulders = new LinkedList<>();
            int[] xs = { 1, 2, 3, 4, 5, 2, 4, 3, 2, 3, 4, 1, 2, 3, 4, 5 };
            int[] ys = { 7, 7, 7, 7, 7, 6, 6, 5, 4, 4, 4, 3, 3, 3, 3, 3 };
            for (int i = 0; i < xs.length; ++i) {
                boulders.add(new DiscreteCoordinates(xs[i], ys[i]));
            }
            return boulders;
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "Physics4", new DiscreteCoordinates(2, 9),
                    this, new DiscreteCoordinates(3, 0)
            );
            new StandardDoor(
                    Logic.TRUE, "Physics6", new DiscreteCoordinates(3, 1),
                    this, new DiscreteCoordinates(3, 10)
            );
        }
    }

    private static class Physics6 extends Physics {
        private Physics6() {
            super("Physics6");
        }

        @Override
        protected List<DiscreteCoordinates> getBoulders() {
            List<DiscreteCoordinates> boulders = new LinkedList<>();
            int[] xs = { 3, 5, 1, 2, 4, 2, 3, 5, 1, 3, 4, 2, 4, 5, 1, 3 };
            int[] ys = { 8, 8, 7, 7, 7, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3 };
            for (int i = 0; i < xs.length; ++i) {
                boulders.add(new DiscreteCoordinates(xs[i], ys[i]));
            }
            return boulders;
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "Physics5", new DiscreteCoordinates(3, 9),
                    this, new DiscreteCoordinates(3, 0)
            );
            new StandardDoor(
                    Logic.TRUE, "Physics7", new DiscreteCoordinates(3, 1),
                    this, new DiscreteCoordinates(3, 11)
            );
        }
    }

    private static class Physics7 extends Physics {
        private Physics7() {
            super("Physics7");
        }

        @Override
        protected List<DiscreteCoordinates> getBoulders() {
            List<DiscreteCoordinates> boulders = new LinkedList<>();
            int[] xs = { 2, 4, 5, 1, 3, 2, 3, 4, 1, 2, 4, 3, 5 };
            int[] ys = { 7, 7, 7, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3 };
            for (int i = 0; i < xs.length; ++i) {
                boulders.add(new DiscreteCoordinates(xs[i], ys[i]));
            }
            return boulders;
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "Physics6", new DiscreteCoordinates(3, 10),
                    this, new DiscreteCoordinates(3, 0)
            );
            new StandardDoor(
                    Logic.TRUE, "Physics8", new DiscreteCoordinates(3, 1),
                    this, new DiscreteCoordinates(3, 10)
            );
        }
    }

    private static class Physics8 extends Physics {
        private Physics8() {
            super("Physics8");
        }

        @Override
        protected List<DiscreteCoordinates> getBoulders() {
            List<DiscreteCoordinates> boulders = new LinkedList<>();
            int[] xs = { 1, 3, 5, 2, 4, 1, 3, 5, 1, 3, 5, 2, 4, 1, 2, 3, 4, 5 };
            int[] ys = { 8, 8, 8, 7, 7, 6, 6, 6, 5, 5, 5, 4, 4, 3, 3, 3, 3, 3 };
            for (int i = 0; i < xs.length; ++i) {
                boulders.add(new DiscreteCoordinates(xs[i], ys[i]));
            }
            return boulders;
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "Physics7", new DiscreteCoordinates(3, 9),
                    this, new DiscreteCoordinates(3, 0)
            );
            new StandardDoor(
                    Logic.TRUE, "Physics9", new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(3, 11)
            );
        }
    }

    private static class Physics9 extends Physics {
        private Physics9() {
            super("Physics9");
        }

        @Override
        protected List<DiscreteCoordinates> getBoulders() {
            return new LinkedList<>();
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "Physics8", new DiscreteCoordinates(3, 10),
                    this, new DiscreteCoordinates(2, 0)
            );
            new Orb(Orb.Type.PHYSICS, this, new DiscreteCoordinates(2, 6));
        }
    }

    /**
     * Get all the subrooms that constitute this area
     */
    public static List<Physics> subRooms() {
        List<Physics> subRooms = new LinkedList<>();
        subRooms.add(new Physics1());
        subRooms.add(new Physics2());
        subRooms.add(new Physics3());
        subRooms.add(new Physics4());
        subRooms.add(new Physics5());
        subRooms.add(new Physics6());
        subRooms.add(new Physics7());
        subRooms.add(new Physics8());
        subRooms.add(new Physics9());
        return subRooms;
    }
}
