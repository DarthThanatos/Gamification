package vobis.example.com.gamification.me2minigame.gameconfig.generator;

import java.util.Random;

import vobis.example.com.gamification.me2minigame.GameMap;
import vobis.example.com.gamification.me2minigame.TileDesc;
import vobis.example.com.gamification.me2minigame.gameconfig.generator.RowGenerator;

public class EasyGenerator implements RowGenerator {

    private int mRowsChanged = 0;
    private int errorRow = -1, errorColumn = -1;

    @Override
    public void replaceOldRow(TileDesc[] row) {
        for (int j = 0; j < GameMap.COLUMNS_AMOUNT; j++){
            row[j].setCodeResourceIndex(Math.abs(new Random().nextInt())% 6);
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
            }
        }
    }

    @Override
    public void produceNewRow(TileDesc[] row) {
        for (int j = 0; j < GameMap.COLUMNS_AMOUNT; j++){
            row[j] = new TileDesc(false, Math.abs(new Random().nextInt())% 6);
        }

    }
}
