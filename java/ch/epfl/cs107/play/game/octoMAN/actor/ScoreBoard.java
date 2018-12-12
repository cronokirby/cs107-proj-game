package ch.epfl.cs107.play.game.octoMAN.actor;

import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a score board capable of saving and displaying
 * finish times
 */
public class ScoreBoard {
    /// Implemented via a tree set in order to preserve orders
    Set<Float> scores;

    public ScoreBoard() {
        scores = new TreeSet<>();
    }

    /**
     * Add a new score, in seconds, to the list of scores
     */
    public void addScore(float score) {
        scores.add(score);
    }

    public void printScores() {
        int i = 1;
        for (float s : scores) {
            System.out.print(i + ":" + s + ", ");
        }
        System.out.println();
    }
}
