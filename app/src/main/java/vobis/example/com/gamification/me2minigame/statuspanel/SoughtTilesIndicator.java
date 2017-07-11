package vobis.example.com.gamification.me2minigame.statuspanel;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import vobis.example.com.gamification.R;
import vobis.example.com.gamification.me2minigame.GameMap;
import vobis.example.com.gamification.me2minigame.MEGameArea;
import vobis.example.com.gamification.me2minigame.MEMiniGameActivity;

public class SoughtTilesIndicator extends FrameLayout{

    private MEMiniGameActivity mContext;
    private ImageSwitcher mCodesHolder;

    private int mIndex = 0;

    public SoughtTilesIndicator(Context context) {
        super(context);
        mContext = (MEMiniGameActivity) context;

        int mWidth = StatusPanel.WIDTH/StatusPanel.CHILDREN_AMOUNT;
        RelativeLayout.LayoutParams parentLayout = new RelativeLayout.LayoutParams(mWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        parentLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        setLayoutParams(parentLayout);


        mCodesHolder = new ImageSwitcher(mContext);
        int padding = 15;
        mCodesHolder.setPadding(padding,padding,padding,padding);

        for (int i = 0; i < 2; i++) mCodesHolder.addView(new ImageView(mContext));
        Animation inAnim = AnimationUtils.loadAnimation(mContext, R.anim.in_anim);
        Animation outAnim = AnimationUtils.loadAnimation(mContext, R.anim.out_anim);
        mCodesHolder.setInAnimation(inAnim);
        mCodesHolder.setOutAnimation(outAnim);
        addView(mCodesHolder);
        setNextToFind();
        setForegroundGravity(Gravity.CENTER);
    }

    public void setNextToFind(){
        int indexOfResource;
        int resourceId;
        if(mIndex !=3){
            indexOfResource = mContext.mSelectedConfig.getSelector().getSelection()[mIndex++];
            mContext.mSelectedConfig.getSelector().setSoughtIndex(mIndex-1);
            resourceId = GameMap.idToResource[indexOfResource];
        }
        else{
            resourceId = R.drawable.ic_check;
            mContext.gameWon("Congratulations! You won");
        }
        mCodesHolder.setImageResource(resourceId);
    }
}
