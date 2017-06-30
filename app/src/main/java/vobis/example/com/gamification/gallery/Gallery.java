package vobis.example.com.gamification.gallery;


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
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import vobis.example.com.gamification.R;


public class Gallery extends Activity implements GalleryFragment.OnGalleryFragmentInteractionListener{

    private static final String IMAGEVIEW_TAG = "icon bitmap";
    private  static final int squareSide = 75;
    private ArrayList<GridViewItem> imageParts;
    private boolean gameWon = false;
    private MediaPlayer mp, mp_fail;
    private GalleryFragment gf;

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
                    gid,      //local data
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
                    if ((!gameWon) && event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
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
                    Bitmap tmpImagePart = v.getImagePart();
                    int tmpIndex = v.getIndex();
                    v.swapImage(gid.gviImagePart, gid.gviIndex);
                    gid.gvi.swapImage(tmpImagePart, tmpIndex);
                    gameWon = gf.checkWin(imageParts);
                    if(gameWon) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.applause);
                        mp.start();
                    }
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


    private Bitmap[] createBitmapParts(int sourceId, int colAmount, int rowAmount){
        ArrayList<Bitmap> bmp = new ArrayList<Bitmap>(colAmount * rowAmount);
        Bitmap source = BitmapFactory.decodeResource(getResources(), sourceId);
        int width=source.getWidth();
        int height=source.getHeight();
        for(int i=0;i<rowAmount;i++){
            for(int j=0;j<colAmount;j++){
                int k = i * colAmount + j;

                bmp.add(k, Bitmap.createBitmap(source, (width * j) / rowAmount, (i * height) / colAmount, width / colAmount, height / rowAmount));
            }

        }
        //Collections.shuffle(bmp);
        return Arrays.copyOf(bmp.toArray(), bmp.size(), Bitmap[].class);
    }

    private void setup(){
        GridLayout layout = (GridLayout) findViewById(R.id.grid_container);
        gf = new GalleryFragment();
        getFragmentManager().beginTransaction().replace(R.id.gallery_layout, gf).commit();
        layout.removeAllViews();
        gameWon = false;
        imageParts = new ArrayList<>(layout.getColumnCount() * layout.getRowCount());
        int [] resourceIds = {R.raw.arrow_glossy_left_blue, R.raw.dweller, R.raw.right_arrow, R.raw.happy};
        int resourceId = resourceIds[new Random().nextInt(resourceIds.length)];
        Bitmap[] parts = createBitmapParts(resourceId, layout.getColumnCount(), layout.getRowCount());
        ArrayList<Integer> indexes = new ArrayList<>(); for (int i = 0; i< layout.getColumnCount() * layout.getRowCount(); i++) indexes.add(i);
        Collections.shuffle(indexes);
        for (int i = 0; i < layout.getRowCount(); i++){
            for (int j = 0; j < layout.getColumnCount(); j++){
                int k = i * layout.getColumnCount() + j;

                GridViewItem gridViewItem = new GridViewItem(this, indexes.get(k), parts[indexes.get(k)]);
                gridViewItem.setImageBitmap(parts[indexes.get(k)]);

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
                imageParts.add(gridViewItem);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        setup();
    }


    @Override
    public void onGameReset() {
        setup();
    }

    @Override
    public void onGameExit() {
        finish();
    }

    @Override
    public void onGameOver() {
        for (GridViewItem imagePart : imageParts){
            imagePart.setOnLongClickListener(null);
            imagePart.setOnDragListener(null);
        }
        mp_fail = MediaPlayer.create(this, R.raw.fail);
        mp_fail.start();
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
