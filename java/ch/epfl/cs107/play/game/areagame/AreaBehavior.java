package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
        /// A set of entities interacting with this cell
        private Set<Interactable> interactables;

        public Cell(int x, int y) {
            this.coordinates = new DiscreteCoordinates(x, y);
            this.interactables = new HashSet<>();
        }

        @Override
        public List<DiscreteCoordinates> getCurrentCells() {
            LinkedList<DiscreteCoordinates> ret = new LinkedList<>();
            ret.add(coordinates);
            return ret;
        }


        /**
         * Add an entity to this cell
         */
        private void enter(Interactable entity) {
            interactables.add(entity);
        }

        /**
         * Remove an entity from this cell
         */
        private void leave(Interactable entity) {
            interactables.remove(entity);
        }

        /**
         * Check whether or not an interactable entity can enter this cell
         * @param entity the interactable to check
         * @return true if the entity can enter
         */
        abstract protected boolean canEnter(Interactable entity);

        /**
         * Check whether or not an interactable entity can leave this cell
         * @param entity the interactable to check
         * @return true if the entity can leave
         */
        abstract protected boolean canLeave(Interactable entity);
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
     * Get the cell at a certain coordinate
     * @param coordinate the coordinate of the cell to fetch
     * @return the cell living at that coordinate
     */
    private Cell getCell(DiscreteCoordinates coordinate) {
        return cells[coordinate.x][coordinate.y];
    }


    /**
     * Have an entity leave a list of cells
     * @param entity the departing entity
     * @param coordinates all the coordinates the entity will leave
     */
    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for (DiscreteCoordinates coordinate : coordinates) {
            getCell(coordinate).leave(entity);
        }
    }

    /**
     * Have an entity enter a list of cells
     * @param entity the entering entity
     * @param coordinates all the coordinates the entity will enter
     */
    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for (DiscreteCoordinates coordinate : coordinates) {
            getCell(coordinate).enter(entity);
        }
    }


    /**
     * Check whether or not an entity can leave every one of a list of coordinates
     * @param entity the entity to check
     * @param coordinates the list of coordinates to leave
     * @return true if the entity can leave every one of those coordinates
     */
    public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for (DiscreteCoordinates coordinate : coordinates) {
            if (!getCell(coordinate).canLeave(entity)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check whether or not an entity can enter every one of a list of coordinates
     * @param entity the entity to check
     * @param coordinates the list of coordinates to enter
     * @return true if the entity can enter every one of those coordinates
     */
    public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for (DiscreteCoordinates coordinate : coordinates) {
            if (!getCell(coordinate).canEnter(entity)) {
                return false;
            }
        }
        return true;
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
