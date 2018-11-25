package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * A class of behavior for the Enigme game
 */
public class EnigmeBehavior extends AreaBehavior {
    /**
     * Represents the type of cell in the Enigme game
     */
    public enum EnigmeCellType {
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

        EnigmeCellType(int type) {
            this.type = type;
        }

        /**
         * Construct a Cell Type from an integer code
         * @param type the RBG code of the type
         * @return NULL if not a recognised code, otherwise an enum branch
         */
        public static EnigmeCellType toType(int type) {
            for (EnigmeCellType cellType : EnigmeCellType.values()) {
                if (cellType.type == type) {
                    return cellType;
                }
            }
            return NULL;
        }
    }

    /**
     * A Cell wrapping over EnigmeCellType
     */
    public class EnigmeCell extends Cell {
        /// The type of this cell
        public final EnigmeCellType type;

        /**
         * Construct a cell in a certain position, of a certain type
         * @param x the x position of this cell
         * @param y the y position of this cell
         * @param type what type of cell this is
         */
        private EnigmeCell(int x, int y, EnigmeCellType type) {
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

        @Override
        public void acceptInteraction(AreaInteractionVisitor v) {
            ((EnigmeInteractionVisitor)v).interactWith(this);
        }
    }

    public EnigmeBehavior(Window window, String filename) {
        super(window, filename);
        Image behaviorMap = getBehaviorMap();
        int width = behaviorMap.getWidth();
        int height = behaviorMap.getHeight();
        Cell[][] cells = getCells();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int type = behaviorMap.getRGB(height - 1 - y, x);
                cells[x][y] = new EnigmeCell(x, y, EnigmeCellType.toType(type));
            }
        }
    }
}
