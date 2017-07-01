package vobis.example.com.gamification.navdraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vobis.example.com.gamification.R;

/**
 * Created by Vobis on 2017-07-01.
 */
public class DrawerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> planetNames;

    public DrawerAdapter(Context context, ArrayList<String> planetNames){
        this.context = context;
        this.planetNames = planetNames;
    }

    @Override
    public int getCount() {
        return planetNames.size();
    }

    @Override
    public Object getItem(int position) {
        return planetNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View linearContainer, ViewGroup listView) {
        if (linearContainer == null){
            linearContainer = LayoutInflater.from(context).inflate(R.layout.drawer_enhanced_item, listView, false);
        }
        TextView tv = (TextView) linearContainer.findViewById(R.id.drawer_item_name);
        ImageView iv = (ImageView) linearContainer.findViewById(R.id.drawer_item_image);

        tv.setText(getItem(position).toString());
        String planetName = (String) planetNames.get(position);

        int imageId = context.getResources().getIdentifier(planetName.toLowerCase(), "drawable", context.getPackageName() );
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imageId);
        int icon_size = 30;
        bmp = Bitmap.createScaledBitmap(bmp, icon_size, icon_size, false);
        iv.setImageBitmap(bmp);

        return linearContainer;
    }
}
