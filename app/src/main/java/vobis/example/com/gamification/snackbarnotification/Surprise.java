package vobis.example.com.gamification.snackbarnotification;

import android.app.NotificationManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import vobis.example.com.gamification.R;

public class Surprise extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surprise);
        MediaPlayer mp = MediaPlayer.create(this, R.raw.surprise);
        mp.start();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(SnackbarActivity.noticode);
    }

    public void goBack(View view) {
        finish();
    }
}
