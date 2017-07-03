package vobis.example.com.gamification;

import android.app.Activity;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.TextView;


import junit.framework.Assert;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static org.robolectric.util.FragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
//@Config( sdk = 21)
public class GalleryFragmentTest{

    @Test
    public void testLaunches(){
        /*
        vobis.example.com.gamification.gallery.Gallery activity = Robolectric.setupActivity( vobis.example.com.gamification.gallery.Gallery.class);
        vobis.example.com.gamification.gallery.GalleryFragment fragment = new vobis.example.com.gamification.gallery.GalleryFragment();
        startFragment(fragment);
        activity.getFragmentManager().beginTransaction().add(R.id.gallery_layout,fragment).commit();


        Button restart_btn = (Button) fragment.getView().findViewById(R.id.restart_btn);
        TextView messageArea = (TextView) fragment.getView().findViewById(R.id.message_area);
        assertEquals(messageArea.getText(), vobis.example.com.gamification.gallery.GalleryMessages.welcomeMsg);
        restart_btn.performClick();
        //assertEquals(messageArea.getText(), GalleryMessages.welcomeMsg);
        */
    }
}

