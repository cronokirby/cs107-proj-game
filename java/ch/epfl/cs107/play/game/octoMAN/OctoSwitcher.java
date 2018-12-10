package ch.epfl.cs107.play.game.octoMAN;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

/**
 * Represents the main game that switches between different
 * sub games, e.g. title screens and main area games
 */
public class OctoSwitcher implements Game {
    /// The current type of game we're playing
    private OctoGameType gameType;
    /// The window to display this game in
    private Window window;
    /// The filesystem for this game
    private FileSystem fileSystem;
    /// The main game we can play
    private OctoGame octoGame;
    /// The end screen we can reach
    private EndScreen endScreen;

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        this.window = window;
        this.fileSystem = fileSystem;
        octoGame = new OctoGame();
        gameType = OctoGameType.MainGame;
        octoGame.begin(window, fileSystem);
        endScreen = new EndScreen();
        return true;
    }

    @Override
    public String getTitle() {
        return "OctoMAN";
    }

    @Override
    public void update(float deltaTime) {
        switch (gameType) {
            case EndScreen:
                endScreen.update(deltaTime);
                break;
            case MainGame:
                octoGame.update(deltaTime);
                if (octoGame.finished()) {
                    gameType = OctoGameType.EndScreen;
                    endScreen.setTimer(octoGame.getTimer());
                    endScreen.begin(window, fileSystem);
                }
                break;
        }
    }

    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public void end() {
    }
}