package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.*;

/**
 * Represents an inventory of weights that the player
 * can place on pedestals
 */
public class WeightSack extends AnchoredEntity {
    /**
     * Represents the type of elements stored in this weight sack
     */
    private class Element {
        private final Weight weight;
        private int count;
        private final Sprite sprite;

        public Element(Weight weight) {
            this.weight = weight;
            this.count = 1;
            this.sprite = new Sprite(weight.getType(), 1.f, 1.f, WeightSack.this);
        }

        /**
         * Increment the count of this element
         */
        public void increment() {
            ++count;
        }

        /**
         * Returns true if this element is now empty
         */
        public boolean decrement() {
            --count;
            return count <= 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Element element = (Element) o;
            return weight.sameType(element.weight);
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight.getType());
        }
    }

    /// The index of the selected element
    private int selected;
    /// The sprite to draw for the cursor
    private Sprite cursor;
    /// The list of elements
    private List<Element> elements;


    public WeightSack(Vector position) {
        super(position);
        selected = 0;
        cursor = new Sprite("cursor", 1.f, 1.f, this);
        // We use a linked hash map in order to preserve the order of addition
        elements = new ArrayList<>();
    }

    @Override
    public void draw(Canvas canvas) {
        int i = 0;
        for (Element e : elements) {
            setCurrentPosition(getPosition().add(0, -1.f));
            e.sprite.draw(canvas);
            if (i == selected) {
                cursor.draw(canvas);
            }
            ++i;
        }
    }


    /**
     * Add a new weight to the collection
     */
    public void addWeight(Weight weight) {
        Element already = null;
        for (Element e : elements) {
            if (e.weight.sameType(weight)) {
                already = e;
                break;
            }
        }
        if (already != null) {
            already.increment();
        } else {
            elements.add(new Element(weight));
        }
    }

    /**
     * Move the cursor forward, cycling if necessary
     */
    public void incrementCursor() {
        selected = (selected + 1) % elements.size();
    }

    /**
     * Take a weight from the current selection
     * @return null if no weight could be taken
     */
    public Weight take() {
        Element target = elements.get(selected);
        if (target == null) {
            return null;
        }
        if (target.decrement()) {
            elements.remove(selected);
            if (selected >= elements.size()) {
                selected = elements.size();
            }
        }
        return target.weight;
    }
}
