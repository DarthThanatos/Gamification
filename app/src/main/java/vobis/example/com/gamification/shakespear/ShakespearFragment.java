package vobis.example.com.gamification.shakespear;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vobis.example.com.gamification.R;
import vobis.example.com.gamification.shakespear.Shakespeare;


/**
 * A placeholder fragment containing a simple view.
 */
public class ShakespearFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_shakespear, container, false);
        TextView textView = (TextView) view.findViewById(R.id.root_display_area);
        textView.setText(Shakespeare.WELCOME_MSG);
        return view;
    }

    public void displayAct(int position){
        TextView textView = (TextView) getActivity().findViewById(R.id.root_display_area);
        String actContent = Shakespeare.DIALOGUE[position];
        textView.setText(actContent);
    }
}
