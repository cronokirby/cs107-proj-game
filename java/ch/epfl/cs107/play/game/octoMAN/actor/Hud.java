package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents the heads up display for the game
 */
public class Hud extends Entity {
    /// The orb holder in thud
    private OrbHolder holder;
    /// The timer in the hud
    private Timer timer;
    /// The weight inventory in the hud
    private WeightSack sack;

    public Hud() {
        super(new Vector(0, 0));
        holder = new OrbHolder(new Vector(-11f, 9f));
        timer = new Timer(new Vector(6f, 9.5f));
        sack = new WeightSack(new Vector(-10f, 7f));
    }

    /**
     * Get the holder contained inside this HUD
     */
    public OrbHolder getHolder() {
        return holder;
    }

    /**
     * Get the weight sack inside this hud
     */
    public WeightSack getWeightSack() {
        return sack;
    }

    /**
     * Set the anchor actor for this HUD
     */
    public void setAnchor(Actor anchor) {
        holder.setAnchor(anchor);
        timer.setAnchor(anchor);
        sack.setAnchor(anchor);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        holder.update(deltaTime);
        timer.update(deltaTime);
        sack.update(deltaTime);
    }

    @Override
    public void draw(Canvas canvas) {
        holder.draw(canvas);
        timer.draw(canvas);
        sack.draw(canvas);
    }
}
