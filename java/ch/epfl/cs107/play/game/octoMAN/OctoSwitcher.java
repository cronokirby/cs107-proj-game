package ch.epfl.cs107.play.game.octoMAN;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.octoMAN.actor.ScoreBoard;
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
    /// The title screen before the main game
    private TitleScreen titleScreen;
    /// The main game we can play
    private OctoGame octoGame;
    /// The end screen we can reach
    private EndScreen endScreen;

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        this.window = window;
        this.fileSystem = fileSystem;
        ScoreBoard board = new ScoreBoard();
        titleScreen = new TitleScreen(board);
        titleScreen.begin(window, fileSystem);
        gameType = OctoGameType.TitleScreen;
        octoGame = new OctoGame(board);
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
            case TitleScreen:
                titleScreen.update(deltaTime);
                if (titleScreen.isFinished()) {
                    gameType = OctoGameType.MainGame;
                    octoGame.begin(window, fileSystem);
                }
                break;
            case EndScreen:
                endScreen.update(deltaTime);
                if (endScreen.isFinished()) {
                    gameType = OctoGameType.TitleScreen;
                    // no need to begin, since the title screen is the first one
                }
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
