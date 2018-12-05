package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.octoMAN.actor.Bird;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a range of coordinates
 */
public class BirdFactory {
    /// The bottom left x coordinate
    private int botX;
    /// The bottom left y coordinate
    private int botY;
    /// The top right x coordinate (inclusive)
    private int topX;
    /// The top right y coordinate (inclusive)
    private int topY;
    /// The name of the bird to spawn
    private String birdName;
    /// How rare birds area
    private int rarity;

    /**
     * Create a new range from a set of coordinates
     * @param botX the bottom left x coordinate
     * @param botY the bottom left y coordinate
     * @param topX the top right x coordinate
     * @param topY the top right y coordinate
     */
    public BirdFactory(int botX, int botY, int topX, int topY, String birdName, int rarity) {
        this.botX = botX;
        this.botY = botY;
        this.topX = topX;
        this.topY = topY;
        this.birdName = birdName;
        this.rarity = rarity;
    }

    private List<DiscreteCoordinates> sample() {
        double cutoff = 1.0 / rarity;
        List<DiscreteCoordinates> acc = new LinkedList<>();
        for (int x = botX; x <= topX; ++x) {
            for (int y = botY; y <= topY; ++y) {
                if (Math.random() < cutoff) {
                    acc.add(new DiscreteCoordinates(x, y));
                }
            }
        }
        return acc;
    }

    public void spawnBirds(Area area) {
        for (DiscreteCoordinates position : sample()) {
            new Bird(birdName, area, position);
        }
    }
}
