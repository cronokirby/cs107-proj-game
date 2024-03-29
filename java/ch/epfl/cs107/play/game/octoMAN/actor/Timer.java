package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

/**
 * Represents a time displaying the current time elapsed in a game
 */
public class Timer extends AnchoredEntity {
    /// The time elapsed since this timer has started in seconds
    private float elapsed;

    public Timer(Vector position) {
        super(position);
    }

    private static String pad0(int x) {
        String input = Integer.toString(x);
        if (input.length() == 1) {
            return "0" + input;
        } else {
            return input;
        }
    }

    /**
     * Format a floating point number of seconds in a nice way
     * @return the text in h:mm:ss format
     */
    public static String formatTime(float f) {
        int acc = (int) f;
        String secondRest = pad0(acc % 60);
        acc /= 60;
        String minuteRest = pad0(acc % 60);
        acc /= 60;
        int hours = acc % 60;
        return hours + ":" + minuteRest + ":" + secondRest;
    }

    public String display() {
        return Timer.formatTime(elapsed);
    }

    /**
     * Have this timer add its current elapsed time to the board
     */
    public void addTo(ScoreBoard board) {
        board.addScore(elapsed);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        elapsed += deltaTime;
    }

    @Override
    public void draw(Canvas canvas) {
        TextGraphics g = new TextGraphics(display(), 1.f, Color.WHITE);
        g.setParent(this);
        g.draw(canvas);
    }
}
