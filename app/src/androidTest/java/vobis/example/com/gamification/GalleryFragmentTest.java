package vobis.example.com.gamification;

import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import org.junit.runner.RunWith;

import vobis.example.com.gamification.gallery.Gallery;
import vobis.example.com.gamification.gallery.GalleryFragment;


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

