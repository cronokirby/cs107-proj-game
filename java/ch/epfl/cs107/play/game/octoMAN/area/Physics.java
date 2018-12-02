package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.Boulder;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

import java.util.LinkedList;
import java.util.List;

public class Physics extends OctoArea {
    /// The title of this subroom
    private String title;
    /// The positions of boulders in this room
    private List<DiscreteCoordinates> boulderPositions;

    private Physics(String title, List<DiscreteCoordinates> boulderPositions) {
        this.title = title;
        this.boulderPositions = boulderPositions;
    }

    public static List<Physics> subRooms() {
        List<Physics> subRooms = new LinkedList<>();
        // Sub Room 1
        List<DiscreteCoordinates> boulders1 = new LinkedList<>();
        for (int x = 1; x <= 7; ++x) {
            boulders1.add(new DiscreteCoordinates(x, 4));
        }
        subRooms.add(new Physics("Physics1", boulders1));
        return subRooms;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        for (DiscreteCoordinates coordinate : boulderPositions) {
            new Boulder(this, coordinate);
        }
        return superOK;
    }
}
