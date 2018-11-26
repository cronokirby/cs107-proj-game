package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


/**
 * Enigme is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {
    /// The player is a concept of RPG games
    private EnigmePlayer player;


    /// Enigme implements Playable

    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        /// We want fast indexing
        Area starting = new LevelSelector();
        addArea(starting);
        addArea(new Level1());
        addArea(new Level2());
        addArea(new Level3());
        boolean roomsOK = beginAreas();
        setCurrentArea(starting.getTitle(), false);
        // Initialising the player
        DiscreteCoordinates playerPOS = new DiscreteCoordinates(5, 5);
        player = new EnigmePlayer(starting, Orientation.DOWN, playerPOS, window.getKeyboard());
        player.enterArea(starting, playerPOS);
        return superOK && roomsOK;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Door lastDoor = player.passedDoor();
        if (lastDoor != null) {
            player.leaveCurrentArea();
            // We assume that this is successful
            // This will fail if the door is badly programmed.
            Area next = setCurrentArea(lastDoor.getDestinationArea(), false);
            player.enterArea(next, lastDoor.getDestinationPosition());
        }
    }

    @Override
    public int getFrameRate() {
        return 24;
    }
}
