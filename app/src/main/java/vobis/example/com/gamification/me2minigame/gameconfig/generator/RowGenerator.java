package vobis.example.com.gamification.me2minigame.gameconfig.generator;

import vobis.example.com.gamification.me2minigame.TileDesc;

public interface RowGenerator {

    public void replaceOldRow(TileDesc[] row);
    public void produceNewRow(TileDesc[]row);
}
