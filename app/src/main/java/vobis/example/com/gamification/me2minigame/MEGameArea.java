package vobis.example.com.gamification.me2minigame;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class MEGameArea extends View {

    public MEGameArea(Context context, AttributeSet attrs) {
        super(context, attrs);


        ValueAnimator valueAnimator = ObjectAnimator.ofInt(this, "backgroundColor", Color.GREEN, Color.BLUE);
        valueAnimator.setDuration(3000);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
    }
}
