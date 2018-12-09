package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

/**
 * Represents a scale that activates when both sides have the same weight
 */
public class Scale extends AreaEntity implements Logic {
    private enum Bias {
        Left,
        Right,
        Even,
        NoWeights
    }

    /// The sprite to display when the scale leans left
    private Sprite leftSprite;
    /// The sprite to display when the scale leans right
    private Sprite rightSprite;
    /// The sprite to display when the scale is even
    private Sprite evenSprite;
    /// The sprite to display when the scale has no weights
    private Sprite emptySprite;
    /// A list of pedestals on the left side
    private List<Pedestal> leftPedestals;
    /// A list of pedestals on the right side
    private List<Pedestal> rightPedestals;
    /// The state of the scale
    private Bias bias;

    public Scale(List<Pedestal> leftPedestals, List<Pedestal> rightPedestals,
                 Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
        area.registerActor(this);
        leftSprite = new Sprite("scale.left", 1.f, 1.f, this);
        rightSprite = new Sprite("scale.right", 1.f, 1.f, this);
        evenSprite = new Sprite("scale.even", 1.f, 1.f, this);
        emptySprite = new Sprite("scale.empty", 1.f, 1.f, this);
        this.leftPedestals = leftPedestals;
        this.rightPedestals = rightPedestals;
        setBias();
    }

    /**
     * Set the bias of the scale based on the weights on its pedestals
     */
    private void setBias() {
        int leftSum = 0;
        for (Pedestal p : leftPedestals) {
            leftSum += p.weigh();
        }
        int rightSum = 0;
        for (Pedestal p : rightPedestals) {
            rightSum += p.weigh();
        }
        if (leftSum > rightSum) {
            bias = Bias.Left;
        } else if (rightSum > leftSum) {
            bias = Bias.Right;
        } else if (leftSum == 0) {
            // the right sum is also 0 since they're equal
            bias = Bias.NoWeights;
        } else {
            bias = Bias.Even;
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        setBias();
    }

    @Override
    public void draw(Canvas canvas) {
        switch (bias) {
            case Right:
                rightSprite.draw(canvas);
                break;
            case Left:
                leftSprite.draw(canvas);
                break;
            case NoWeights:
                emptySprite.draw(canvas);
                break;
            case Even:
                evenSprite.draw(canvas);
                break;
        }
    }

    @Override
    public boolean isOn() {
        return bias == Bias.Even;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        // We don't need anything here since we accept no interactions
    }
}
