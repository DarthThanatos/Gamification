package vobis.example.com.gamification.topdownminigame;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import vobis.example.com.gamification.R;

public class TopDownActivity extends AppCompatActivity {

    private GameArea mGameArea;
    private LivesIndicator mLivesIndicator;

    private boolean gameEnded = false;

    private void setup(){
        mGameArea = (GameArea) findViewById(R.id.game_area);
        mLivesIndicator = (LivesIndicator) findViewById(R.id.lives_indicator);

    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_down);
        setup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_down, menu);
        return true;
    }

    private void setGameSpeed(int slideStep){
        mGameArea.setSlideStep(slideStep);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.low_speed:
                item.setChecked(true);
                setGameSpeed(1);
                return true;
            case R.id.medium_speed:
                item.setChecked(true);
                setGameSpeed(5);
                return true;
            case R.id.high_speed:
                item.setChecked(true);
                setGameSpeed(10);
                return true;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void switchColumn(View view) {
        if (gameEnded) return;
        try {
            boolean switched = mGameArea.switchColumn();
            if(!switched) mLivesIndicator.fail();
            if(mLivesIndicator.getLives() == 0) {
                mGameArea.gameOver();
                gameEnded = true;
            }
        }catch(GameArea.WinException e){
            mGameArea.gameWon();
            gameEnded = true;
        }
    }

    public void restartGame(MenuItem item) {
        recreate();
    }
}
