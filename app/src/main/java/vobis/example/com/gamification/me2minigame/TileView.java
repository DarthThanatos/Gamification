package vobis.example.com.gamification.me2minigame;

import android.content.Context;
import android.view.View;

public class TileView extends View {

    public static int TILE_SIZE;
    private TileDesc mTileDesc;

    public TileView(Context context, TileDesc tileDesc) {
        super(context);
        mTileDesc = tileDesc;
    }
}
