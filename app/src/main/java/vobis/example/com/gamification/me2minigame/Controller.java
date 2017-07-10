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
        meGameArea.fillRowsWithContent(mGameMap);
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
            try {
                mGameMap.setSelectedTile(mHorizontalIndex, mVerticalIndex);
            } catch (TileDesc.WrongTileException e) {
                e.printStackTrace();
            }
        }
    }

    public void moveDown(){
        if(mHorizontalIndex < GameMap.ROWS_AMOUNT){
            mHorizontalIndex ++;
            try {
                mGameMap.setSelectedTile(mHorizontalIndex, mVerticalIndex);
            } catch (TileDesc.WrongTileException e) {
                e.printStackTrace();
            }
        }
    }

    public void accept(){

    }
}
