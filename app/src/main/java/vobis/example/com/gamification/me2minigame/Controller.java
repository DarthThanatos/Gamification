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
        moveDown();
        moveRight();
    }

    public void moveLeft(){
        if(mVerticalIndex > 0){
            mVerticalIndex --;
            try {
                mGameMap.setSelectedTile(mHorizontalIndex, mVerticalIndex);
            } catch (TileDesc.WrongTileException e) {
                e.printStackTrace();
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
        }
    }

    public void accept(){

    }
}
