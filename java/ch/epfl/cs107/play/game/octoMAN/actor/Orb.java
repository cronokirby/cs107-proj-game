package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an orb that can be collected
 */
public class Orb extends AreaEntity implements Portal {
    public enum Type {
        PHYSICS,
        ENVIRONMENT,
        MATHEMATICS,
        LIFESCIENCES,
        ELECTRICITY,
        CHEMISTRY,
        MICROTECHNICS,
        COMPUTERSCIENCE,
        NULL;

        public static List<Type> realValues() {
            List<Type> ret = new ArrayList<>(Arrays.asList(Type.values()));
            ret.remove(NULL);
            return ret;
        }
    }

    /// The underlying type for this orb
    public final Type type;
    /// The sprite for this orb
    private Sprite sprite;
    /// The coordinates in the level select room this orb leads to
    private DiscreteCoordinates destinationPosition;


    public Orb(Type type, Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        if (type != Type.NULL) {
            area.registerActor(this);
        }
        this.type = type;
        String spriteName = "";
        int x = 0;
        switch (type) {
            case PHYSICS:
                spriteName = "orb.1";
                x = 2;
                break;
            case ENVIRONMENT:
                spriteName = "orb.2";
                x = 5;
                break;
            case MATHEMATICS:
                spriteName = "orb.3";
                x = 8;
                break;
            case LIFESCIENCES:
                spriteName = "orb.4";
                x = 11;
                break;
            case ELECTRICITY:
                spriteName = "orb.5";
                x = 15;
                break;
            case CHEMISTRY:
                spriteName = "orb.6";
                x = 18;
                break;
            case MICROTECHNICS:
                spriteName = "orb.7";
                x = 21;
                break;
            case COMPUTERSCIENCE:
                spriteName = "orb.8";
                x = 24;
                break;
            case NULL:
                spriteName = "orb.1";
                x = -1;
                break;
        }
        sprite = new Sprite(spriteName, 1.f, 1.f, this);
        destinationPosition = new DiscreteCoordinates(x, 8);
    }

    /**
     * Return the sprite associated with this orb
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Have this orb be collected by a holder
     */
    public void collect(OrbHolder holder) {
        holder.insert(this);
        getOwnerArea().unregisterActor(this);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    @Override
    public String getDestinationArea() {
        return "LevelSelect";
    }

    @Override
    public DiscreteCoordinates getDestinationPosition() {
        return destinationPosition;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        OctoInteractionVisitor o = (OctoInteractionVisitor) v;
        o.interactWith((Portal)this);
        o.interactWith(this);
    }
}
