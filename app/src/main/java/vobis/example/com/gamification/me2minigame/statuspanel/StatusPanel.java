package vobis.example.com.gamification.me2minigame.statuspanel;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import vobis.example.com.gamification.me2minigame.MEMiniGameActivity;
import vobis.example.com.gamification.me2minigame.gameconfig.GameConfig;

public class StatusPanel extends RelativeLayout {

    public static int WIDTH, HEIGHT;

    private GameConfig mGameConfig;
    private LivesIndicator mLivesIndicator;
    private TimeIndicator mTimeIndicator;
    private SoughtTilesIndicator mSoughtTilesIndicator;
    protected final static int CHILDREN_AMOUNT = 3;

    private MEMiniGameActivity mContext;

    public StatusPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = (MEMiniGameActivity) context;

        WIDTH = getResources().getDisplayMetrics().widthPixels;
        HEIGHT = getResources().getDisplayMetrics().heightPixels;

        mLivesIndicator = new LivesIndicator(context);
        mTimeIndicator = new TimeIndicator(context);
        mSoughtTilesIndicator = new SoughtTilesIndicator(context);

        addView(mLivesIndicator);
        addView(mTimeIndicator);
        addView(mSoughtTilesIndicator);

        ValueAnimator valueAnimator = ObjectAnimator.ofInt(this, "backgroundColor", Color.RED, Color.BLUE);
        valueAnimator.setDuration(3000);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
    }

    public void reflectMistake(){
        mLivesIndicator.fail();
        if(mLivesIndicator.getLives() == 0)
            mContext.gameOver("You lost all your lives");
    }

    public void reflectCorrectChoice(){
        mSoughtTilesIndicator.setNextToFind();
    }

    public void stopGame(String msg){
        mTimeIndicator.stopCounting(msg);
    }
}
