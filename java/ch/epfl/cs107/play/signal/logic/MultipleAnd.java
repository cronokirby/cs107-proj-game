package ch.epfl.cs107.play.signal.logic;


/**
 * Represents a signal that only fires when all of its
 * arguments do
 */
public class MultipleAnd extends LogicSignal {
    // Array is fine here since we never add any signals
    /// A list of signals
    private Logic[] signals;

    public MultipleAnd(Logic ...signals) {
        this.signals = signals;
    }

    @Override
    public boolean isOn() {
        for (Logic signal : signals) {
            if (signal == null || !signal.isOn()) {
                return false;
            }
        }
        return true;
    }
}
