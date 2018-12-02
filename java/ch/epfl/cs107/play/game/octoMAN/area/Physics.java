package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.Boulder;
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
    /// The positions of boulders in this room
    private List<DiscreteCoordinates> boulderPositions;

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
                    helpText, this,
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
            new StandardDoor(Logic.FALSE, "", null, this, new DiscreteCoordinates(2, 8));
        }
    }

    /**
     * Get all the subrooms that constitute this area
     */
    public static List<Physics> subRooms() {
        List<Physics> subRooms = new LinkedList<>();
        subRooms.add(new Physics1());
        subRooms.add(new Physics2());
        return subRooms;
    }
}
