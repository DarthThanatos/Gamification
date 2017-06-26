package vobis.example.com.gamification;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends Activity{

    private  static final int buttonWidth = 150;
    private static  final int buttonHeight = 75;

    private void setButtonWidth(int buttonId){
        Button btn = (Button) findViewById(buttonId);
        btn.setLayoutParams(new LinearLayout.LayoutParams(buttonWidth, buttonHeight));
    }

    private void setMoveAction(final TransitionButtonInfo tbi){
        Button btn = (Button) findViewById(tbi.btnId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), tbi.activity));
            }
        });
    }

    private class TransitionButtonInfo{

        public int btnId;
        public Class activity;

        public TransitionButtonInfo(int btnId, Class activity){
            this.btnId = btnId;
            this.activity = activity;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int btnId : Arrays.asList(R.id.gamefication, R.id.gallery, R.id.shakespear)) setButtonWidth(btnId);
        for (TransitionButtonInfo tbi :
                    Arrays.asList(
                        new TransitionButtonInfo(R.id.gamefication, Gamification.class),
                        new TransitionButtonInfo(R.id.shakespear, ShakespearActivity.class),
                        new TransitionButtonInfo(R.id.gallery, Gallery.class)
                    )
                ){
            setMoveAction(tbi);
        }
    }
}
