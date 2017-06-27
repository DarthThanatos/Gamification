package vobis.example.com.gamification;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }


    @MediumTest
    public void testLaunches(){
        startActivity(new Intent(Intent.ACTION_MAIN), null, null);
        Button btn = (Button) getActivity().findViewById(R.id.gamefication);
        btn.performClick();
        assertNotNull(getStartedActivityIntent());
    }
}
