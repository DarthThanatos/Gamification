package vobis.example.com.gamification.topdownminigame;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class Piece extends View {

    private boolean correct = false;
    private char mDisplayLetter;
    private boolean mIsTheLuckyOne = false;
    private ShapeDrawable mDrawable;
    private int mI, mJ;
    private float x,y;
    public static float CUSTOM_HEIGHT;
    public static float CUSTOM_WIDTH;

    private Paint textPaint;

    private void setColors(int r,int g, int b){
        int  mLightColor = 0xff000000 | r << 16 | g << 8 << b;
        int mDarkColor = 0xff000000 | r / 4 << 16 | g / 4 << 8 | b / 4;
        Paint paint = mDrawable.getPaint();
        RadialGradient radialGradient =
                new RadialGradient(35.0f, 15.0f, 50f, mLightColor, mDarkColor, Shader.TileMode.CLAMP);
        paint.setShader(radialGradient);

    }

    public void setLucky(){
        setColors(0,255,0);
        mIsTheLuckyOne = true;
    }

    public boolean isLucky(){
        return mIsTheLuckyOne;
    }


    public Piece(Context context, char displayLetter, int i, int j){
        super(context);
        mDisplayLetter = displayLetter;
        OvalShape circle = new OvalShape();
        mDrawable = new ShapeDrawable(circle);
        setColors(255,0,0);
        CUSTOM_WIDTH  = GameArea.SCREEN_WIDTH / GameArea.COLUMNS_AMOUNT;
        CUSTOM_HEIGHT = GameArea.SCREEN_HEIGHT / GameArea.ROWS_AMOUNT;
        circle.resize(CUSTOM_WIDTH, CUSTOM_HEIGHT);

        mI = i;
        mJ = j;
        y = mI * CUSTOM_HEIGHT;
        x = mJ * CUSTOM_WIDTH;

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);

        long velocity = 100 * 1000; // 100px/s
        float distanceFrom = GameArea.SCREEN_HEIGHT - CUSTOM_HEIGHT - mI * CUSTOM_HEIGHT;
        long durationFrom = distanceFrom > 0 ? (long)(velocity / distanceFrom) : 0;
        final ValueAnimator animatorFromOrigin = ObjectAnimator.ofFloat(this, "y", y, GameArea.SCREEN_HEIGHT - CUSTOM_HEIGHT);
        animatorFromOrigin.setDuration(durationFrom);
        animatorFromOrigin.setRepeatCount(0);
        animatorFromOrigin.setInterpolator(new LinearInterpolator());

        float distanceTo = mI * CUSTOM_HEIGHT;
        long durationTo = distanceTo > 0 ? (long)(velocity / distanceTo) : 0;
        final ValueAnimator animatorToOrigin = ObjectAnimator.ofFloat(this, "y", 0, mI * CUSTOM_HEIGHT);
        animatorToOrigin.setDuration(durationTo);
        animatorToOrigin.setRepeatCount(0);
        animatorToOrigin.setInterpolator(new LinearInterpolator());

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatorFromOrigin).before(animatorToOrigin);

        animatorToOrigin.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.start();
            }
        });

        //animatorSet.start();
    }

    public void slideDown(int step){
        y += step;
        if(y > GameArea.SCREEN_HEIGHT){
            float delta = y - GameArea.SCREEN_HEIGHT;
            y = -CUSTOM_HEIGHT + delta + 5;
        }
    }

    public void setY(float y){
        this.y = y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //System.out.println("Draw piece " + "(" + mI + ", " + mJ + ")");
        canvas.save();

        canvas.translate(x,y);
        mDrawable.draw(canvas);
        canvas.drawText(Character.toString(mDisplayLetter),CUSTOM_WIDTH/2, CUSTOM_HEIGHT/2, textPaint);
        canvas.restore();

    }
}
