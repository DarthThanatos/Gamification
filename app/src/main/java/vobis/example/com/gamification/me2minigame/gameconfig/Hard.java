package vobis.example.com.gamification.me2minigame.gameconfig;

import vobis.example.com.gamification.me2minigame.gameconfig.generator.HardGenerator;
import vobis.example.com.gamification.me2minigame.gameconfig.generator.MediumGenerator;
import vobis.example.com.gamification.me2minigame.gameconfig.generator.RowGenerator;

public class Hard implements  GameConfig {

    private GameTime mTime;
    private GameSpeedController mGameSpeedController;
    private RowGenerator mGenerator;
    private CodesSelector mSelector;
    private final static String name = "Hard level of difficulty";

    public Hard(){
        mTime = new GameTime(30);
        mGameSpeedController =  new GameSpeedController(5);
        mGenerator = new HardGenerator();
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
