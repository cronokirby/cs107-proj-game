package ch.epfl.cs107.play.signal.logic;


import java.util.List;

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

    public MultipleAnd(List<Logic> signals) {
        this.signals = new Logic[signals.size()];
        this.signals = signals.toArray(this.signals);
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
