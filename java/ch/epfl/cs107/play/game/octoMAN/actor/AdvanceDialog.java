package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Represents a class of dialogs that can only be advanced
 * and fed, automatically hiding themselves when no text is available
 */
public class AdvanceDialog implements Graphics {
    /// The dialog we're wrapping around.
    private Dialog dialog;
    /// Whether or not to display this
    private boolean display;

    public AdvanceDialog(String backgroundName, Area area) {
        dialog = new Dialog("", backgroundName, area);
        display = false;
    }

    /**
     * Fill this dialog with text, which may take multiple dialogs
     * @param text the text to fill this with
     */
    public void fill(String text) {
        dialog.resetDialog(text);
        display = true;
    }

    /**
     * Advance the dialog, automatically closing it if
     * no more text is left to display.
     */
    public void advance() {
        display = !dialog.push();
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
