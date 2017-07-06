package vobis.example.com.gamification.me2minigame.gameconfig;

import vobis.example.com.gamification.me2minigame.gameconfig.generator.EasyGenerator;
import vobis.example.com.gamification.me2minigame.gameconfig.generator.MediumGenerator;
import vobis.example.com.gamification.me2minigame.gameconfig.generator.RowGenerator;

public class Easy implements GameConfig {
    private GameTime mTime;
    private GameSpeedController mGameSpeedController;
    private RowGenerator mGenerator;
    private CodesSelector mSelector;
    private final static String name = "Easy level of difficulty";

    public Easy(){
        mTime = new GameTime(90);
        mGameSpeedController =  new GameSpeedController(1);
        mGenerator = new EasyGenerator();
        mSelector = new CodesSelector();
    }

    @Override
    public GameTime getTimer() {
        return mTime;
    }

    @Override
    public GameSpeedController getSpeedController() {
        return mGameSpeedController;
    }

    @Override
    public RowGenerator getGenerator() {
        return mGenerator;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CodesSelector getSelector() {
        return mSelector;
    }
}
