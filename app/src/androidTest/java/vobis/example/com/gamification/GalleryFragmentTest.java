package vobis.example.com.gamification;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.TextView;

import org.junit.runner.RunWith;

import static org.mockito.Mockito.when;


public class GalleryFragmentTest extends ActivityUnitTestCase<Gallery> {

    public GalleryFragmentTest() {
        super(Gallery.class);
    }

    private GalleryFragment fragment;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        fragment = new GalleryFragment();
       // getActivity().getFragmentManager().beginTransaction().add(1,fragment,null).commit();
    }

    @MediumTest
    public void testLaunches(){
        /*Button restart_btn = (Button) fragment.getView().findViewById(R.id.restart_btn);
        TextView messageArea = (TextView) fragment.getView().findViewById(R.id.message_area);
        assertEquals(messageArea.getText(), GalleryMessages.welcomeMsg);
        restart_btn.performClick();
        assertEquals(messageArea.getText(), GalleryMessages.restartWelcomeMsg);*/
    }
}

