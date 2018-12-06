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
            Logic plate1 = new PressurePlate(0.05f, this, new DiscreteCoordinates(6, 9));
            Logic plate2 = new PressurePlate(0.05f, this, new DiscreteCoordinates(2, 9));
            new Potion(this, new DiscreteCoordinates(4, 4));
            MultipleAnd allSwitches = new MultipleAnd(plate1, plate2);
            new StickyDoor(
                    allSwitches, "LifeSciences9", new DiscreteCoordinates(2, 1),
                    this, new DiscreteCoordinates(4, 10)
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
            new StandardDoor(
                    Logic.TRUE, "LifeSciences1", new DiscreteCoordinates(4, 9),
                    this, new DiscreteCoordinates(2, 0)
            );
        }

        @Override
        protected boolean reset() {
            return false;
        }
    }


    public static List<LifeSciences> subRooms() {
        List<LifeSciences> subRooms = new LinkedList<>();
        subRooms.add(new LifeSciences1());
        subRooms.add(new LifeSciences9());
        return subRooms;
    }
}
