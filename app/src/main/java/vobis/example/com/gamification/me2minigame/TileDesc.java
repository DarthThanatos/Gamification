package vobis.example.com.gamification.me2minigame;

public class TileDesc {

    private boolean mFailTile = false;
    private boolean mSelected = false;
    private boolean mMarked = false;

    private int mCodeResourceIndex;

    public class WrongTileException extends Exception{

        public WrongTileException(String msg){
            super(msg);
        }
    }

    public TileDesc(boolean failTile, int codeResourceIndex){
        mFailTile = failTile;
        mCodeResourceIndex = codeResourceIndex;
    }

    public int getmCodeResourceIndex(){
        return mCodeResourceIndex;
    }

    public boolean getMarked(){
        return mMarked;
    }

    public boolean getSelected(){
        return mSelected;
    }

    public void setSelected(boolean selected) throws WrongTileException{
        mSelected = selected;
        if (selected){
            if(mFailTile) throw new WrongTileException("You messed with the wrong tile");
        }
        else{

        }
    }

}
