package ch.epfl.cs107.play.game.octoMAN;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.OrbHolder;
import ch.epfl.cs107.play.game.octoMAN.actor.Player;
import ch.epfl.cs107.play.game.octoMAN.actor.Portal;
import ch.epfl.cs107.play.game.octoMAN.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class OctoGame extends AreaGame {
    /// The player for this game
    private Player player;
    /// The orb holder for this game
    private OrbHolder holder;

    @Override
    public String getTitle() {
        return "OctoMAN";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        OctoArea starting = new CharacterSelect();
        addArea(starting);
        addArea(new LevelSelect());
        addArea(new TestArea());
        for (OctoArea a : Physics.subRooms()) {
            addArea(a);
        }
        addArea(new Environment());
        boolean areasOK = beginAreas();
        setCurrentArea(starting.getTitle(), false);

        holder = new OrbHolder(new Vector(-11f, 9f));
        // Initialising the player
        DiscreteCoordinates playerPos = new DiscreteCoordinates(5, 1);
        player = new Player(holder, starting, "boy.1", Orientation.DOWN, playerPos);
        player.enterArea(starting, playerPos);
        holder.setAnchor(player);
        return superOK && areasOK;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Portal lastPortal = player.getLastPortal();
        if (lastPortal != null) {
            player.leaveCurrentArea();
            Area next = setCurrentArea(lastPortal.getDestinationArea(), false);
            player.enterArea(next, lastPortal.getDestinationPosition());
        }
    }

    @Override
    public int getFrameRate() {
        return 24;
    }
}
