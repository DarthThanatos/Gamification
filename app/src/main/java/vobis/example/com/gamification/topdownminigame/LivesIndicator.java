package vobis.example.com.gamification.topdownminigame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import vobis.example.com.gamification.R;

public class LivesIndicator extends LinearLayout {

    private static final int MAX_LIVES = 3;
    private int lives = 3;
    private int mWidth, mHeight, mHeartWidth;

    private Context mContext;

    private ArrayList<ImageSwitcher>hearts;

    private void setup(Context context){
        mHeight = 50;
        mWidth = 100;
        mContext = context;
        hearts = new ArrayList<>(MAX_LIVES);
        //setBackgroundColor(Color.WHITE);
        mHeartWidth = mWidth / MAX_LIVES;
        setOrientation(LinearLayout.HORIZONTAL);

        for (int i = 0; i < MAX_LIVES; i ++ ){
            ImageSwitcher heartHolder = new ImageSwitcher(mContext);
            for (int j = 0; j<2; j++) heartHolder.addView(new ImageView(mContext));
            heartHolder.setImageResource(R.drawable.heart);
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.out_anim);
            heartHolder.setOutAnimation(animation);
            hearts.add(heartHolder);
            LinearLayout.LayoutParams params = new LayoutParams(mHeartWidth, ViewGroup.LayoutParams.MATCH_PARENT);
            heartHolder.setLayoutParams(params);
            addView(heartHolder);
        }
    }

    public void fail(){
        if (lives == 0) return;
        hearts.get(lives-1).setImageResource(0);
        lives --;

    }

    public int getLives(){
        return lives;
    }

    public LivesIndicator(Context context) {
        super(context);
        setup(context);
    }

    public LivesIndicator(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        setup(context);
    }



}
