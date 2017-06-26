package vobis.example.com.gamification;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
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
