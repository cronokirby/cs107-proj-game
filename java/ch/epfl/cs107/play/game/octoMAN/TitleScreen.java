package ch.epfl.cs107.play.game.octoMAN;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.actor.Text;
import ch.epfl.cs107.play.game.octoMAN.actor.ScoreBoard;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.awt.*;

/**
 * Represents the title screen displaying at the start of the game
 */
public class TitleScreen implements Game {
    /// The window in this title screen
    private Window window;
    /// The score board in this title screen
    private ScoreBoard scoreBoard;
    /// Whether or not the player has pressed on new game
    private boolean startNewGame;
    /// Whether or not the player is selecting new game or the scoreboard
    private boolean onNewGame;
    /// The text for the title
    private Graphics title;
    /// The text for the new game prompt
    private GraphicsEntity newGamePrompt;
    /// The text for the scoreboard prompt
    private GraphicsEntity scoreboardPrompt;
    /// The cursor for new game
    private Sprite newGameCursor;
    /// The cursor for scoreboard
    private Sprite scoreboardCursor;

    /**
     * Construct a new title screen with access to a certain score board
     */
    public TitleScreen(ScoreBoard board) {
        scoreBoard = board;
    }

    @Override
    public String getTitle() {
        return "OctoMAN";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        this.window = window;
        window.setRelativeTransform(Transform.I.scaled(22.f));
        TextGraphics titleText = new TextGraphics("8CT8 MAN", 3.f, Color.WHITE);
        title = new GraphicsEntity(new Vector(-8, 5), titleText);
        TextGraphics newGameText = new TextGraphics("New Game", 1.3f, Color.WHITE);
        newGamePrompt = new GraphicsEntity(new Vector(-3.5f, -2), newGameText);
        newGameCursor = new Sprite("ghost.1", 1.f, 1.f, newGamePrompt);
        newGameCursor.setAnchor(new Vector(-1.2f, 0));
        TextGraphics scoreboardText = new TextGraphics("Score Board", 1.3f, Color.WHITE);
        scoreboardPrompt = new GraphicsEntity(new Vector(-3.5f, -4), scoreboardText);
        scoreboardCursor = new Sprite("ghost.1", 1.f, 1.f, scoreboardPrompt);
        scoreboardCursor.setAnchor(new Vector(-1.2f, 0));
        onNewGame = true;
        return true;
    }

    /**
     * Return true if the title screen is finished.
     * This will be true once the player has selected "New Game"
     */
    public boolean isFinished() {
        return startNewGame;
    }

    /// Return true if key is pressed
    private boolean getKeyPressed(int code) {
        return window.getKeyboard().get(code).isPressed();
    }

    @Override
    public void update(float deltaTime) {
        title.draw(window);
        newGamePrompt.draw(window);
        scoreboardPrompt.draw(window);
        if (onNewGame) {
            newGameCursor.draw(window);
            startNewGame = getKeyPressed(Keyboard.L);
        } else {
            scoreboardCursor.draw(window);
            if (getKeyPressed(Keyboard.L)) {
                scoreBoard.printScores();
            }
        }
        boolean upPressed = getKeyPressed(Keyboard.W) || getKeyPressed(Keyboard.UP);
        boolean downPressed = getKeyPressed(Keyboard.S) || getKeyPressed(Keyboard.DOWN);
        if (upPressed || downPressed) {
            onNewGame = !onNewGame;
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
