package vobis.example.com.gamification.me2minigame;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vobis.example.com.gamification.me2minigame.gameconfig.GameConfig;

public class MEGameArea extends View {

    public static int WIDTH, HEIGHT;

    private GameConfig mGameConfig;
    private GameMap mGameMap;
    private MEMiniGameActivity mContext;

    private ArrayList<GameRow> mGameRows;
    private TimerTask mTask;
    private Timer mTimer;
    private Controller mController;


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
        //valueAnimator.start();

    }

    public void cleanup(){
        if(mTimer!=null)mTimer.cancel();
        for(GameRow gameRow: mGameRows){
            gameRow.cleanup();
        }
    }

    public void fillRowsWithContent(GameMap gameMap, Controller controller){
        mGameMap = gameMap;

        for(int i = 0; i < GameMap.ROWS_AMOUNT+1; i++){
            GameRow gameRow = new GameRow(mContext, gameMap.getModelRow(i), i, controller);
            mGameRows.add(gameRow);
        }

        try {
            mGameMap.setSelectedTile(0,0);
        } catch (TileDesc.WrongTileException e) {
            e.printStackTrace();
        } catch (TileView.NotInViewAreaException e) {
            e.printStackTrace();
        }

        mTask = new TimerTask() {
            @Override
            public void run() {
                for (GameRow gameRow: mGameRows){
                    gameRow.slideDown();
                }
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MEGameArea.this.invalidate();

                    }
                });
            }
        };

        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(mTask, 0, 100);
    }

    public void stopGame(){
        mTimer.cancel();
        System.out.println("invalidating game area ");
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (GameRow gameRow : mGameRows){
            gameRow.draw(canvas);
        }
    }
}
