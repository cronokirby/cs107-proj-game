package ch.epfl.cs107.play.game.octoMAN.area;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a sub room of the mathematics area
 */
public abstract class Mathematics extends SubRoom {
    private Mathematics(String title) {
        super(title);
    }

    private static class Mathematics1 extends Mathematics {
        private Mathematics1() {
            super("Mathematics1");
        }

        @Override
        protected void addActors() {
        }
    }

    /**
     * Get a list of all the sub rooms in this area
     */
    public List<Mathematics> subRooms() {
        List<Mathematics> subRooms = new LinkedList<>();
        subRooms.add(new Mathematics1());
        return subRooms;
    }
}
