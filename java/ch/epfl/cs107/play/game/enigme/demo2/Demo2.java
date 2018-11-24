package ch.epfl.cs107.play.game.enigme.demo2;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Demo2 extends AreaGame {
    @Override
    public int getFrameRate() {
        return 60;
    }

    @Override
    public String getTitle() {
        return "Demo2";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        super.begin(window, fileSystem);
        Room room0 = new Room("LevelSelector");
        addArea(room0);
        boolean room0OK = room0.begin(window, fileSystem);
        Room room1 = new Room("Level1");
        boolean room1OK = room1.begin(window, fileSystem);
        addArea(room1);
        setCurrentArea("LevelSelector", false);
        return room0OK && room1OK;
    }
}
