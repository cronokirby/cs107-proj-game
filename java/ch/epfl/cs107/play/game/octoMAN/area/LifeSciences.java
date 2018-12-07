package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents an abstract version of a sub room in the life sciences area
 */
public abstract class LifeSciences extends OctoArea {
    /// The title of this sub room
    private String title;

    /**
     * Construct a new subroom from a title
     */
    private LifeSciences(String title) {
        this.title = title;
    }

    /**
     * Add all the necessary actors to this area
     */
    protected abstract void addActors();

    /**
     * Whether or not to reset the room
     */
    protected boolean reset() {
        return true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        addActors();
        return superOK;
    }

    @Override
    public boolean resume(Window window, FileSystem fileSystem) {
        if (reset()) {
            return begin(window, fileSystem);
        }
        else {
            return super.resume(window, fileSystem);
        }
    }

    private static class LifeSciences1 extends LifeSciences {
        public LifeSciences1() {
            super("LifeSciences1");
        }

        @Override
        protected void addActors() {
            new StandardDoor(
                    Logic.TRUE, "LevelSelect", new DiscreteCoordinates(11, 8),
                    this, new DiscreteCoordinates(4, 0)
            );
            Logic plate1 = PressurePlate.fast(this, new DiscreteCoordinates(6, 9));
            Logic plate2 = PressurePlate.fast(this, new DiscreteCoordinates(2, 9));
            new Potion(this, new DiscreteCoordinates(4, 4));
            MultipleAnd allSwitches = new MultipleAnd(plate1, plate2);
            new StickyDoor(
                    allSwitches, "LifeSciences2", new DiscreteCoordinates(6, 1),
                    this, new DiscreteCoordinates(4, 10)
            );
        }
    }

    private static class LifeSciences2 extends LifeSciences {
        public LifeSciences2() {
            super("LifeSciences2");
        }

        @Override
        protected void addActors() {
            new Potion(this, new DiscreteCoordinates(7, 3));
            new Potion(this, new DiscreteCoordinates(6, 4));
            Logic[] switches = new Logic[3];
            switches[0] = PressurePlate.fast(this, new DiscreteCoordinates(6, 5));
            switches[1] = PressurePlate.fast(this, new DiscreteCoordinates(2, 10));
            switches[2] = PressurePlate.fast(this, new DiscreteCoordinates(10, 10));
            Logic allSwitches = new MultipleAnd(switches);
            new StickyDoor(
                allSwitches, "LifeSciences3", new DiscreteCoordinates(6, 1),
                this, new DiscreteCoordinates(6, 11)
            );
        }
    }

    private static class LifeSciences3 extends LifeSciences {
        private LifeSciences3() {
            super("LifeSciences3");
        }

        @Override
        protected void addActors() {
            new Potion(this, new DiscreteCoordinates(7, 3));
            new Potion(this, new DiscreteCoordinates(6, 4));
            Logic[] switches = new Logic[3];
            switches[0] = PressurePlate.fast(this, new DiscreteCoordinates(6, 7));
            switches[1] = PressurePlate.fast(this, new DiscreteCoordinates(2, 10));
            switches[2] = PressurePlate.fast(this, new DiscreteCoordinates(10, 10));
            Logic allSwitches = new MultipleAnd(switches);
            new StickyDoor(
                allSwitches, "LifeSciences4", new DiscreteCoordinates(6, 1),
                this, new DiscreteCoordinates(6, 11)
            );
        }
    }

    private static class LifeSciences4 extends LifeSciences {
        public LifeSciences4() {
            super("LifeSciences4");
        }

        @Override
        protected void addActors() {
            new Potion(this, new DiscreteCoordinates(7, 3));
            new Potion(this, new DiscreteCoordinates(6, 4));
            new Potion(this, new DiscreteCoordinates(5, 3));
            Logic[] switches = new Logic[4];
            switches[0] = PressurePlate.fast(this, new DiscreteCoordinates(4, 5));
            switches[1] = PressurePlate.fast(this, new DiscreteCoordinates(2, 11));
            switches[2] = PressurePlate.fast(this, new DiscreteCoordinates(10, 11));
            switches[3] = PressurePlate.fast(this, new DiscreteCoordinates(8, 5));
            Logic allSwitches = new MultipleAnd(switches);
            new StickyDoor(
                allSwitches, "LifeSciences9", new DiscreteCoordinates(2, 1),
                this, new DiscreteCoordinates(6, 13)
            );
        }
    }

    private static class LifeSciences9 extends LifeSciences {
        public LifeSciences9() {
            super("LifeSciences9");
        }

        @Override
        protected void addActors() {
            new Orb(Orb.Type.LIFESCIENCES, this, new DiscreteCoordinates(2, 6));
        }

        @Override
        protected boolean reset() {
            return false;
        }
    }


    public static List<LifeSciences> subRooms() {
        List<LifeSciences> subRooms = new LinkedList<>();
        subRooms.add(new LifeSciences1());
        subRooms.add(new LifeSciences2());
        subRooms.add(new LifeSciences3());
        subRooms.add(new LifeSciences4());
        subRooms.add(new LifeSciences9());
        return subRooms;
    }
}
