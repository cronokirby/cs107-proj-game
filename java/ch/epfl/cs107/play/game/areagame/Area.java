package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and a List of Actors
 */
public abstract class Area implements Playable {
    /// The window the game plays in
    private Window window;
    /// The filesystem we need to access resources
    private FileSystem filesystem;
    /// This is set to true if the area has begun before
    private boolean begun = false;
    /* Actor parameters */
    // A list of actors inside the area
    private List<Actor> actors;
    /// A list of actors requested to be added
    private List<Actor> registeredActors;
    /// A list of actors requested to be removed
    private List<Actor> unregisteredActors;
    /// A map of interactables that need to enter the area
    private Map<Interactable, List<DiscreteCoordinates>> enteringInteractables;
    /// A map of interactables that need to leave the are
    private Map<Interactable, List<DiscreteCoordinates>> leavingInteractables;
    /// A list of interactors in this area
    private List<Interactor> interactors;
    /// The behavior associated with this area
    private AreaBehavior areaBehavior;
    /* Camera Parameters */
    /// Actor on which the camera is centered
    private Actor viewCandidate;
    /// Actual center of the view
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
        boolean add = true;
        if (a instanceof Interactable) {
            Interactable aI = (Interactable) a;
            add = transitionAreaCells(aI, new LinkedList<>(), aI.getCurrentCells());
        }
        if (a instanceof Interactor) {
            Interactor aI = (Interactor) a;
            interactors.add(aI);
        }
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
        if (a instanceof Interactable) {
            Interactable aI = (Interactable) a;
            remove = transitionAreaCells(aI, aI.getCurrentCells(), new LinkedList<>());
        }
        if (a instanceof Interactor) {
            Interactor aI = (Interactor) a;
            interactors.remove(aI);
        }
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
     * Check whether or not a pair of coordinates is in this area
     * @param coords the coordinates to check
     * @return true if the coordinates are in this area
     */
    public final boolean contains(DiscreteCoordinates coords) {
        boolean containsX = coords.x >= 0 && coords.x < getWidth();
        boolean containsY = coords.y >= 0 && coords.y < getHeight();
        return containsX && containsY;
    }


    /**
     * Make an entity leave a list of coordinates and enter another.
     *
     * It's necessary to not split this into 2 separate methods because
     * of the following scenario:
     * If we can enter the desired cells, but cannot leave the current ones,
     * then one of the checks will successfully register us as entering,
     * but the other won't register us as leaving, and we'll be registered as
     * interactable in 2 cells at once, which is not the behavior we want.
     * Joining them in a method allows to ensure that we will either
     * not affect the state of the cells, or affect them in a valid way.
     *
     * @param entity the entity wanting to transition
     * @param leaving the cells it wants to leave
     * @param entering the cells it wants to enter
     * @return whether or not the transition could be carried out
     */
    public final boolean transitionAreaCells(Interactable entity,
                                             List<DiscreteCoordinates> leaving,
                                             List<DiscreteCoordinates> entering) {
        if (areaBehavior.canLeave(entity, leaving) &&
            areaBehavior.canEnter(entity, entering)) {
            leavingInteractables.put(entity, leaving);
            enteringInteractables.put(entity, entering);
            return true;
        }
        return false;
    }

    /**
     * Check if an entity can enter a list of cells, but don't make
     * any commitment.
     * This is useful to make a decision based on whether or not an entity
     * could move into a cell.
     * @param entity the entity to check
     * @param entering the list of cells the entity is entering
     * @return true if the entity can enter
     */
    public final boolean canEnter(Interactable entity, List<DiscreteCoordinates> entering) {
        return areaBehavior.canEnter(entity, entering);
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
        return window.getKeyboard();
    }

    /// Area implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        this.window = window;
        this.filesystem = fileSystem;
        this.actors = new LinkedList<>();
        this.registeredActors = new LinkedList<>();
        this.unregisteredActors = new LinkedList<>();
        this.enteringInteractables = new HashMap<>();
        this.leavingInteractables = new HashMap<>();
        this.interactors = new LinkedList<>();
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

        // Handling the Interactables

        for (Map.Entry<Interactable, List<DiscreteCoordinates>> e : enteringInteractables.entrySet()) {
            areaBehavior.enter(e.getKey(), e.getValue());
        }
        for (Map.Entry<Interactable, List<DiscreteCoordinates>> e : leavingInteractables.entrySet()) {
            areaBehavior.leave(e.getKey(), e.getValue());
        }
        enteringInteractables.clear();
        leavingInteractables.clear();
    }

    @Override
    public void update(float deltaTime) {
        // actors
        purgeRegistration();
        // drawing
        for (Actor a : actors) {
            a.update(deltaTime);
            a.draw(window);
        }
        // Handling interactions
        for (Interactor interactor : interactors) {
            if (interactor.wantsCellInteraction()) {
                areaBehavior.cellInteractionOf(interactor);
            }
            if (interactor.wantsViewInteraction()) {
                areaBehavior.viewInteractionOf(interactor);
            }
        }
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
