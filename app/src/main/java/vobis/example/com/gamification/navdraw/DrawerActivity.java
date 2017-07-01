package vobis.example.com.gamification.navdraw;

import android.app.Fragment;
import android.content.res.Configuration;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import vobis.example.com.gamification.R;

public class DrawerActivity extends AppCompatActivity {


    private DrawerLayout mDrawerContainer;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToogle;

    private String[] mPlanetsList;
    private CharSequence mTitle, mDrawerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        mDrawerContainer = (DrawerLayout)findViewById(R.id.drawer_container);
        mDrawerList = (ListView) findViewById(R.id.drawer_list);

        mDrawerContainer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mPlanetsList = getResources().getStringArray(R.array.planets_names);
        //mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_item, mPlanetsList));
        mDrawerList.setAdapter(new DrawerAdapter(this, new ArrayList<String>(Arrays.asList(mPlanetsList))));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mTitle = mDrawerTitle = getTitle();
        mDrawerToogle = new ActionBarDrawerToggle(this,mDrawerContainer, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                //invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View view){
                super.onDrawerOpened(view);
                getSupportActionBar().setTitle(mTitle);
                //invalidateOptionsMenu();
            }
        };
        mDrawerContainer.setDrawerListener(mDrawerToogle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if(savedInstanceState == null) selectItem(0);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mDrawerToogle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mDrawerToogle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        boolean drawerOpen = mDrawerContainer.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    private class DrawerItemClickListener implements  ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position){
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.PLANET_FRAGMENT_ARG, position);
        fragment.setArguments(args);

        getFragmentManager().beginTransaction().replace(R.id.drawer_frame, fragment).commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetsList[position]);
        mDrawerContainer.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title){
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToogle.onOptionsItemSelected(item)) return true;
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
