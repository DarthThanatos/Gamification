package vobis.example.com.gamification.me2minigame;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import vobis.example.com.gamification.me2minigame.gameconfig.GameConfig;

public class MEGameArea extends View {

    public static int WIDTH, HEIGHT;

    private GameConfig mGameConfig;
    private GameMap mGameMap;
    private MEMiniGameActivity mContext;

    private ArrayList<GameRow> mGameRows;

    public MEGameArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = (MEMiniGameActivity) context;
        mGameRows = new ArrayList<>(GameMap.ROWS_AMOUNT + 1);

        WIDTH = getResources().getDisplayMetrics().widthPixels;
        HEIGHT = getResources().getDisplayMetrics().heightPixels;

        ValueAnimator valueAnimator = ObjectAnimator.ofInt(this, "backgroundColor", Color.GREEN, Color.BLUE);
        valueAnimator.setDuration(3000);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
    }

    public void fillRowsWithContent(GameMap gameMap){
        mGameMap = gameMap;

        for(int i = 0; i < GameMap.ROWS_AMOUNT+1; i++){
            GameRow gameRow = new GameRow(mContext, gameMap.getModelRow(i));
            mGameRows.add(gameRow);
        }
    }

}
