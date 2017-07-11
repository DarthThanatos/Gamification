package vobis.example.com.gamification.me2minigame.gameconfig.generator;

import java.util.Arrays;
import java.util.Random;

import vobis.example.com.gamification.me2minigame.GameMap;
import vobis.example.com.gamification.me2minigame.TileDesc;
import vobis.example.com.gamification.me2minigame.gameconfig.CodesSelector;
import vobis.example.com.gamification.me2minigame.gameconfig.generator.RowGenerator;

public class EasyGenerator implements RowGenerator {

    private int mRowsChanged = 0;
    private int errorRow = -1, errorColumn = -1;

    @Override
    public void replaceOldRow(TileDesc[] row, CodesSelector codesSelector) {
        for (int j = 0; j < GameMap.COLUMNS_AMOUNT; j++){
            int resId = Math.abs(new Random().nextInt())% 6;
            boolean soughtTile = codesSelector.getCurrentResId() == resId;
            row[j].setCodeResourceIndex(resId);
            row[j].setSought(soughtTile);
            row[j].setFail(false);
        }
        if(mRowsChanged == GameMap.ROWS_AMOUNT){
            errorRow = Math.abs(new Random().nextInt())%GameMap.ROWS_AMOUNT;
            errorColumn = Math.abs(new Random().nextInt())%GameMap.COLUMNS_AMOUNT;
            mRowsChanged = 0;
        }
        mRowsChanged ++;
        if(errorRow != -1 && errorColumn != -1){
            TileDesc tileDesc = row[errorColumn];
            if(tileDesc.getView().getRowIndex() == errorRow){
                tileDesc.setCodeResourceIndex(GameMap.errorResourceIndex);
                tileDesc.setFail(true);
            }
        }
    }

    @Override
    public void produceNewRow(TileDesc[] row, CodesSelector codesSelector) {
        for (int j = 0; j < GameMap.COLUMNS_AMOUNT; j++){
            int resId = Math.abs(new Random().nextInt())% 6;
            boolean soughtTile = codesSelector.getCurrentResId() == resId;
            row[j] = new TileDesc(false, soughtTile, resId, codesSelector);
        }

    }
}
