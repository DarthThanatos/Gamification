package vobis.example.com.gamification.topdownminigame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PiecesColumn extends View {

    private int mJ;
    private PiecesRow mLuckyRow;
    private ArrayList<Piece> mPiecesInColumn;
    private int mPiecesAmount;
    private ShapeDrawable mRectDrawable;

    private float mWidth, mHeight;
    private Paint mStrokePaint;
    private Context mContext;

    private TimerTask mTimerTask;
    private Timer mTimer;

    private char pickLetter(){
        char res;
        Random random = new Random();
        do{
            int picked = random.nextInt('z' - 'a');
            res = (char) (picked + 'a');
        }while(res == mLuckyRow.getLetterAt(mJ));
        return res;
    }

    public void setColumnSelected(boolean selected){
        mStrokePaint = mRectDrawable.getPaint();
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mTimer.cancel();
        if(selected){
            mTimer = new Timer();
            mTimerTask = new SliderTask(mContext,mPiecesInColumn);
            mTimer.scheduleAtFixedRate(mTimerTask, 0, 100);
            mStrokePaint.setColor(Color.YELLOW);
            mStrokePaint.setStrokeWidth(5);
        }
        else{
            mStrokePaint.setColor(Color.BLACK);
            mStrokePaint.setStrokeWidth(1);
        }
    }

    private class SliderTask extends TimerTask{

        private Context mTaskContext;
        private ArrayList<Piece> mTaskPieces;

        public SliderTask(Context context, ArrayList<Piece> pieces){
            mTaskContext = context;
            mTaskPieces = pieces;
        }

        @Override
        public void run() {
            ((TopDownActivity)mTaskContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (Piece piece: mTaskPieces){
                        piece.slideDown(5);
                    }
                    invalidate();
                }
            });
        }
    }

    public PiecesColumn(final Context context, int j, PiecesRow luckyRow) {
        super(context);
        mContext = context;

        mJ = j;
        mLuckyRow = luckyRow;

        mWidth = GameArea.SCREEN_WIDTH;
        mHeight = GameArea.SCREEN_HEIGHT;

        mPiecesAmount = GameArea.ROWS_AMOUNT + 1;
        mPiecesInColumn = new ArrayList<>(mPiecesAmount);
        int randomSlide = Math.abs(new Random().nextInt())%GameArea.ROWS_AMOUNT;
        Piece piece;
        for (int i = 0; i < mPiecesAmount; i++){
            if (i != luckyRow.getRowIndex()){
                char letter = pickLetter();
                piece = new Piece(context, letter, i, j);

            }
            else{
                piece = luckyRow.getPieceAt(j);
            }
            piece.slideDown((int) (randomSlide * Piece.CUSTOM_HEIGHT) );
            mPiecesInColumn.add(piece);
        }


        mTimer = new Timer();
        mTimerTask = new SliderTask(context,mPiecesInColumn);

        RectShape rectShape = new RectShape();
        rectShape.resize(Piece.CUSTOM_WIDTH, GameArea.ROWS_AMOUNT * Piece.CUSTOM_HEIGHT);
        mRectDrawable = new ShapeDrawable(rectShape);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRectDrawable.getPaint().setStyle(Paint.Style.STROKE);

        canvas.save();
        canvas.translate(mJ * Piece.CUSTOM_WIDTH, 0);
        mRectDrawable.draw(canvas);
        canvas.restore();

        for(Piece piece : mPiecesInColumn){
            piece.draw(canvas);
        }

    }
}
