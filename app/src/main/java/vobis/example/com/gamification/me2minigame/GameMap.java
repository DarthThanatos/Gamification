package vobis.example.com.gamification.me2minigame;

import android.media.tv.TvContract;

import vobis.example.com.gamification.R;
import vobis.example.com.gamification.me2minigame.gameconfig.GameConfig;

import static vobis.example.com.gamification.me2minigame.GameMap.COLUMNS_AMOUNT;

//Class for aggragating and navigating through tileDescs
public class GameMap {
    public static final int COLUMNS_AMOUNT = 3;
    public static final int ROWS_AMOUNT = 4;

    private TileDesc mSelectedTile;

    public static final int[] idToResource
            = {R.drawable.code1, R.drawable.code2,R.drawable.code3,R.drawable.code4,R.drawable.code5,R.drawable.code6, R.drawable.error_code};

    public static int errorResourceIndex;

    public void setSelectedTile(int i, int j) throws TileDesc.WrongTileException, TileView.NotInViewAreaException {
        if (mSelectedTile != null)
            System.out.println("Changing from (" + mSelectedTile.getView().getRowIndex() + ", " + mSelectedTile.getView().getVerticalIndex() +
            ") to: (" + i + ", " + j + ")");
        else
            System.out.println("Moving to: (" + i + ", " + j + ")");
        try {

            mTileDescs[i][j].setSelected(true);
        }catch(TileDesc.WrongTileException e){

            if(mSelectedTile != null){
                mSelectedTile.setSelected(false);
            }
            mSelectedTile = mTileDescs[i][j];

            throw e;

        }

        if(mSelectedTile != null){
            mSelectedTile.setSelected(false);
        }
        mSelectedTile = mTileDescs[i][j];
        /*
        try{
            throw new Exception();
        }catch(Exception e){
            e.printStackTrace();
        } // ^ for debugging purposes*/
    }

    public void acceptTile(int i, int j)throws TileDesc.WrongTileException{
        mTileDescs[i][j].accept();
    }

    private GameConfig mGameConfig;
    private TileDesc[][] mTileDescs;

    public GameMap(GameConfig gameConfig){
        mGameConfig = gameConfig;
        errorResourceIndex = idToResource.length-1;
        mTileDescs = new TileDesc[ROWS_AMOUNT+1][COLUMNS_AMOUNT];
        for (int i = 0; i < ROWS_AMOUNT + 1; i++){
            mGameConfig.getGenerator().produceNewRow(mTileDescs[i], mGameConfig.getSelector());
        }
    }

    public TileDesc[] getModelRow(int index){
        return mTileDescs[index];
    }
}
