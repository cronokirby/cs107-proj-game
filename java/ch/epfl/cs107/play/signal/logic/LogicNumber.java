package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

import java.util.List;

/**
 * Represents a signal comparing a group of signals to a number
 */
public class LogicNumber extends LogicSignal {
    /// The signals to keep track of
    private List<Logic> signals;
    /// The number to compare with
    private float target;

    /**
     * Construct a new logic number signal from a target number,
     * and a list of signals acting as fluid binary digits for
     * that number.
     * The signals must not be null
     */
    public LogicNumber(float target, List<Logic> signals) {
        this.signals = signals;
        this.target = target;
    }

    @Override
    public boolean isOn() {
        int power = 1;
        float acc = 0.0f;
        for (Logic s : signals) {
            acc += s.getIntensity() * power;
            power <<= 1;
        }
        return Math.abs(acc - target) < Signal.EPSILON;
    }
}
