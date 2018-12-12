package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a score board capable of saving and displaying
 * finish times
 */
public class ScoreBoard extends Entity {
    /// Implemented via a tree set in order to preserve orders
    private Set<Float> scores;
    /// The title displaying above the scores
    private Graphics title;
    /// An array of TextGraphics for each score
    /// The TextGraphics can be null, indicating new score
    private TextGraphics[] scoreTexts;

    public ScoreBoard(Vector position) {
        super(position);
        scores = new TreeSet<>();
        TextGraphics titleText = new TextGraphics("Best Finish Times:", 1.5f, Color.WHITE);
        titleText.setParent(this);
        titleText.setAnchor(new Vector(0, 2));
        title = titleText;
        scoreTexts = new TextGraphics[10];
        for (int i = 0; i < scoreTexts.length; ++i) {
            scoreTexts[i] = null;
        }
    }


    private void updateScoreTexts() {
        int i = 0;
        for (float s : scores) {
            // we only want to take the best 9 scores
            if (i > 9) {
                break;
            }
            String txt = "#" + (i + 1) + "                  " + Timer.formatTime(s);
            TextGraphics g = new TextGraphics(txt, 1.f, Color.WHITE);
            g.setParent(this);
            g.setAnchor(new Vector(1.f, -(i + 1)));
            scoreTexts[i] = g;
            ++i;
        }
    }

    /**
     * Add a new score, in seconds, to the list of scores
     */
    public void addScore(float score) {
        scores.add(score);
        updateScoreTexts();
    }

    @Override
    public void draw(Canvas canvas) {
        title.draw(canvas);
        for (Graphics g : scoreTexts) {
            if (g != null) {
                g.draw(canvas);
            }
        }
    }
}
