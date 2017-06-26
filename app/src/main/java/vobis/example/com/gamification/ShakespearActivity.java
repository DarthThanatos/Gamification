package vobis.example.com.gamification;

import android.app.Activity;
import android.os.Bundle;


public class ShakespearActivity extends Activity  implements EnumFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakespear);
    }

    @Override
    public void onFragmentInteraction(int position) {
        MainActivityFragment fragment = (MainActivityFragment) getFragmentManager().findFragmentById(R.id.fragmentTwo);
        fragment.displayAct(position);
    }
}
