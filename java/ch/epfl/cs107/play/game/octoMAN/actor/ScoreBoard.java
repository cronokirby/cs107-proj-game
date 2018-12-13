package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
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
    // static colors
    private static final Color gold = new Color(0xF6E319);
    private static final Color silver = new Color(0x9BA0B3);
    private static final Color bronze = new Color(0xBC5B0F);

    /**
     * Create a new score board at a certain position
     */
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

    /**
     * Load scores into the score board from a file.
     * @param fileName the fileName to read, as a series of integers
     */
    public void loadFile(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextFloat()) {
                scores.add(scanner.nextFloat());
            }
            updateScoreTexts();
        } catch (FileNotFoundException f) {
            // we could create the file, but we'll do that when we save our scores anyways
            System.out.println("Scoreboard couldn't load from " + fileName);
        }
    }

    public void saveToFile(String fileName) {
        try {
            FileWriter fstream = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(fstream);
            for (float s : scores) {
                out.write(s + "\n");
            }
            out.close();
        } catch (IOException io) {
            System.out.println("Couldn't save scoreboard to " + fileName);
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
            Color color;
            switch (i) {
                case 0:
                    color = gold;
                    break;
                case 1:
                    color = silver;
                    break;
                case 2:
                    color = bronze;
                    break;
                default:
                    color = Color.LIGHT_GRAY;
                    break;
            }
            TextGraphics g = new TextGraphics(txt, 1.f, color);
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
