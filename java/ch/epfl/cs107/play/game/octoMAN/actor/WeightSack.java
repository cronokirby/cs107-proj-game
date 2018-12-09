package ch.epfl.cs107.play.game.octoMAN.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an inventory of weights that the player
 * can place on pedestals
 */
public class WeightSack extends AnchoredEntity {
    /**
     * Represents the type of elements stored in this weight sack
     */
    private class Element implements Graphics {
        private final Weight weight;
        private int count;

        private Element(Weight weight) {
            this.weight = weight;
            this.count = 1;
            weight.attachSprite(WeightSack.this);
        }

        /**
         * Increment the count of this element
         */
        private void increment() {
            ++count;
        }

        /**
         * Returns true if this element is now empty
         */
        private boolean decrement() {
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

        @Override
        public void draw(Canvas canvas) {
            weight.draw(canvas);
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
            e.draw(canvas);
            TextGraphics g = new TextGraphics(Integer.toString(e.count), .8f, Color.WHITE);
            g.setParent(this);
            g.setAnchor(new Vector(1.2f, 0.25f));
            g.draw(canvas);
            if (i == selected) {
                cursor.draw(canvas);
            }
            ++i;
        }
    }

    /**
     * Add a new weight to the collection
     * @param weight the weight to add, not null
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
        int sz = elements.size();
        // selected should remain at 0 otherwise
        if (sz > 0) {
            selected = selected + 1 % sz;
        }
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
                selected = Math.max(elements.size() - 1, 0);
            }
        }
        return target.weight;
    }

    /**
     * Remove all elements from this sack
     */
    public void empty() {
        elements.clear();
        selected = 0;
    }
}
