package vobis.example.com.gamification.me2minigame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import vobis.example.com.gamification.R;
import vobis.example.com.gamification.mainactivity.MainActivity;
import vobis.example.com.gamification.me2minigame.gameconfig.Easy;
import vobis.example.com.gamification.me2minigame.gameconfig.GameConfig;
import vobis.example.com.gamification.me2minigame.gameconfig.Hard;
import vobis.example.com.gamification.me2minigame.gameconfig.Medium;
import vobis.example.com.gamification.me2minigame.statuspanel.StatusPanel;

public class MEMiniGameActivity extends ActionBarActivity {

    public static final String CONFIG_KEY = "vobis.example.com.gamification.me2minigame.config_key";
    public GameConfig mSelectedConfig;

    private Controller mController;
    private MEGameArea mGameArea;
    private StatusPanel mStatusPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSelectedConfig = (GameConfig)getIntent().getSerializableExtra(CONFIG_KEY);
        mSelectedConfig = mSelectedConfig == null ? new Easy() : mSelectedConfig;
        Toast.makeText(this, mSelectedConfig.getName(), Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_memini_game);
        mGameArea = (MEGameArea) findViewById(R.id.me_game_area);
        mController = new Controller(mGameArea, mSelectedConfig);

        mStatusPanel = (StatusPanel) findViewById(R.id.status_panel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memini_game, menu);
        return true;
    }


    private void customRecreate(GameConfig gameConfig){
        getIntent().putExtra(CONFIG_KEY, gameConfig);
        recreate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.easy:
                System.out.println("Easy");
                customRecreate(new Easy());
                break;
            case R.id.medium:
                System.out.println("Medium");
                customRecreate(new Medium());
                break;
            case R.id.hard:
                System.out.println("Hard");
                customRecreate(new Hard());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void moveDown(View view) {
        mController.moveDown();
    }

    public void moveUp(View view) {
        mController.moveUp();
    }

    public void moveRight(View view) {
        mController.moveRight();
    }

    public void moveLeft(View view) {
        mController.moveLeft();
    }

    public void acceptTile(View view) {
        mStatusPanel.reflectCorrectChoice();
    }

    public void gameOver(){

    }

    public void gameWon(){

    }

    public void restartGame(MenuItem item) {
        customRecreate(mSelectedConfig);
    }
}
