package vobis.example.com.gamification.snackbarnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.support.design.widget.Snackbar;
import android.widget.Toast;

import vobis.example.com.gamification.R;

public class SnackbarActivity extends ActionBarActivity {

    CoordinatorLayout mCoordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
        mCoordLayout = (CoordinatorLayout) findViewById(R.id.snackbar_coordinatior_layout);
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
                stackBuilder.addParentStack(SnackbarActivity.this);
                stackBuilder.addNextIntent(intent);
                PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());
                //startActivity(new Intent(SnackbarActivity.this, Surprise.class));
            }
        });
        snackbar.show();
    }
}
