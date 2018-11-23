package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

import java.util.HashMap;
import java.util.Map;


/**
 * AreaGame is a type of Game displayed in a (MxN) Grid called Area
 * An AreaGame has multiple Areas
 */
abstract public class AreaGame implements Game {

    // Context objects
    private Window window;
    private FileSystem filesystem;
    // A map containing each area of the game
    private Map<String, Area> areas;
    // The current area we are in
    private Area currentArea;

    /**
     * Add an Area to the AreaGame list
     * @param a (Area): The area to add, not null
     */
    protected final void addArea(Area a) {
        areas.put(a.getTitle(), a);
    }

    /**
     * Setter for the current area: Select an Area in the list from its key
     * - the area is then begin or resume depending if the area is already started or not and if it is forced
     * @param key (String): Key of the Area to select, not null
     * @param forceBegin (boolean): force the key area to call begin even if it was already started
     * @return (Area): after setting it, return the new current area
     */
    protected final Area setCurrentArea(String key, boolean forceBegin) {
        // Save the state of the current area
        if (currentArea != null) {
            currentArea.suspend();
        }
        Area nextArea = areas.get(key);
        if (nextArea != null) {
            if (!nextArea.hasBegun() || forceBegin) {
                nextArea.begin(window, filesystem);
            } else {
                nextArea.resume(window, filesystem);
            }
            currentArea = nextArea;
        }
        return currentArea;
    }


    /**@return (Window) : the Graphic and Audio context*/
    protected final Window getWindow(){
        // TODO implements me #PROJECT #TUTO
        return null;
    }

    /**@return (FIleSystem): the linked file system*/
    protected final FileSystem getFileSystem(){
        // TODO implements me #PROJECT #TUTO
        return null;
    }


    /// AreaGame implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        this.window = window;
        this.filesystem = fileSystem;
        this.areas = new HashMap<>();
        return true;
    }


    @Override
    public void update(float deltaTime) {
        // TODO implements me #PROJECT #TUTO
    }

    @Override
    public void end() {
        // TODO save the game states somewhere
    }

}
