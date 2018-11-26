package ch.epfl.cs107.play.signal.logic;

/**
 * A signal that requires both signals to be active
 */
public class And extends LogicSignal {
    /// The left argument signal
    private Logic left;
    /// The right argument signal
    private Logic right;

    /**
     * Construct a new And signal from 2 other signals.
     * If either of them are null, this never fires.
     */
    public And(Logic left, Logic right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isOn() {
        boolean leftOn = left != null && left.isOn();
        boolean rightOn = right != null && right.isOn();
        return leftOn && rightOn;
    }
}
