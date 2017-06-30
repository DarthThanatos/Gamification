package vobis.example.com.gamification.mainactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StackItem extends ImageButton {
    public Integer imageId;
    public Class activity = null;
    public View.OnClickListener action;

    public StackItem(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public StackItem(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public StackItem(Context context, Integer imageId, Class activity, View.OnClickListener action){
        super(context);
        this.imageId = imageId;
        this.activity = activity;
        setOnClickListener(action);
        int width = 400, height = 200;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height, Gravity.CENTER);
        setBackgroundColor(Color.BLACK);
        setLayoutParams(params);

        setScaleType(ScaleType.MATRIX);
        setAdjustViewBounds(true);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), imageId);
        bmp = Bitmap.createScaledBitmap(bmp,width, height,false);
        setBackground(new BitmapDrawable(getResources(), bmp));

        //setBackgroundResource(imageId);
    }

    public Class getActivity() {
        return activity;
    }
}
