package ch.epfl.cs107.play.signal.logic;

/**
 * Represents a signal activated when either of its arguments are
 */
public class Or extends LogicSignal {
    /// The left signal
    private Logic left;
    /// The right signal
    private Logic right;

    /**
     * Construct a new Or signal from 2 other signals.
     * If either of them are null, this signal never fires.
     */
    public Or(Logic left, Logic right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isOn() {
        boolean notNull = left != null && right != null;
        return notNull && (left.isOn() || right.isOn());
    }
}
