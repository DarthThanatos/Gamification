package vobis.example.com.gamification.me2minigame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.util.ArrayList;

import vobis.example.com.gamification.me2minigame.gameconfig.generator.RowGenerator;

public class GameRow extends View {

    private TileDesc[] mTileDescs;
    private ArrayList<TileView> mTileViews;

    private MEMiniGameActivity mContext;
    private RowGenerator mRowGenerator;

    private float mSlideY;
    private int mIndex;

    public GameRow(Context context, TileDesc[] tileDescs, int index) {
        super(context);
        mContext = (MEMiniGameActivity)context;
        mRowGenerator = mContext.mSelectedConfig.getGenerator();
        mTileDescs = tileDescs;

        mTileViews = new ArrayList<>(tileDescs.length);

        mIndex = index;
        int tileHeight = MEGameArea.HEIGHT / GameMap.ROWS_AMOUNT;
        mSlideY = index * tileHeight;

        for (int i = 0; i < tileDescs.length; i++){
            mTileViews.add(new TileView(context, tileDescs[i], i, index));
        }
    }

    public void slideDown(){
        mSlideY += mContext.mSelectedConfig.getSpeedController().getSlideStep();
        if(mSlideY > MEGameArea.HEIGHT){
            float delta = MEGameArea.HEIGHT - mSlideY;
            int tileHeight = MEGameArea.HEIGHT / GameMap.ROWS_AMOUNT;
            mSlideY = -tileHeight + delta + 2 * mContext.mSelectedConfig.getSpeedController().getSlideStep();
            mRowGenerator.replaceOldRow(mTileDescs);
        }
    }


    public void setSlideY(float slideY){
        mSlideY = slideY;
    }

    public int getIndex(){
        return mIndex;
    }

    @Override
    protected void onDraw(Canvas canvas){

        canvas.save();
        canvas.translate(0, mSlideY);
        for (TileView tileView : mTileViews){
            tileView.draw(canvas);
        }
        canvas.restore();
    }

    public void cleanup(){
        for(TileView tileView : mTileViews){
            tileView.cleanup();
        }
    }
}
