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

    @Override
    public void draw(Canvas canvas) {
        TextGraphics g = new TextGraphics("1:00:15", 1.f, Color.WHITE);
        g.setParent(this);
        g.draw(canvas);
    }
}
