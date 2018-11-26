package ch.epfl.cs107.play.game.octoMAN.actor;


import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a set of animations for cardinal orientations,
 * that can be cycled.
 */
public class Animation {
    /// The sprite cycles for each direction
    Map<Orientation, List<Sprite>> spriteMap;
    /// The current direction
    private Orientation orientation;
    /// The current position in a cycle
    private int cyclePosition = 0;

    /**
     * Create a new Animation from a list of sprites for each direction.
     * The first sprite is both the first sprite of an animation in
     * a direction, and also the standby sprite for that orientation.
     *
     * No deep copy of the map is done, so take care not to modify
     * the map unless you mean to.
     */
    public Animation(Map<Orientation, List<Sprite>> spriteMap) {
        this.spriteMap = spriteMap;
    }

    /**
     * Create an animation given the title of an animation sheet,
     * where each column represents an orientation, and each row
     * represents the different cycles.
     *
     * The sheet must be in DOWN, LEFT, UP, RIGHT order
     * @param spriteWidth how wide this sprite is relative to a 16px tile
     * @param spriteHeight how tall this sprite is relative to a 16px tile
     * @param pixelWidth the width of this sprite in pixels
     * @param pixelHeight the height of this sprite in pixels
     * @param cycleCount how many frames are in a cycle of animation
     * @param repeat how many times to repeat a frame per animation cycle >= 1
     */
    public Animation(String animationSheet, Positionable parent, Orientation defaultDirection,
                     float spriteWidth, float spriteHeight,
                     int pixelWidth, int pixelHeight,
                     int cycleCount, int repeat) {
        spriteMap = new HashMap<>();
        Orientation[] orientations = Orientation.values();
        for (Orientation o : orientations) {
            // We want constant indexing
            List<Sprite> arr = new ArrayList<>();
            spriteMap.put(o, arr);
        }
        for (int row = 0; row < cycleCount; ++row) {
            for (int col = 0; col < orientations.length; ++col) {
                Orientation o = orientations[col];
                RegionOfInterest roi = new RegionOfInterest(
                        col * pixelWidth, row * pixelHeight, pixelWidth, pixelHeight
                );
                Sprite sprite = new Sprite(
                        animationSheet, spriteWidth, spriteHeight, parent, roi, Vector.ZERO
                );
                for (int i = 0; i < repeat; ++i) {
                    spriteMap.get(o).add(sprite);
                }
            }
        }
        resetOrientation(defaultDirection);
    }

    /**
     * Updates the current position in the animation cycle
     */
    public void updateCycle() {
        int cycleLength = spriteMap.get(orientation).size();
        ++cyclePosition;
        if (cyclePosition >= cycleLength) {
            cyclePosition = 0;
        }
    }

    /**
     * Resets the orientation and cycle position of this animation
     */
    public void resetOrientation(Orientation orientation) {
        this.orientation = orientation;
        this.cyclePosition = 0;
    }

    /**
     * Get the current sprite in the animation
     */
    public Sprite getSprite() {
        return spriteMap.get(orientation).get(cyclePosition);
    }
}
