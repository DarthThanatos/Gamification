package vobis.example.com.gamification.me2minigame.gameconfig;

import android.os.CountDownTimer;

import java.util.TimerTask;

public class GameTime {

    private int mSecondsToEnd;

    public GameTime(int secondsToEnd){
        mSecondsToEnd = secondsToEnd;
    }

    public long getGameTimeMillis(){
        return mSecondsToEnd * 1000;
    }

    public int getGameTimeSeconds(){
        return mSecondsToEnd;
    }
}
