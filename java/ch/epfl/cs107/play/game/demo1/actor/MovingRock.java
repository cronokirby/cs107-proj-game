package ch.epfl.cs107.play.game.demo1.actor;

import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

/**
 * Represents a Rock with some text attached to it
 */
public class MovingRock extends GraphicsEntity {
    // We could use Graphics instead, but since this is private, it's ok
    private final TextGraphics text;

    /**
     * Create a new MovingRock
     * @param position The starting position for the rock
     * @param text The text that should be attached to the rock
     */
    public MovingRock(Vector position, String text) {
        super(position, new ImageGraphics(ResourcePath.getSprite("rock.3"),
                0.1f, 0.1f, null, Vector.ZERO, 1.0f, -Float.MAX_VALUE));
        this.text = new TextGraphics(text, 0.04f, Color.BLUE);
        this.text.setParent(this);
        this.text.setAnchor(new Vector(0f, 0.12f));
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        text.draw(canvas);
    }

    @Override
    public void update(float deltaTime) {
        // Move down at a rate of X screens per second
        Vector delta = new Vector(0.4f, 0.4f).mul(deltaTime);
        setCurrentPosition(getPosition().sub(delta));
    }
}
