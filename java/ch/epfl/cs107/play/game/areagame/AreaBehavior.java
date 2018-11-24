package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

import java.util.LinkedList;
import java.util.List;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior {
    // The image representing the behavior map
    private Image behaviorMap;
    // We could make these public instead of having getters,
    // but getters help with inheritance.
    private final int width, height;
    // An array of cells representing the behavior
    private final Cell[][] cells;

    /**
     * Each game may have its own cell contents
     */
    public abstract class Cell implements Interactable {
        private DiscreteCoordinates coordinates;

        public Cell(int x, int y) {
            this.coordinates = new DiscreteCoordinates(x, y);
        }

        @Override
        public List<DiscreteCoordinates> getCurrentCells() {
            LinkedList<DiscreteCoordinates> ret = new LinkedList<>();
            ret.add(coordinates);
            return ret;
        }
    }

    /**
     * Default AreaBehavior Constructor
     * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public AreaBehavior(Window window, String fileName){
        behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
        width = behaviorMap.getWidth();
        height = behaviorMap.getHeight();
        cells = new Cell[width][height];
    }


    /**
     * This is useful to let subclasses toy around
     * with the cell grid.
     * @return a (mutable) reference to the cell grid
     */
    protected Cell[][] getCells() {
       return cells;
    }

    /**
     * @return the width of the behavior map
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height of the behavior map
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the image associated to this behavior
     */
    public Image getBehaviorMap() {
        return behaviorMap;
    }
}
