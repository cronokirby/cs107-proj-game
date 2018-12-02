package ch.epfl.cs107.play.game.octoMAN;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.Door;
import ch.epfl.cs107.play.game.octoMAN.actor.Player;
import ch.epfl.cs107.play.game.octoMAN.area.CharacterSelect;
import ch.epfl.cs107.play.game.octoMAN.area.LevelSelect;
import ch.epfl.cs107.play.game.octoMAN.area.Physics;
import ch.epfl.cs107.play.game.octoMAN.area.TestArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class OctoGame extends AreaGame {
    /// The player for this game
    private Player player;

    @Override
    public String getTitle() {
        return "OctoMAN";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        Area starting = new CharacterSelect();
        addArea(starting);
        addArea(new LevelSelect());
        addArea(new TestArea());
        for (Area a : Physics.subRooms()) {
            addArea(a);
        }
        boolean areasOK = beginAreas();
        setCurrentArea(starting.getTitle(), false);
        // Initialising the player
        DiscreteCoordinates playerPos = new DiscreteCoordinates(5, 1);
        player = new Player(starting, "boy.1", Orientation.DOWN, playerPos);
        player.enterArea(starting, playerPos);
        return superOK && areasOK;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Door lastDoor = player.getLastDoor();
        if (lastDoor != null) {
            player.leaveCurrentArea();
            Area next = setCurrentArea(lastDoor.getDestinationArea(), false);
            player.enterArea(next, lastDoor.getDestinationPosition());
        }
    }

    @Override
    public int getFrameRate() {
        return 24;
    }
}
