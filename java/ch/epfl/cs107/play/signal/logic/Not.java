package ch.epfl.cs107.play.signal.logic;

/**
 * Represents the negation of a given symbol
 */
public class Not extends LogicSignal {
    /// The signal this is negating
    private Logic signal;

    /**
     * Construct a new negation from a signal
     * @param signal the signal to negate, can be null
     */
    public Not(Logic signal) {
        this.signal = signal;
    }

    @Override
    public boolean isOn() {
        return signal != null && !signal.isOn();
    }
}
