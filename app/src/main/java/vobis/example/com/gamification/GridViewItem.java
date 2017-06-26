package vobis.example.com.gamification;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;

public class GridViewItem extends ImageView {

    private int index;
    private int imageId;

    public GridItemData getGridItemData(){
        return new GridItemData(this);
    }

    public int getIndex(){
        return index;
    }

    public int getImageId(){
        return imageId;
    }

    public GridViewItem(Context context, int index, int imageId) {
        super(context);
        this.index = index;
        this.imageId = imageId;
    }

    public void swapImage(int resource){
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resource);
        setImageBitmap(bmp);
        imageId = resource;
    }

    public GridViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }

    public class GridItemData{

        public GridItemData(GridViewItem gvi){
            this.gvi = gvi;
            gviIndex = index;
            gviImageId = imageId;
        }

        public GridViewItem gvi;
        public int gviIndex;
        public int gviImageId;
    }

}
