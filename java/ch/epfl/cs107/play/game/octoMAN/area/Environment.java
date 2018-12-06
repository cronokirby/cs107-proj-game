package ch.epfl.cs107.play.game.octoMAN.area;

import ch.epfl.cs107.play.game.octoMAN.actor.Orb;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

import java.util.LinkedList;
import java.util.List;

public class Environment extends OctoArea {
    @Override
    public String getTitle() {
        return "Environment";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean superOK = super.begin(window, fileSystem);
        new Orb(Orb.Type.ENVIRONMENT, this, new DiscreteCoordinates(83, 97));

        List<BirdFactory> factories = new LinkedList<>();
        factories.add(new BirdFactory(1, 2, 8, 6, "bird.1", 4));
        factories.add(new BirdFactory(2, 9, 7, 13, "bird.2", 3));
        factories.add(new BirdFactory(14, 1, 20, 5, "bird.1", 2));
        factories.add(new BirdFactory(11, 13, 20, 15, "bird.1", 4));
        factories.add(new BirdFactory(11, 8, 20, 10, "bird.2", 4));
        factories.add(new BirdFactory(23, 9, 26, 15, "bird.3", 2));
        factories.add(new BirdFactory(12, 19, 14, 21, "bird.1", 8));
        factories.add(new BirdFactory(16, 23, 18, 25, "bird.2", 6));
        factories.add(new BirdFactory(23, 18, 28, 28, "bird.4", 4));
        factories.add(new BirdFactory(22, 33, 26, 38, "bird.3", 6));
        factories.add(new BirdFactory(27, 35, 30, 38, "bird.1", 5));
        factories.add(new BirdFactory(12, 35, 18, 37, "bird.4", 4));
        factories.add(new BirdFactory(12, 28, 18, 30, "bird.1", 5));
        factories.add(new BirdFactory(2, 28, 6, 31, "bird.2", 3));
        factories.add(new BirdFactory(2, 41, 7, 44, "bird.4", 3));
        factories.add(new BirdFactory(10, 48, 15, 54, "bird.3", 4));
        factories.add(new BirdFactory(9, 60, 13, 62, "bird.1", 5));
        factories.add(new BirdFactory(1, 60, 5, 67, "bird.2", 2));
        factories.add(new BirdFactory(6, 68, 10, 70, "bird.4", 3));
        factories.add(new BirdFactory(9, 72, 11, 74, "bird.1", 2));
        factories.add(new BirdFactory(11, 73, 12, 78, "bird.3", 4));
        factories.add(new BirdFactory(14, 79, 16, 82, "bird.2", 3));
        factories.add(new BirdFactory(16, 84, 20, 85, "bird.4", 4));
        factories.add(new BirdFactory(20, 86, 24, 89, "bird.1", 3));
        factories.add(new BirdFactory(24, 90, 27, 91, "bird.2", 3));
        factories.add(new BirdFactory(27, 92, 30, 94, "bird.3", 4));
        factories.add(new BirdFactory(34, 88, 35, 93, "bird.1", 3));
        factories.add(new BirdFactory(36, 86, 39, 89, "bird.1", 3));
        factories.add(new BirdFactory(36, 79, 38, 81, "bird.3", 4));
        factories.add(new BirdFactory(27, 75, 33, 79, "bird.4", 2));
        factories.add(new BirdFactory(24, 66, 34, 69, "bird.1", 2));
        factories.add(new BirdFactory(25, 48, 27, 65, "bird.2", 3));
        factories.add(new BirdFactory(24, 48, 36, 53, "bird.4", 3));
        factories.add(new BirdFactory(35, 37, 38, 41, "bird.3", 3));
        factories.add(new BirdFactory(50, 37, 57, 42, "bird.1", 3));
        factories.add(new BirdFactory(54, 54, 59, 60, "bird.2", 3));
        factories.add(new BirdFactory(46, 25, 50, 30, "bird.1", 2));
        factories.add(new BirdFactory(37, 24, 40, 30, "bird.4", 3));
        factories.add(new BirdFactory(33, 15, 39, 19, "bird.3", 2));
        factories.add(new BirdFactory(45, 14, 48, 19, "bird.2", 3));
        factories.add(new BirdFactory(51, 13, 63, 14, "bird.4", 3));
        factories.add(new BirdFactory(64, 9, 70, 18, "bird.1", 3));
        factories.add(new BirdFactory(77, 10, 80, 16, "bird.3", 3));
        factories.add(new BirdFactory(81, 11, 82, 16, "bird.2", 3));
        factories.add(new BirdFactory(75, 25, 79, 30, "bird.1", 2));
        factories.add(new BirdFactory(68, 31, 71, 34, "bird.4", 3));
        factories.add(new BirdFactory(65, 39, 72, 42, "bird.2", 3));
        factories.add(new BirdFactory(69, 48, 72, 48, "bird.1", 3));
        factories.add(new BirdFactory(77, 42, 79, 45, "bird.3", 3));
        factories.add(new BirdFactory(77, 49, 79, 52, "bird.1", 3));
        factories.add(new BirdFactory(91, 65, 93, 70, "bird.1", 2));
        factories.add(new BirdFactory(80, 68, 85, 72, "bird.1", 2));
        factories.add(new BirdFactory(80, 77, 85, 79, "bird.1", 2));
        factories.add(new BirdFactory(80, 84, 85, 87, "bird.1", 2));
        for (BirdFactory f : factories) {
            f.spawnBirds(this);
        }
        return superOK;
    }
}
