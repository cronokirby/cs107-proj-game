package ch.epfl.cs107.play.game.octoMAN;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.octoMAN.actor.Timer;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.awt.*;

/**
 * Represents a screen displaying at the end of the OCTO game
 */
public class EndScreen implements Game {
    /// The window for this game
    private Window window;
    /// The timer associated with this end screen
    private Timer timer;
    /// True if the player wants to leave the end screen
    private boolean leaveScreen;
    /// The congratulation message to display
    private Graphics congrats;
    /// The message display the final time text
    private Graphics finalTimePrompt;
    /// The final time display
    private Graphics finalTime;

    /**
     * Set the timer this uses to something.
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    @Override
    public String getTitle() {
        return "OctoMAN";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        this.window = window;
        window.setRelativeTransform(Transform.I.scaled(22.f));
        TextGraphics congratsText = new TextGraphics("Congratulations!", 2.f, Color.WHITE);
        congrats = new GraphicsEntity(new Vector(-8.5f, 5), congratsText);
        TextGraphics promptText = new TextGraphics("Final Time:", 1.5f, Color.WHITE);
        finalTimePrompt = new GraphicsEntity(new Vector(-4, 2), promptText);
        TextGraphics finalTimeText = new TextGraphics(timer.display(), 1.5f, Color.WHITE);
        finalTime = new GraphicsEntity(new Vector(-3f, 0), finalTimeText);
        return true;
    }

    /// Return true if the end screen is finished
    public boolean isFinished() {
        return leaveScreen;
    }

    @Override
    public void update(float deltaTime) {
        congrats.draw(window);
        finalTimePrompt.draw(window);
        finalTime.draw(window);
        if (window.getKeyboard().get(Keyboard.L).isPressed()) {
            leaveScreen = true;
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
