package vobis.example.com.gamification.me2minigame.gameconfig.generator;

import vobis.example.com.gamification.me2minigame.TileDesc;
import vobis.example.com.gamification.me2minigame.gameconfig.CodesSelector;

public interface RowGenerator {

    public void replaceOldRow(TileDesc[] row, CodesSelector codesSelector);
    public void produceNewRow(TileDesc[]row, CodesSelector codesSelector);
}
