package vobis.example.com.gamification.topdownminigame;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class GameArea extends View {

    protected static final String SOLUTION = "SOLUTION";
    protected static final int COLUMNS_AMOUNT = SOLUTION.length();
    protected static final int ROWS_AMOUNT = 6;
    protected static int SCREEN_WIDTH = 0, SCREEN_HEIGHT = 0;
    private int selectedColumn = 0;

    public void switchColumn(){
        mColumns.get(selectedColumn).setColumnSelected(false);
        selectedColumn = (selectedColumn + 1) % COLUMNS_AMOUNT;
        mColumns.get(selectedColumn).setColumnSelected(true);
    }

    private void setup(Context context){
        int menuSize = 70;
        SCREEN_WIDTH = getContext().getResources().getDisplayMetrics().widthPixels;
        SCREEN_HEIGHT = getContext().getResources().getDisplayMetrics().heightPixels - menuSize;
        int columnsAmount = COLUMNS_AMOUNT; //(int) (mHeight/Piece.mCustomHeight);
        int luckyRowIndex = ROWS_AMOUNT/ 2 ;
        mLuckyRow = new PiecesRow(context, luckyRowIndex);

        mColumns = new ArrayList<>(columnsAmount);
        for (int j = 0; j < columnsAmount; j++){
            mColumns.add(new PiecesColumn(context, j, mLuckyRow));
        }

        mColumns.get(0).setColumnSelected(true);

        ValueAnimator valueAnimator = ObjectAnimator.ofInt(this, "backgroundColor", RED, GREEN);
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

    private static final int RED = 0xffff0000;
    private static final int GREEN = 0xff00ff00;
    private static final int BLUE = 0xff0000ff;

    private ArrayList<PiecesColumn> mColumns;
    private PiecesRow mLuckyRow;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //System.out.println("Draw game area");
        for (PiecesColumn column: mColumns){
            column.draw(canvas);
        }

        mLuckyRow.draw(canvas);
        invalidate();
    }



}
