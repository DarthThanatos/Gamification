package vobis.example.com.gamification;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;


public class Gallery extends Activity {

    private static final String IMAGEVIEW_TAG = "icon bitmap";
    private  static final int squareSide = 75;


    private View.OnDragListener imageMover = new View.OnDragListener() {

        @Override
        public boolean onDrag(View v, DragEvent e) {
            View view = v.getRootView();
            switch (e.getAction()) {
                case DragEvent.ACTION_DROP:
                    view.setX(e.getX() - (view.getWidth() / 2));
                    view.setY(e.getY() - (view.getHeight() / 2));
                    view.invalidate();
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DRAG_STARTED:
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.invalidate();
                    return true;
                default:
                    break;
            }
            return true;
        }
    };

    private View.OnLongClickListener clickListener =  new View.OnLongClickListener() {
        public boolean onLongClick(View v) {
            GridViewItem gvi = (GridViewItem) v;
            ClipData.Item item = new ClipData.Item((String) v.getTag());
            ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
            View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);
            GridViewItem.GridItemData gid = gvi.getGridItemData();
            v.startDrag(dragData,  // the data to be dragged
                    myShadow,  // the drag shadow builder
                    gid,      // no need to use local data
                    0          // flags (not currently used, set to 0)
            );
            return true;
        }
    };

    private View.OnDragListener colorChanger = new View.OnDragListener(){
        public boolean onDrag(View view, DragEvent event) {
            final int action = event.getAction();
            GridViewItem v = (GridViewItem) view;
            switch(action) {

                case DragEvent.ACTION_DRAG_STARTED:
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        v.setColorFilter(Color.BLUE);
                        v.invalidate();
                        return true;
                    }
                    return false;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setColorFilter(Color.GREEN);
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DRAG_LOCATION:
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setColorFilter(Color.BLUE);
                    v.invalidate();
                    return true;
                case DragEvent.ACTION_DROP:
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    CharSequence dragData = item.getText();
                    GridViewItem.GridItemData gid = (GridViewItem.GridItemData)event.getLocalState();
                    int tmp = v.getImageId();
                    v.swapImage(gid.gviImageId);
                    gid.gvi.swapImage(tmp);
                    Toast.makeText(getApplicationContext(), "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
                    v.clearColorFilter();
                    v.invalidate();
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    v.clearColorFilter();
                    v.invalidate();
                    if (event.getResult()) {
                        //Toast.makeText(getApplicationContext(), "The drop was handled.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //Toast.makeText(getApplicationContext(), "The drop didn't work.", Toast.LENGTH_SHORT).show();
                    }
                    return true;

                default:
                    Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                    break;
            }

            return false;
        }
    };

    private void setup(){
        GridLayout layout = (GridLayout) findViewById(R.id.grid_container);
        int [] resourceIds = {R.raw.arrow_glossy_left_blue, R.raw.dweller, R.raw.right_arrow};
        for (int i = 0; i < layout.getRowCount(); i++){
            for (int j = 0; j < layout.getColumnCount(); j++){

                GridViewItem gridViewItem = new GridViewItem(this, i * layout.getColumnCount() + j, resourceIds[j]);
                //gridViewItem.setColorFilter(Color.BLACK);

                Bitmap mIconBitmap = BitmapFactory.decodeResource(getResources(), resourceIds[j]);
                gridViewItem.setImageBitmap(mIconBitmap);

                gridViewItem.setTag(IMAGEVIEW_TAG);
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();

                gridViewItem.setAdjustViewBounds(true);
                gridViewItem.setMaxWidth(squareSide);
                gridViewItem.setMaxHeight(squareSide);
                gridViewItem.setOnLongClickListener(clickListener);
                gridViewItem.setOnDragListener(colorChanger);

                param.height = GridLayout.LayoutParams.WRAP_CONTENT;
                param.width = GridLayout.LayoutParams.WRAP_CONTENT;
                param.rightMargin = 5;
                param.topMargin = 5;
                param.setGravity(Gravity.CENTER);
                param.columnSpec = GridLayout.spec(j);
                param.rowSpec = GridLayout.spec(i);
                gridViewItem.setLayoutParams (param);
                layout.addView(gridViewItem);
            }
        }
    }

    private void trialSetup(){
        GridLayout gridLayout = (GridLayout)findViewById(R.id.grid_container);
        gridLayout.removeAllViews();

        int total = 9;
        int column = 3;
        int row = total / column;
        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row + 1);
        for(int i =0, c = 0, r = 0; i < total; i++, c++)
        {
            if(c == column)
            {
                c = 0;
                r++;
            }
            ImageView oImageView = new ImageView(this);
            oImageView.setAdjustViewBounds(true);
            oImageView.setMaxWidth(squareSide);
            oImageView.setMaxHeight(squareSide);
            oImageView.setOnLongClickListener(clickListener);
            oImageView.setOnDragListener(colorChanger);
            Bitmap mIconBitmap = BitmapFactory.decodeResource(getResources(), R.raw.happy);
            oImageView.setImageBitmap(mIconBitmap);
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.rightMargin = 5;
            param.topMargin = 5;
            param.setGravity(Gravity.CENTER);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            oImageView.setLayoutParams (param);
            gridLayout.addView(oImageView);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        setup();
    }


    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        private static Drawable shadow;

        public MyDragShadowBuilder(View v) {
            super(v);
            shadow = new ColorDrawable(Color.LTGRAY);
        }

        private int width, height;

        @Override
        public void onProvideShadowMetrics (Point size, Point touch) {
            width = getView().getWidth();
            height = getView().getHeight();
            shadow.setBounds(0, 0, width, height);
            size.set(width, height);
            touch.set(width / 2, height / 2);
            System.out.println("In provide metrics in shadow builder (" + getView().getX() + ", " + getView().getY() + ")");
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            shadow.draw(canvas);
        }
    }
}
