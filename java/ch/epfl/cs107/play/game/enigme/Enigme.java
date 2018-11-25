package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;
import org.omg.PortableInterceptor.DISCARDING;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Enigme is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {
    /// The player is a concept of RPG games


    /// Enigme implements Playable

    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        /// We want fast indexing
        List<Area> areas = new ArrayList<>();
        areas.add(new EnigmeArea("LevelSelector"));
        areas.add(new EnigmeArea("Level1"));
        areas.add(new EnigmeArea( "Level2"));
        areas.add(new EnigmeArea("Level3"));
        boolean roomsOK = true;
        for (Area area : areas) {
            boolean thisRoomOK = area.begin(window, fileSystem);
            addArea(area);
            roomsOK = roomsOK && thisRoomOK;
        }
        Area starting = areas.get(0);
        setCurrentArea(starting.getTitle(), false);
        // Initialising the player
        DiscreteCoordinates playerPOS = new DiscreteCoordinates(5, 5);
        EnigmePlayer player = new EnigmePlayer(starting, Orientation.DOWN, playerPOS, window.getKeyboard());
        player.enterArea(starting, playerPOS);
        return superOK && roomsOK;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public int getFrameRate() {
        return 24;
    }
}
