package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * A class of behaviors for the second demo game
 */
public class Demo2Behavior extends AreaBehavior {
    /**
     * Represents the type of cell in the 2nd demo game
     */
    public enum Demo2CellType {
        NULL(0),
        // Black RGB Code
        WALL(-16777216),
        // Red RGB Code
        DOOR(-65536),
        // Blue RGB Code
        WATER(-16776961),
        INDOOR_WALKABLE(-1),
        OUTDOOR_WALKABLE(-14112955);

        final int type;

        Demo2CellType(int type) {
            this.type = type;
        }

        /**
         * Construct a Cell Type from an integer code
         * @param type the RBG code of the type
         * @return NULL if not a recognised code, otherwise an enum branch
         */
        public static Demo2CellType toType(int type) {
            for (Demo2CellType cellType : Demo2CellType.values()) {
                if (cellType.type == type) {
                    return cellType;
                }
            }
            return NULL;
        }
    }

    /**
     * A cell wrapping over Demo2CellType
     */
    public class Demo2Cell extends Cell {
        // The type of this cell
        public final Demo2CellType type;

        /**
         * Construct a cell in a certain position, of a certain type
         * @param x the x position of this cell
         * @param y the y position of this cell
         * @param type what type of cell this is
         */
        private Demo2Cell(int x, int y, Demo2CellType type) {
            super(x, y);
            this.type = type;
        }

        // Cell behavior

        @Override
        protected boolean canEnter(Interactable entity) {
            if (super.canEnter(entity)) {
                switch (type) {
                    case NULL:
                        return false;
                    case WALL:
                        return false;
                    default:
                        return true;
                }
            }
            return false;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return super.canLeave(entity);
        }

        @Override
        public boolean takeCellSpace() {
            return false;
        }

        @Override
        public boolean isViewInteractable() {
            return false;
        }

        @Override
        public boolean isCellInteractable() {
            return true;
        }

        // This is needed for compatability with later parts
        @Override
        public void acceptInteraction(AreaInteractionVisitor v) {

        }
    }

    public Demo2Behavior(Window window, String filename) {
        super(window, filename);
        Image behaviorMap = getBehaviorMap();
        int width = behaviorMap.getWidth();
        int height = behaviorMap.getHeight();
        Cell[][] cells = getCells();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int type = behaviorMap.getRGB(height - 1 - y, x);
                cells[x][y] = new Demo2Cell(x, y, Demo2CellType.toType(type));
            }
        }
    }

    /**
     * This is a dirty hack to check if a coordinate is a door
     * @param position the position to check
     * @return true if the position is a door
     */
    public boolean isDoor(DiscreteCoordinates position) {
        int x = position.x;
        int y = position.y;
        int type = getBehaviorMap().getRGB(getHeight() - 1 - y, x);
        return Demo2CellType.toType(type).equals(Demo2CellType.DOOR);
    }
}
