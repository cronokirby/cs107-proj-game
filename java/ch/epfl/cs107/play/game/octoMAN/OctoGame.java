package ch.epfl.cs107.play.game.octoMAN;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.octoMAN.actor.*;
import ch.epfl.cs107.play.game.octoMAN.area.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class OctoGame extends AreaGame {
    /// The player for this game
    private Player player;
    /// The hud for this game
    private Hud hud;
    /// The scoreboard for this game
    private ScoreBoard scoreBoard;

    /**
     * Construct a new octo game with a given score board
     */
    public OctoGame(ScoreBoard board) {
        scoreBoard = board;
    }

    @Override
    public String getTitle() {
        return "OctoMAN";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        hud = new Hud();
        OctoArea starting = new CharacterSelect();
        addArea(starting);
        addArea(new LevelSelect(hud.getHolder()));
        addArea(new FinalArea());
        addArea(new TestArea());
        for (OctoArea a : Physics.subRooms()) {
            addArea(a);
        }
        for (OctoArea a : Mathematics.subRooms()) {
            addArea(a);
        }
        for (OctoArea a : LifeSciences.subRooms()) {
            addArea(a);
        }
        for (OctoArea a : Electricity.subRooms()) {
            addArea(a);
        }
        for (OctoArea a : Chemistry.subRooms()) {
            addArea(a);
        }
        for (OctoArea a : Microtech.subRooms()) {
            addArea(a);
        }
        for (OctoArea a : Computer.subRooms()) {
            addArea(a);
        }
        addArea(new Environment());
        setCurrentArea(starting.getTitle(), false);
        // Initialising the player
        DiscreteCoordinates playerPos = new DiscreteCoordinates(5, 1);
        player = new Player(hud, starting, "boy.1", Orientation.DOWN, playerPos);
        player.enterArea(starting, playerPos);
        hud.setAnchor(player);
        return superOK;
    }

    /**
     * Check whether or not we've reached the end of the game
     * @return true if we've reached the end of the game
     */
    public boolean finished() {
        return player.isFinished();
    }

    /**
     * Get the timer running in this game
     */
    public Timer getTimer() {
        return hud.getTimer();
    }

    @Override
    public void update(float deltaTime) {
       super.update(deltaTime);
        Portal lastPortal = player.getLastPortal();
        if (lastPortal != null) {
            Area next = setCurrentArea(lastPortal.getDestinationArea(), false);
            player.enterArea(next, lastPortal.getDestinationPosition());
        }
        /// We avoid a frame of flashing when the player transitions
        if (lastPortal == null) {
            hud.update(deltaTime);
            hud.draw(getWindow());
        }
        if (player.isFinished()) {
            hud.getTimer().addTo(scoreBoard);
        }
    }

    @Override
    public int getFrameRate() {
        return 24;
    }
}
