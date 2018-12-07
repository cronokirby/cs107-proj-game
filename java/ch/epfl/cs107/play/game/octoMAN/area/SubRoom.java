package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

/**
 * Represents a simple sub room in an area
 */
public abstract class SubRoom extends OctoArea {
    // The title of this sub room
    private String title;

    public SubRoom(String title) {
        this.title = title;
    }

    /**
     * Add the initial actors in this room
     */
    protected abstract void addActors();

    /**
     * Return true if this room should reset
     */
    protected boolean reset() {
        return true;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        addActors();
        return superOK;
    }

    @Override
    public boolean resume(Window window, FileSystem fileSystem) {
        if (reset()) {
            return begin(window, fileSystem);
        }
        else {
            return super.resume(window, fileSystem);
        }
    }
}
