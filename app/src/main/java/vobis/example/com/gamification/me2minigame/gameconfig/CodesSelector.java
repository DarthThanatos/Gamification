package vobis.example.com.gamification.me2minigame.gameconfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodesSelector {

    private Integer[] selection;

    public CodesSelector(){
        List list = Arrays.asList(0,1, 2, 3, 4, 5);
        Collections.shuffle(list);
        selection = Arrays.copyOf(list.toArray(), 3, Integer[].class);
    }

    public Integer[] getSelection(){
        return selection;
    }
}
