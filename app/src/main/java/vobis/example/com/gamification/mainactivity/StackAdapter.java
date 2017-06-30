package vobis.example.com.gamification.mainactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import vobis.example.com.gamification.R;

public class StackAdapter extends BaseAdapter {

    ArrayList<StackItem> arrayList, copy;
    LayoutInflater inflater;
    Context context;

    public StackAdapter(Context context, ArrayList<StackItem> arrayList){
        this.arrayList = arrayList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View frameContainer, ViewGroup scrollView) {
        StackItem si = arrayList.get(position);
        if(frameContainer == null){
            frameContainer = inflater.inflate(R.layout.main_activity_stack_item, scrollView, false);
        }
        ViewParent parent = si.getParent();
        if(parent != null)((FrameLayout)parent).removeView(si);
        ((FrameLayout)frameContainer).addView(si);
        return frameContainer;
    }

}
