package vobis.example.com.gamification.topdownminigame;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;

import java.util.ArrayList;

public class PiecesRow extends View {

    private int mIndex;
    private int mVerticalPiecesAmount;
    private ArrayList<Piece> luckyPieces;
    private static String solution = "SOLUTION";

    public static final int COLUMNS_AMOUNT = solution.length();

    private ShapeDrawable mRectDrawable;
    private float mWidth, mHeight;

    public PiecesRow(Context context, int i) {
        super(context);
        mIndex = i;
        mWidth = GameArea.SCREEN_WIDTH;
        mHeight = GameArea.SCREEN_HEIGHT;
        mVerticalPiecesAmount = COLUMNS_AMOUNT;
        luckyPieces = new ArrayList<>(mVerticalPiecesAmount);
        for (int j = 0; j < mVerticalPiecesAmount; j++){
            Piece piece = new Piece(context,solution.charAt(j),i,j);
            piece.setLucky();
            luckyPieces.add(piece);
        }

        RectShape rectShape = new RectShape();
        rectShape.resize(mWidth, Piece.CUSTOM_HEIGHT);
        mRectDrawable = new ShapeDrawable(rectShape);
    }

    public int getRowIndex(){
        return mIndex;
    }

    public char getLetterAt(int index){
        return solution.charAt(index);
    }

    public Piece getPieceAt(int index){
        return luckyPieces.get(index);
    }

    public boolean checkCorrect(int j){
        Piece luckyPiece = luckyPieces.get(j);
        float y = luckyPiece.getSlideY();
        float distanceAbove = mIndex * Piece.CUSTOM_HEIGHT - y;
        float distanceBelow = (mIndex + 1) * Piece.CUSTOM_HEIGHT - y;
        if( (0 <=  distanceAbove && distanceAbove <= 0.2 * Piece.CUSTOM_HEIGHT)
                || (0.8 * Piece.CUSTOM_HEIGHT <= distanceBelow && distanceBelow <Piece.CUSTOM_HEIGHT))
            return true;
        return false;
    }

    public boolean checkIntersect(PiecesColumn column){
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = mRectDrawable.getPaint();
        paint.setStyle(Paint.Style.STROKE);

        canvas.save();
        canvas.translate(0, mIndex * Piece.CUSTOM_HEIGHT);
        mRectDrawable.draw(canvas);
        canvas.restore();

    }
}
