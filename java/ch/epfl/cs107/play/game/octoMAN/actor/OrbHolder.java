package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.HashMap;
import java.util.Map;

public class OrbHolder extends Entity {
    private Map<Orb.Type, Sprite> orbs;
    private Sprite nullSprite;
    private Vector startPosition;
    private Actor anchor;

    public OrbHolder(Vector position) {
        super(position);
        orbs = new HashMap<>();
        nullSprite = new Sprite("orb.0", 1.f, 1.f, this);
        startPosition = position;
        for (Orb.Type t : Orb.Type.values()) {
            orbs.put(t, null);
        }
    }

    public void setAnchor(Actor anchor) {
        this.anchor = anchor;
    }

    public void insert(Orb orb) {
        Sprite sprite = orb.getSprite();
        sprite.setParent(this);
        orbs.put(orb.type, sprite);
    }

    @Override
    public void draw(Canvas canvas) {
        setCurrentPosition(anchor.getPosition().add(startPosition));
        for (Orb.Type t : Orb.Type.values()) {
            setCurrentPosition(getPosition().add(1.f, 0));
            Sprite s = orbs.get(t);
            if (s == null) {
                nullSprite.draw(canvas);
            } else {
                s.draw(canvas);
            }
        }
        setCurrentPosition(startPosition);
    }
}
