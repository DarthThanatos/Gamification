package vobis.example.com.gamification.me2minigame;

import android.media.tv.TvContract;

import vobis.example.com.gamification.R;
import vobis.example.com.gamification.me2minigame.gameconfig.GameConfig;

import static vobis.example.com.gamification.me2minigame.GameMap.COLUMNS_AMOUNT;

//Class for aggragating and navigating through tileDescs
public class GameMap {
    public static final int COLUMNS_AMOUNT = 3;
    public static final int ROWS_AMOUNT = 4;

    public static final int[] idToResource
            = {R.drawable.code1, R.drawable.code2,R.drawable.code3,R.drawable.code4,R.drawable.code5,R.drawable.code6};


    private GameConfig mGameConfig;
    private TileDesc[][] mTileDescs;

    public GameMap(GameConfig gameConfig){
        mGameConfig = gameConfig;
        mTileDescs = new TileDesc[ROWS_AMOUNT+1][COLUMNS_AMOUNT];
        for (int i = 0; i < ROWS_AMOUNT + 1; i++){
            mGameConfig.getGenerator().produceNewRow(mTileDescs[i]);
        }
    }

    public TileDesc[] getModelRow(int index){
        return mTileDescs[index];
    }
}
