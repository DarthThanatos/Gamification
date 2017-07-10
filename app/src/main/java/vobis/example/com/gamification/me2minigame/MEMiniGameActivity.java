package vobis.example.com.gamification.me2minigame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import vobis.example.com.gamification.R;
import vobis.example.com.gamification.mainactivity.MainActivity;
import vobis.example.com.gamification.mainactivity.StackItem;
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

    private boolean gameLasts = true;

    private void setup(){
        mSelectedConfig = (GameConfig)getIntent().getSerializableExtra(CONFIG_KEY);
        mSelectedConfig = mSelectedConfig == null ? new Easy() : mSelectedConfig;
        Toast.makeText(this, mSelectedConfig.getName(), Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_memini_game);
        mGameArea = (MEGameArea) findViewById(R.id.me_game_area);
        mController = new Controller(mGameArea, mSelectedConfig);

        mStatusPanel = (StatusPanel) findViewById(R.id.status_panel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mGameArea != null)mGameArea.cleanup();
        setup();
        gameLasts = true;
    }

    @Override
    public void onPause(){
        super.onPause();  // Always call the superclass method first
        mGameArea.cleanup();
        System.out.println("MEMiniGame activity onPause cleanup");
    }

    @Override
    public void onResume(){
        super.onResume();
        if(mGameArea != null)mGameArea.cleanup();
        setup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memini_game, menu);
        return true;
    }


    private void customRecreate(GameConfig gameConfig){
        mGameArea.stopGame();
        mStatusPanel.stopGame("");
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
        if(!gameLasts) return;
        try {
            mController.moveDown();
        } catch (TileDesc.WrongTileException e) {
            mStatusPanel.reflectMistake();
        }
    }

    public void moveUp(View view) {
        if(!gameLasts) return;
        try {
            mController.moveUp();
        } catch (TileDesc.WrongTileException e) {
            mStatusPanel.reflectMistake();
        }
    }

    public void moveRight(View view) {
        if(!gameLasts) return;
        try {
            mController.moveRight();
        } catch (TileDesc.WrongTileException e) {
            mStatusPanel.reflectMistake();
        }
    }

    public void moveLeft(View view) {
        if(!gameLasts) return;
        try {
            mController.moveLeft();
        } catch (TileDesc.WrongTileException e) {
            mStatusPanel.reflectMistake();
        }
    }

    public void acceptTile(View view)
    {
        if(!gameLasts) return;
        try{
            mController.accept();
            mStatusPanel.reflectCorrectChoice();
        } catch (Exception e) { //TileDesc.WrongTile
            mStatusPanel.reflectMistake();
        }
    }

    public void gameOver(String msg){
        if(!gameLasts) return;
        gameLasts = false;
        mGameArea.stopGame();
        mStatusPanel.stopGame(msg);
        MediaPlayer mp = MediaPlayer.create(this,R.raw.fail);
        mp.start();
    }

    public void gameWon(String msg){
        if(!gameLasts) return;
        gameLasts = false;
        mGameArea.stopGame();
        mStatusPanel.stopGame(msg);
        MediaPlayer mp = MediaPlayer.create(this,R.raw.applause);
        mp.start();
    }

    public void restartGame(MenuItem item) {
        customRecreate(mSelectedConfig);
    }
}
