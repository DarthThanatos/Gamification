package vobis.example.com.gamification.me2minigame.statuspanel;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vobis.example.com.gamification.me2minigame.MEGameArea;
import vobis.example.com.gamification.me2minigame.MEMiniGameActivity;
import vobis.example.com.gamification.me2minigame.gameconfig.GameConfig;
import vobis.example.com.gamification.topdownminigame.GameArea;

public class TimeIndicator extends TextView {

    private MEMiniGameActivity mContext;
    private CountDownTimer mCountDownTimer;

    public TimeIndicator(Context context) {
        super(context);
        mContext = (MEMiniGameActivity) context;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                StatusPanel.WIDTH/StatusPanel.CHILDREN_AMOUNT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        setLayoutParams(params);
        setGravity(Gravity.CENTER);
        mCountDownTimer =  new CountDownTimer(mContext.mSelectedConfig.getTimer().getGameTimeMillis(), 1000) {

            @Override
            public void onTick(final long millisUntilFinished) {
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TimeIndicator.this.setText("Time: " + millisUntilFinished / 1000);
                    }
                });
            }

            @Override
            public void onFinish() {
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //TimeIndicator.this.setText("Time finished, game over");
                        mContext.gameOver("Time exceeded");
                    }
                });
            }
        };
        mCountDownTimer.start();
    }

    public void stopCounting(String msg){
        setText("Game over\n" + msg);
        mCountDownTimer.cancel();
    }
}
