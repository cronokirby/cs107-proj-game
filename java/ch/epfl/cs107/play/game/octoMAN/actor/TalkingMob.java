package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents a mob you can talk to
 */
public class TalkingMob extends WanderingEntity implements Talkable {
    /// The entity's sprite
    private Animation spriteSheet;
    /// This entity's text
    private String text;
    /// Whether or not this entity is talking
    private boolean talking;


    public TalkingMob(String text, String sheetName, Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        area.registerActor(this);
        this.spriteSheet = Animation.from4x4(sheetName, this);
        this.spriteSheet.resetOrientation(orientation);
        this.text = text;
    }

    @Override
    protected boolean canMove() {
        return !talking;
    }

    @Override
    protected Animation getAnimation() {
        return spriteSheet;
    }

    @Override
    public String talk(Orientation orientation) {
        spriteSheet.resetOrientation(orientation.opposite());
        talking = true;
        return text;
    }

    @Override
    public void doneTalking() {
        talking = false;
    }

    @Override
    public void draw(Canvas canvas) {
        spriteSheet.getSprite().draw(canvas);
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
        ((OctoInteractionVisitor)v).interactWith((Talkable)this);
    }
}
