package vobis.example.com.gamification.shakespear;

import android.app.Activity;
import android.os.Bundle;

import vobis.example.com.gamification.R;


public class ShakespearActivity extends Activity  implements EnumFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakespear);
    }

    @Override
    public void onFragmentInteraction(int position) {
        ShakespearFragment fragment = (ShakespearFragment) getFragmentManager().findFragmentById(R.id.fragmentTwo);
        fragment.displayAct(position);
    }
}
