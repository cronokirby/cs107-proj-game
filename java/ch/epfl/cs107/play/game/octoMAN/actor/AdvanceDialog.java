package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents a class of dialogs that can only be advanced
 * and fed, automatically hiding themselves when no text is available
 */
public class AdvanceDialog implements Graphics {
    /// The dialog we're wrapping around.
    private Dialog dialog;
    /// The talker we're wrapping around
    private Talkable talker;
    /// Whether or not to display this
    private boolean display;

    public AdvanceDialog(String backgroundName, Area area) {
        dialog = new Dialog("", backgroundName, area);
        display = false;
    }

    /**
     * Fill this dialog with text, which may take multiple dialogs
     * @param talker the entity talking via this dialog
     * @param orientation the orientation of the entity being talked to
     */
    public void setTalker(Talkable talker, Orientation orientation) {
        dialog.resetDialog(talker.talk(orientation));
        this.talker = talker;
        display = true;
    }

    /**
     * Advance the dialog, automatically closing it if
     * no more text is left to display.
     */
    public void advance() {
        display = !dialog.push();
        if (!display) {
            talker.doneTalking();
        }
    }

    /**
     * Check whether or not this dialog is open
     */
    public boolean isOpen() {
        return display;
    }

    @Override
    public void draw(Canvas canvas) {
        if (display) {
            dialog.draw(canvas);
        }
    }
}
