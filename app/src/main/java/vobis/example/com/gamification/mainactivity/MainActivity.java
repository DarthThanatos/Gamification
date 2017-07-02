package vobis.example.com.gamification.mainactivity;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.StackView;


import java.util.ArrayList;
import java.util.Arrays;

import vobis.example.com.gamification.R;
import vobis.example.com.gamification.accelerometer.AccelerometerPlayActivity;
import vobis.example.com.gamification.cardactivity.CardActivity;
import vobis.example.com.gamification.compass.CompassActivity;
import vobis.example.com.gamification.contactmanager.ContactAdder;
import vobis.example.com.gamification.floatingactivity.FloatingActivity;
import vobis.example.com.gamification.gallery.Gallery;
import vobis.example.com.gamification.gamification.Gamification;
import vobis.example.com.gamification.navdraw.DrawerActivity;
import vobis.example.com.gamification.shakespear.ShakespearActivity;
import vobis.example.com.gamification.snackbarnotification.SnackbarActivity;


public class MainActivity extends Activity{

    private  static final int buttonWidth = 150;
    private static  final int buttonHeight = 75;

    public void transitCallback(View v){
        System.out.println("transit callback");
    }

    private View.OnClickListener exitAction = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener transitAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            StackItem si = (StackItem) v;
            startActivity(new Intent(si.getContext(), si.getActivity()));
        }
    };

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

    private void boringSetup(){
        setContentView(R.layout.activity_main);
        for (int btnId : Arrays.asList(R.id.gamefication, R.id.gallery, R.id.shakespear, R.id.accelerometer, R.id.contact_manager, R.id.compass, R.id.exit_btn)) setButtonWidth(btnId);
        for (TransitionButtonInfo tbi :
                Arrays.asList(
                        new TransitionButtonInfo(R.id.gamefication, Gamification.class),
                        new TransitionButtonInfo(R.id.shakespear, ShakespearActivity.class),
                        new TransitionButtonInfo(R.id.gallery, Gallery.class),
                        new TransitionButtonInfo(R.id.accelerometer, AccelerometerPlayActivity.class),
                        new TransitionButtonInfo(R.id.compass, CompassActivity.class),
                        new TransitionButtonInfo(R.id.contact_manager, ContactAdder.class)

                )
                ){
            setMoveAction(tbi);
        }
        findViewById(R.id.exit_btn).setOnClickListener(exitAction);
    }


    private static  final Integer[] icons =  {R.drawable.shakespeare, R.drawable.icon, R.drawable.gamification, R.drawable.compass, R.drawable.contactmanager, R.drawable.exit};

    private void stackSetup(){
        setContentView(R.layout.activity_main_stack);
        StackView stackView = (StackView) findViewById(R.id.stack_container);
        ArrayList<StackItem> list = new ArrayList<>(Arrays.asList(
                new StackItem(this, R.drawable.floating, FloatingActivity.class, transitAction),
                new StackItem(this, R.drawable.snackbar, SnackbarActivity.class, transitAction),
                new StackItem(this, R.drawable.elevation, CardActivity.class, transitAction),
                new StackItem(this, R.drawable.drawer, DrawerActivity.class, transitAction),
                new StackItem(this, R.drawable.shakespeare, ShakespearActivity.class, transitAction),
                new StackItem(this, R.drawable.puzzle, Gallery.class, transitAction),
                new StackItem(this, R.drawable.gamification, Gamification.class, transitAction),
                new StackItem(this, R.drawable.compass, CompassActivity.class, transitAction),
                new StackItem(this, R.drawable.contactmanager, ContactAdder.class, transitAction),
                new StackItem(this, R.drawable.billard, AccelerometerPlayActivity.class, transitAction),
                new StackItem(this, R.drawable.exit,null, exitAction)
        ));
        StackAdapter adapter = new StackAdapter(MainActivity.this, list);
        stackView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stackSetup();
    }
}
