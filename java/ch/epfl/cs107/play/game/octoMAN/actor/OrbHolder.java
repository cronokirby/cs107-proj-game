package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a hud display of every orb the player has collected.
 * The holder is activated when all orbs are collected.
 */
public class OrbHolder extends AnchoredEntity implements Logic {
    /// Maps each orb to either a null sprite, or a valid sprite
    private Map<Orb.Type, Sprite> orbs;
    /// The sprite to display when we encounter a missing orb
    private Sprite nullSprite;

    /**
     * Construct a new orbholder from an offset position.
     *
     * After calling this, you also want to set an anchor.
     */
    public OrbHolder(Vector position) {
        super(position);
        orbs = new HashMap<>();
        nullSprite = new Sprite("orb.0", 1.f, 1.f, this);
        for (Orb.Type t : Orb.Type.realValues()) {
            orbs.put(t, null);
        }
    }

    /**
     * Return true if the orb holder contains all the orbs
     */
    public boolean isOn() {
        for (Orb.Type t : Orb.Type.realValues()) {
            if (orbs.get(t) == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Insert an orb into this holder.
     * This assumes the orb has been removed from whatever area it was in
     */
    public void insert(Orb orb) {
        Sprite sprite = orb.getSprite();
        sprite.setParent(this);
        orbs.put(orb.type, sprite);
    }

    @Override
    public void draw(Canvas canvas) {
        for (Orb.Type t : Orb.Type.realValues()) {
            setCurrentPosition(getPosition().add(1.f, 0));
            Sprite s = orbs.get(t);
            if (s == null) {
                nullSprite.draw(canvas);
            } else {
                s.draw(canvas);
            }
        }
    }
}
