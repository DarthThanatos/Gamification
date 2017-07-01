package vobis.example.com.gamification.navdraw;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vobis.example.com.gamification.R;

public class PlanetFragment extends Fragment {
    public static final String PLANET_FRAGMENT_ARG = "planet arg";



    public PlanetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
        int i = getArguments().getInt(PLANET_FRAGMENT_ARG);
        String planetName = getResources().getStringArray(R.array.planets_names)[i];
        int imageId = getResources().getIdentifier(planetName.toLowerCase(), "drawable", getActivity().getPackageName());
        ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
        getActivity().setTitle(planetName);
        return rootView;
    }
}
