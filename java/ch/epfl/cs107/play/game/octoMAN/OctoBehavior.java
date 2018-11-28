package ch.epfl.cs107.play.game.octoMAN;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;


/**
 * Represents the behavior of areas in this game.
 */
public class OctoBehavior extends AreaBehavior {
    /**
     * Represents the types of cells that exist in this game
     */
    public enum OctoCellType {
        WALL(0xFF_00_00_00),
        SLIPPERY(0xFF_00_FF_00),
        NORMAL(0xFF_FF_FF_FF),
        NULL(0);

        final int type;

        OctoCellType(int type) {
            this.type = type;
        }

        public static OctoCellType fromType(int type) {
            for (OctoCellType cellType : OctoCellType.values()) {
                if (cellType.type == type) {
                    return cellType;
                }
            }
            return NULL;
        }
    }

    /**
     * A cell wrapping OctoCellType
     */
    public class OctoCell extends Cell {
        /// The type of this cell
        public final OctoCellType type;

        private OctoCell(int x, int y, OctoCellType type) {
            super(x, y);
            this.type = type;
        }

        @Override
        protected boolean canEnter(Interactable entity) {
            switch (type) {
                case WALL:
                    return false;
                case NULL:
                    return false;
                default:
                    return true;
            }
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
            ((OctoInteractionVisitor)v).interactWith(this);
        }
    }

    /**
     * Construct a new behavior from a window and filename
     * @param window the window for this area
     * @param filename this is used to find the behavior map
     */
    public OctoBehavior(Window window, String filename) {
        super(window, filename);
        Image behaviorMap = getBehaviorMap();
        int width = behaviorMap.getWidth();
        int height = behaviorMap.getHeight();
        Cell[][] cells = getCells();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < width; ++y) {
                int type = behaviorMap.getRGB(height - 1 - y, x);
                cells[x][y] = new OctoCell(x, y, OctoCellType.fromType(type));
            }
        }
    }
}
