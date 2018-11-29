package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.octoMAN.handler.OctoInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents an old man you can talk to
 */
public class OldMan extends AreaEntity implements Talkable {
    /// The old man's sprite
    private Animation spriteSheet;
    /// This old man's text
    private String text;

    public OldMan(String text, Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        area.registerActor(this);
        this.spriteSheet = Animation.from4x4("old.man.1", this);
        this.spriteSheet.resetOrientation(orientation);
        this.text = text;
    }

    @Override
    public String talk(Orientation orientation) {
        spriteSheet.resetOrientation(orientation.opposite());
        return text;
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
