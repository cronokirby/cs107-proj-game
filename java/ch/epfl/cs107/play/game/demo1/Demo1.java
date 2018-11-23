package ch.epfl.cs107.play.game.demo1;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.awt.*;

/**
 * A simple game with just a moving rock and circle
 */
public class Demo1 implements Game {
    /// The window the game renders to
    private Window window;
    /// The file system the game uses
    private FileSystem filesystem;
    /// The circle in the game window
    private Actor circle;
    private final float circleRadius = 0.2f;
    /// The moving rock in the game window
    private Actor rock;
    /// The boom text to display
    private TextGraphics boomText;

    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public String getTitle() {
        return "Demo1";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        ShapeGraphics shape = new ShapeGraphics(
                new Circle(circleRadius), null, Color.RED, 0.005f
        );
        this.circle = new GraphicsEntity(Vector.ZERO, shape);
        this.rock = new MovingRock(new Vector(0.3f, 0.3f), "Hello!");
        this.boomText = new TextGraphics("BOOM!", 0.06f, Color.RED);
        boomText.setAnchor(new Vector(-0.1f, 0.0f));
        this.window = window;
        this.filesystem = fileSystem;
        return true;
    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = window.getKeyboard();
        Button downArrow = keyboard.get(Keyboard.DOWN);
        if (downArrow.isDown())	{
            rock.update(deltaTime);
        }
        circle.draw(window);
        rock.draw(window);

        if (circle.getPosition().sub(rock.getPosition()).getLength() < circleRadius) {
            boomText.draw(window);
        }
    }

    @Override
    public void end() {
    }
}
