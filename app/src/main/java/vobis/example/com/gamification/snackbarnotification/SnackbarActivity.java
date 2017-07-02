package vobis.example.com.gamification.snackbarnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.support.design.widget.Snackbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import vobis.example.com.gamification.R;

public class SnackbarActivity extends ActionBarActivity {


    public static final int noticode = 1292;
    CoordinatorLayout mCoordLayout;
    volatile ImageSwitcher mImageView;

    Timer mTimer;
    private int index = 0;
    private final int[] resourceImages = {R.drawable.surprise_one, R.drawable.surprise_two, R.drawable.surprise_three};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
        mCoordLayout = (CoordinatorLayout) findViewById(R.id.snackbar_coordinatior_layout);
        mImageView  = (ImageSwitcher)findViewById(R.id.snack_gallery);
        for(int i = 0; i < 2; i++){
            mImageView.addView(new ImageView(SnackbarActivity.this));
        }

        Animation inAnim = AnimationUtils.loadAnimation(SnackbarActivity.this, R.anim.in_anim);
        Animation outAnim = AnimationUtils.loadAnimation(SnackbarActivity.this, R.anim.out_anim);
        mImageView.setInAnimation(inAnim);
        mImageView.setOutAnimation(outAnim);

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                SnackbarActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageResource(resourceImages[index++]);
                        if(index == resourceImages.length) index = 0;
                    }
                });
            }
        };
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(timerTask, 0, 3 * 1000);

    }


    @Override
    public void onStop(){
        mTimer.cancel();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_snackbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    public void createSnackBar(View view) {
        Snackbar snackbar = Snackbar.make(mCoordLayout, "Now click to unlock surprise!", Snackbar.LENGTH_LONG);
        snackbar.setAction("Move forward", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SnackbarActivity.this, "Surprise madafaka", Toast.LENGTH_SHORT).show();
                NotificationCompat.Builder builder
                        = new NotificationCompat
                            .Builder(SnackbarActivity.this)
                            .setSmallIcon(R.drawable.icon)
                            .setContentTitle("Surprise!")
                            .setContentText("Click to go to surprise");
                Intent intent = new Intent(SnackbarActivity.this, Surprise.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(SnackbarActivity.this);
                stackBuilder.addParentStack(Surprise.class);
                stackBuilder.addNextIntent(intent);
                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(noticode,builder.build());
            }
        });
        snackbar.show();
    }
}
