package vobis.example.com.gamification.me2minigame;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

import vobis.example.com.gamification.me2minigame.gameconfig.generator.EasyGenerator;
import vobis.example.com.gamification.me2minigame.gameconfig.generator.RowGenerator;

public class GameRow extends View {

    private ArrayList<TileView> tileViews;

    private Context mContext;
    private RowGenerator mRowGenerator;

    private float slideY;

    public GameRow(Context context, TileDesc[] tileDescs) {
        super(context);
        mContext = context;
        mRowGenerator = new EasyGenerator();
    }
}
