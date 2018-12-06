package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.Orb;
import ch.epfl.cs107.play.game.octoMAN.actor.StandardDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
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
        return begin(window, fileSystem);
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
            new StandardDoor(
                    Logic.TRUE, "LifeSciences9", new DiscreteCoordinates(2, 1),
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
        }
    }


    public static List<LifeSciences> subRooms() {
        List<LifeSciences> subRooms = new LinkedList<>();
        subRooms.add(new LifeSciences1());
        subRooms.add(new LifeSciences9());
        return subRooms;
    }
}
