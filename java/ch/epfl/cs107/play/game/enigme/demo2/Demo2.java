package ch.epfl.cs107.play.game.enigme.demo2;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.demo2.actor.Demo2Player;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {
    /// The player actor for the game
    private Demo2Player player;
    /// The level selector room
    private Room room0;
    /// The empty room 1
    private Room room1;
    /// Whether or not the player is in Room 0
    private boolean inRoom0;

    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public String getTitle() {
        return "Demo2";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);
        room0 = new Room("LevelSelector");
        addArea(room0);
        boolean room0OK = room0.begin(window, fileSystem);
        room1 = new Room("Level1");
        boolean room1OK = room1.begin(window, fileSystem);
        addArea(room1);
        setCurrentArea("LevelSelector", false);
        DiscreteCoordinates position= new DiscreteCoordinates(5, 5);
        player = new Demo2Player(room0, Orientation.DOWN, position);
        player.enterArea(room0, position);
        inRoom0 = true;
        return room0OK && room1OK;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (player.isInDoor()) {
            player.toggleDoor(false);
            player.leaveCurrentArea();
            // We can forgo checking the exact current room returned,
            // since we're pretty sure these rooms exist.
            if (inRoom0) {
                // The order of this is crucial, otherwise the registration won't get purged
                setCurrentArea(room1.getTitle(), false);
                player.enterArea(room1, new DiscreteCoordinates(5, 2));
                inRoom0 = false;
            } else {
                setCurrentArea(room0.getTitle(), false);
                player.enterArea(room0, new DiscreteCoordinates(5, 5));
                inRoom0 = true;
            }
        }
    }
}
