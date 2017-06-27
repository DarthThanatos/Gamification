package vobis.example.com.gamification;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class GalleryFragment extends Fragment implements View.OnClickListener {

    private OnGalleryFragmentInteractionListener mListener;
    private CountDownTimer cdt;
    private int countDownInterval = 1000, duration = 30000;


    TextView messageArea;

    public GalleryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setButtonListener(View v, int btnId){
        Button btn = (Button) v.findViewById(btnId);
        btn.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        messageArea = (TextView) view.findViewById(R.id.message_area);
        messageArea.setText(GalleryMessages.welcomeMsg);
        for (int btnId: Arrays.asList(R.id.restart_btn, R.id.exit_btn)) setButtonListener(view, btnId);
        ((TextView)view.findViewById(R.id.message_area)).setTextColor(Color.BLACK);

        final TextView timerArea = (TextView) view.findViewById(R.id.timer_area);
        cdt = new CountDownTimer(duration, countDownInterval) {

            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished / 1000 < 10){
                    timerArea.setTextColor(Color.RED);
                }
                else{
                    timerArea.setTextColor(Color.BLUE);
                }
                timerArea.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerArea.setText("Time exceeded!");
                messageArea.setText(GalleryMessages.failMsg);
                mListener.onGameOver();
            }
        }.start();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnGalleryFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public boolean checkWin(ArrayList<GridViewItem> imageParts){
        System.out.println("Checking win conditions");

        for (GridViewItem imagePart : imageParts){
            System.out.print(imagePart.getIndex() + ", ");
        }
        System.out.println();

        for (int i = 0; i < imageParts.size(); i++){
            GridViewItem imagePart = imageParts.get(i);
            if(imagePart.getIndex() != i) return false;
        }

        //now we know that everything is at the correct place, set farewell msg
        TextView messageArea = (TextView) getView().findViewById(R.id.message_area);
        messageArea.setText(GalleryMessages.winMsg);
        messageArea.setTextColor(Color.BLUE);
        cdt.cancel();
        return true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("On detach fragment gallery");
        mListener = null;
        cdt.cancel();
    }

    public void setMessageAreaText(String content){
        messageArea.setText(content);
    }

    public void restartPuzzle(View view) {
        mListener.onGameReset();
    }

    public void exitPuzzle(View view) {
        mListener.onGameExit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.restart_btn:
                restartPuzzle(v);
                break;
            case R.id.exit_btn:
                exitPuzzle(v);
                break;
            default:
                System.out.println("Not recognized clicked item");
                break;
        }
    }

    public interface OnGalleryFragmentInteractionListener {
        public void onGameReset();
        public void onGameExit();
        public void onGameOver();
    }

}
