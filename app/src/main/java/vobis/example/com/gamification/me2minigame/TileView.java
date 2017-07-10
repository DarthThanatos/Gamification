package vobis.example.com.gamification.me2minigame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.view.View;
import android.widget.Toast;

public class TileView extends View {

    private static int TILE_WIDTH, TILE_HEIGHT;
    private TileDesc mTileDesc;
    private int mVerticalIndex, mRowIndex;
    private Bitmap mBitmap;
    private Rect mBpmRect;
    private ShapeDrawable mFrame;
    private float mX;

    public TileView(Context context, TileDesc tileDesc, int verticalIndex, int rowIndex) {
        super(context);
        mTileDesc = tileDesc;
        tileDesc.setView(this);
        mVerticalIndex = verticalIndex;
        mRowIndex = rowIndex;
        int resourceId = GameMap.idToResource[tileDesc.getmCodeResourceIndex()];
        mBitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        TILE_WIDTH = MEGameArea.WIDTH / GameMap.COLUMNS_AMOUNT;
        TILE_HEIGHT = MEGameArea.HEIGHT / GameMap.ROWS_AMOUNT;
        mBpmRect = new Rect(0,0,TILE_WIDTH,TILE_HEIGHT);
        mX = TILE_WIDTH * verticalIndex;

        RectShape rectShape = new RectShape();
        rectShape.resize(TILE_WIDTH, TILE_HEIGHT);
        mFrame = new ShapeDrawable(rectShape);

        Paint p = mFrame.getPaint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(3);
        p.setColor(Color.WHITE);
    }

    public void updateBmp(int codeBmpIndex){
        cleanup();
        int resourceId = GameMap.idToResource[codeBmpIndex];
        mBitmap = BitmapFactory.decodeResource(getResources(), resourceId);
    }

    public int getRowIndex(){
        return mRowIndex;
    }

    public void setSelected(boolean selected){
        Paint p = mFrame.getPaint();
        if(selected) {
            p.setStrokeWidth(5);
            p.setColor(Color.GREEN);
        }
        else{
            p.setStrokeWidth(3);
            p.setColor(Color.WHITE);
        }

    }

    @Override
    protected void onDraw(Canvas canvas){
        if (mBitmap == null) return;
        canvas.save();
        canvas.translate(mX, 0);
        canvas.drawBitmap(mBitmap, null, mBpmRect, null);
        mFrame.draw(canvas);
        canvas.restore();
    }

    public void cleanup(){
        mBitmap.recycle();
        mBitmap = null;
    }

}
