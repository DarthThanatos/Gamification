package vobis.example.com.gamification.mainactivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.StackView;

public class CustomStackView extends StackView {
    public CustomStackView(Context context) {
        super(context);
    }
    public CustomStackView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CustomStackView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void setDisplayedChild(int whichChild)
    {
        //this.getOnItemSelectedListener().onItemSelected(this, null, whichChild, -1);
        System.out.println("Listener, pos: " + whichChild);
        super.setDisplayedChild(whichChild);
    }

}
