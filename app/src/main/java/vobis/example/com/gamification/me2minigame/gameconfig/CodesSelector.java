package vobis.example.com.gamification.me2minigame.gameconfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodesSelector {

    private Integer[] selection;
    private  int currentSoughtIndex = 0;

    public CodesSelector(){
        List list = Arrays.asList(0,1, 2, 3, 4, 5);
        Collections.shuffle(list);
        selection = Arrays.copyOf(list.toArray(), 3, Integer[].class);
    }

    public void setSoughtIndex(int soughtIndex){
        currentSoughtIndex = soughtIndex;
    }

    public int getCurrentSoughtIndex(){
        return currentSoughtIndex;
    }

    public Integer[] getSelection(){
        return selection;
    }

    public int getCurrentResId(){
        return selection[currentSoughtIndex];
    }
}
