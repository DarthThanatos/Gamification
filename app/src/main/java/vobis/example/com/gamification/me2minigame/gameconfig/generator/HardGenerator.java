package vobis.example.com.gamification.me2minigame.gameconfig.generator;

import java.util.Random;

import vobis.example.com.gamification.me2minigame.GameMap;
import vobis.example.com.gamification.me2minigame.TileDesc;

/**
 * Created by Vobis on 2017-07-06.
 */
public class HardGenerator implements RowGenerator {
    @Override
    public void replaceOldRow(TileDesc[] row) {

    }

    @Override
    public void produceNewRow(TileDesc[] row) {
        for (int j = 0; j < GameMap.COLUMNS_AMOUNT; j++){
            row[j] = new TileDesc(false, Math.abs(new Random().nextInt())% 6);
        }

    }
}
