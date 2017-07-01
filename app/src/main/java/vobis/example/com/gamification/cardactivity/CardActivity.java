package vobis.example.com.gamification.cardactivity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import vobis.example.com.gamification.R;

public class CardActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        if (savedInstanceState == null)
            getFragmentManager().beginTransaction().add(R.id.card_container, CardFragment.newInstance()).commit();

    }

}
