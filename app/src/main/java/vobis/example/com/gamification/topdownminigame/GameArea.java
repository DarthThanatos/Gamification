package vobis.example.com.gamification.topdownminigame;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vobis.example.com.gamification.R;

public class GameArea extends View {

    protected static final String SOLUTION = "SOLUTION";
    protected static final int COLUMNS_AMOUNT = SOLUTION.length();
    protected static final int ROWS_AMOUNT = 6;
    protected static int SCREEN_WIDTH, SCREEN_HEIGHT;
    private int selectedColumn = 0;

    private static final int RED = 0xffff0000;
    private static final int GREEN = 0xff00ff00;
    private static final int BLUE = 0xff0000ff;

    private Context mContext;

    private ArrayList<PiecesColumn> mColumns;
    private PiecesRow mLuckyRow;

    private LivesIndicator mLivesIndicator;

    public class WinException extends Exception{
        public WinException(String msg){
            super(msg);
        }
    }

    public void setSlideStep(int slideStep){
        for (PiecesColumn column: mColumns){
            column.setSlideStep(slideStep);
        }
    }

    public boolean switchColumn() throws WinException{
        boolean within_bounds = mLuckyRow.checkCorrect(selectedColumn);
        if(!within_bounds){
            Toast.makeText(mContext, "Not in row", Toast.LENGTH_SHORT).show();
            return false;
        }
        mColumns.get(selectedColumn).setColumnSelected(false);
        selectedColumn ++;
        if (selectedColumn == COLUMNS_AMOUNT) throw new WinException("You won!");
        //selectedColumn = (selectedColumn + 1) % COLUMNS_AMOUNT;
        mColumns.get(selectedColumn).setColumnSelected(true);
        return true;
    }

    private void setup(Context context){
        mContext = context;
        int menuSize = 70, columnsAmount = COLUMNS_AMOUNT, luckyRowIndex = ROWS_AMOUNT/ 2 ;
        SCREEN_WIDTH = getContext().getResources().getDisplayMetrics().widthPixels;
        SCREEN_HEIGHT = getContext().getResources().getDisplayMetrics().heightPixels - menuSize;

        mLuckyRow = new PiecesRow(context, luckyRowIndex);

        mColumns = new ArrayList<>(columnsAmount);
        for (int j = 0; j < columnsAmount; j++){
            mColumns.add(new PiecesColumn(context, j, mLuckyRow));
        }

        mColumns.get(0).setColumnSelected(true);

        ValueAnimator valueAnimator = ObjectAnimator.ofInt(this, "backgroundColor", GREEN, BLUE);
        valueAnimator.setDuration(3000);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();

        invalidate();
    }

    public GameArea(Context context){
        super(context);
        setup(context);
    }

    public GameArea(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        setup(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (PiecesColumn column: mColumns){
            column.draw(canvas);
        }

        mLuckyRow.draw(canvas);
        invalidate();
    }


    public void gameOver(){
        mColumns.get(selectedColumn).stopSliding();
        MediaPlayer mp = MediaPlayer.create(mContext,R.raw.fail);
        mp.start();
    }

    public void gameWon(){
        mColumns.get(selectedColumn-1).stopSliding();
        MediaPlayer mp = MediaPlayer.create(mContext,R.raw.applause);
        mp.start();

    }
}
