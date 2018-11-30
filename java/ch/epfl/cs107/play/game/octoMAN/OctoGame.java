package ch.epfl.cs107.play.game.octoMAN;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.Player;
import ch.epfl.cs107.play.game.octoMAN.area.TestArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class OctoGame extends AreaGame {

    @Override
    public String getTitle() {
        return "OctoMAN";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        Area starting = new TestArea();
        addArea(starting);
        boolean areasOK = beginAreas();
        setCurrentArea(starting.getTitle(), false);
        // Initialising the player
        DiscreteCoordinates playerPos = new DiscreteCoordinates(5, 10);
        Player player = new Player(starting, "boy.1", Orientation.DOWN, playerPos);
        player.enterArea(starting, playerPos);
        return superOK && areasOK;
    }

    @Override
    public int getFrameRate() {
        return 24;
    }
}
