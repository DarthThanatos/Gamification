package vobis.example.com.gamification.me2minigame;

public class TileDesc {

    private boolean mFailTile = false; //if this tile is a trap
    private boolean mSelected = false; // set if user selects it - after set to true, it cannot be further selected
    private boolean mSought = false; //

    private TileView mTileView;
    private int mCodeResourceIndex;

    public class WrongTileException extends Exception{

        public WrongTileException(String msg){
            super(msg);
        }
    }

    public TileDesc(boolean failTile, boolean sought,int codeResourceIndex){
        mFailTile = failTile;
        mCodeResourceIndex = codeResourceIndex;
        mSought = sought;
    }

    public void setSought(boolean sought){
        mSought = sought;
    }

    public void setView(TileView tileView){
     mTileView = tileView;
    }

    public TileView getView(){
        return mTileView;
    }

    public int getmCodeResourceIndex(){
        return mCodeResourceIndex;
    }

    public void setCodeResourceIndex(int codeResourceIndex){
        mCodeResourceIndex = codeResourceIndex;
        if(mTileView == null) return;
        mTileView.updateBmp(codeResourceIndex);
    }

    public boolean isSought(){
        return mSought;
    }

    public void setFail( boolean failTile){
        mFailTile = failTile;
    }

    public boolean getSelected(){
        return mSelected;
    }

    public void setSelected(boolean selected) throws WrongTileException, TileView.NotInViewAreaException {
        System.out.println("Selecting in set selected");
        mTileView.setSelectedInBounds(selected);
        mSelected = selected;
        if (selected){
            if(mFailTile) throw new WrongTileException("You messed with the wrong tile");
        }
    }


    public void accept() throws WrongTileException {
        if (!mSought) throw  new WrongTileException("Not accepted");
    }

}
