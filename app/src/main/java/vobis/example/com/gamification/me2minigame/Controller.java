package vobis.example.com.gamification.me2minigame;

import vobis.example.com.gamification.me2minigame.gameconfig.GameConfig;

public class Controller {

    private int mVerticalIndex=0, mHorizontalIndex=0;
    private GameMap mGameMap;
    private GameConfig mGameConfig;
    private MEGameArea mGameArea;

    public Controller (MEGameArea meGameArea, GameConfig gameConfig){
        mGameConfig = gameConfig;
        mGameArea = meGameArea;
        mGameMap = new GameMap(gameConfig);
        meGameArea.fillRowsWithContent(mGameMap, this);
    }

    public void moveLeft(){
        if(mVerticalIndex > 0){
            mVerticalIndex --;
            try {
                mGameMap.setSelectedTile(mHorizontalIndex, mVerticalIndex);
            } catch (TileDesc.WrongTileException e) {
                e.printStackTrace();
            } catch (TileView.NotInViewAreaException e) {
                mVerticalIndex ++;
            }
        }
    }

    public void moveRight(){
        if(mVerticalIndex < GameMap.COLUMNS_AMOUNT - 1){
            mVerticalIndex ++;
            try {
                mGameMap.setSelectedTile(mHorizontalIndex, mVerticalIndex);
            } catch (TileDesc.WrongTileException e) {
                e.printStackTrace();
            } catch (TileView.NotInViewAreaException e) {
                mVerticalIndex --;
            }
        }
    }

    public void moveUp(){
        if(mHorizontalIndex > 0){
            mHorizontalIndex --;
        }
        else{
            mHorizontalIndex = GameMap.ROWS_AMOUNT;
        }
        try {
            mGameMap.setSelectedTile(mHorizontalIndex, mVerticalIndex);
        } catch (TileDesc.WrongTileException e) {
            e.printStackTrace();
        } catch (TileView.NotInViewAreaException e) {
            mHorizontalIndex = mHorizontalIndex == GameMap.ROWS_AMOUNT ? 0 : mHorizontalIndex + 1;
        }
    }

    public void moveDown(){
        if(mHorizontalIndex < GameMap.ROWS_AMOUNT){
            mHorizontalIndex ++;
        }
        else{
            mHorizontalIndex = 0;
        }
        try {
            mGameMap.setSelectedTile(mHorizontalIndex, mVerticalIndex);
        } catch (TileDesc.WrongTileException e) {
            e.printStackTrace();
        } catch (TileView.NotInViewAreaException e) {
            mHorizontalIndex = mHorizontalIndex ==  0 ? GameMap.ROWS_AMOUNT  : mHorizontalIndex - 1;
        }
    }

    public void accept(){

    }
}
