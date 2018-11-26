package ch.epfl.cs107.play.game.octoMAN;

import ch.epfl.cs107.play.game.areagame.AreaGame;

public class OctoGame extends AreaGame {
    @Override
    public String getTitle() {
        return "OctoMAN";
    }

    @Override
    public int getFrameRate() {
        return 24;
    }
}
