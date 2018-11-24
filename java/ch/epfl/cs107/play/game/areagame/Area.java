package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.List;


/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and a List of Actors
 */
public abstract class Area implements Playable {
    // The window the game plays in
    private Window window;
    // The filesystem we need to access resources
    private FileSystem filesystem;
    // This is set to true if the area has begun before
    private boolean begun = false;
    /* Actor parameters */
    // A list of actors inside the area
    private List<Actor> actors;
    // A list of actors requested to be added
    private List<Actor> registeredActors;
    // A list of actors requested to be removed
    private List<Actor> unregisteredActors;
    // The behavior associated with this area
    private AreaBehavior areaBehavior;
    /* Camera Parameters */
    // Actor on which the camera is centered
    private Actor viewCandidate;
    // Actual center of the view
    private Vector viewCenter;

    /** @return (float): camera scale factor, assume it is the same in x and y direction */
    public abstract float getCameraScaleFactor();

    public final void setViewCandidate(Actor a) {
        this.viewCandidate = a;
    }
    
    /**
     * Add an actor to the actors list
     * @param a (Actor): the actor to add, not null
     * @param forced (Boolean): if true, add regardless of what the area wants
     */
    private void addActor(Actor a, boolean forced) {
        // This is useless as long as we have no logic for
        // refusing to add an actor, but is good as a placeholder
        boolean add = true;
        if (add || forced) {
            // add on LinkedList always returns true
           actors.add(a);
        }
    }

    /**
     * Remove an actor form the actor list
     * @param a (Actor): the actor to remove, not null
     * @param forced (Boolean): if true, remove regardless of what the area wants
     */
    private void removeActor(Actor a, boolean forced){
        // Like addActor, this can contain more complex veto logic
        boolean remove = true;
        if (remove || forced) {
            // This will remove based on reference equality,
            // which is the behavior we want.
            actors.remove(a);
        }
    }

    /**
     * Register an actor : will be added at next update
     * @param a (Actor): the actor to register, not null
     * @return (boolean): always true, since adding can't fail
     */
    public final boolean registerActor(Actor a){
        return registeredActors.add(a);
    }

    /**
     * Unregister an actor : will be removed at next update
     * @param a (Actor): the actor to unregister, not null
     * @return (boolean): always true, since removing can't fail
     */
    public final boolean unregisterActor(Actor a){
        return unregisteredActors.add(a);
    }

    /**
     * Getter for the area width
     * @return (int) : the width in number of cols
     */
    public final int getWidth(){
        return areaBehavior.getWidth();
    }

    /**
     * Getter for the area height
     * @return (int) : the height in number of rows
     */
    public final int getHeight(){
        return areaBehavior.getHeight();
    }

    /**
     * Set the behavior of this method.
     * @param behavior The behavior to set for this area
     */
    protected final void setBehavior(AreaBehavior behavior) {
        this.areaBehavior = behavior;
    }

    /** @return the Window Keyboard for inputs */
    public final Keyboard getKeyboard () {
        // TODO implements me #PROJECT #TUTO
        return null;
    }

    /// Area implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        this.window = window;
        this.filesystem = fileSystem;
        this.viewCenter = Vector.ZERO;
        this.begun = true;
        return true;
    }

    /**
     * This method is useful to know if an area has been started before,
     * in order to not reinitialise it if it's in a certain state.
     * @return true if the area has begun before
     */
    public boolean hasBegun() {
        return begun;
    }

    /**
     * Resume method: Can be overridden
     * @param window (Window): display context, not null
     * @param fileSystem (FileSystem): given file system, not null
     * @return (boolean) : if the resume succeed, true by default
     */
    public boolean resume(Window window, FileSystem fileSystem){
        return true;
    }

    // Note to TAs: making this method final instead of private void makes no
    // sense, since package private methods can't be overriden anyways.
    // We also don't want this method ever being called outside the class.
    /**
     * Add the registered actors to the concrete list, and remove
     * the unregistered ones.
     */
    private void purgeRegistration() {
        // This doesn't work at all if an actor appears multiple times
        // in one of the lists, but let's treat that as a programmer error
        // with bizarre behavior
        for (Actor a : registeredActors) {
            addActor(a, false);
        }
        for (Actor a : unregisteredActors) {
            removeActor(a, false);
        }
        // Assigning the old lists to an empty list could potentially
        // introduce a memory link, if we hold a reference to an intermediate
        // node, but none of the rest. Using this method avoids that
        registeredActors.clear();
        unregisteredActors.clear();
    }

    @Override
    public void update(float deltaTime) {
        // actors
        purgeRegistration();
        // drawing
        updateCamera();
    }

    private void updateCamera () {
        if (viewCandidate != null) {
            viewCenter = viewCandidate.getPosition();
        }
        Transform viewTransform = Transform.I
                .scaled(getCameraScaleFactor())
                .translated(viewCenter);
        window.setRelativeTransform(viewTransform);
    }

    /**
     * Suspend method: Can be overridden, called before resume other
     */
    public void suspend(){
        // Simply update the actor list
        purgeRegistration();
    }


    @Override
    public void end() {
        // TODO save the AreaState somewhere
    }

}
