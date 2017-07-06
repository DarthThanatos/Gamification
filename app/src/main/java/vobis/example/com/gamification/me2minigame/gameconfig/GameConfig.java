package vobis.example.com.gamification.me2minigame.gameconfig;

import java.io.Serializable;

import vobis.example.com.gamification.me2minigame.gameconfig.generator.RowGenerator;

public interface GameConfig extends Serializable {

    public GameTime getTimer();
    public GameSpeedController getSpeedController();
    public RowGenerator getGenerator();
    public String getName();
    public CodesSelector getSelector();
}
