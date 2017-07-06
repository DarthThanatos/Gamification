package vobis.example.com.gamification.me2minigame.gameconfig;

public class GameSpeedController {

    private int mSlideStep;

    public GameSpeedController(int slideStep){
        mSlideStep = slideStep;
    }

    public int getSlideStep(){
        return mSlideStep;
    }

}
