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

    public void moveLeft() throws TileDesc.WrongTileException {
        if(mVerticalIndex > 0){
            mVerticalIndex --;
            try {
                mGameMap.setSelectedTile(mHorizontalIndex, mVerticalIndex);
            } catch (TileView.NotInViewAreaException e) {
                mVerticalIndex ++;
            }
        }
    }

    public void moveRight() throws TileDesc.WrongTileException {
        if(mVerticalIndex < GameMap.COLUMNS_AMOUNT - 1){
            mVerticalIndex ++;
            try {
                mGameMap.setSelectedTile(mHorizontalIndex, mVerticalIndex);
            } catch (TileView.NotInViewAreaException e) {
                mVerticalIndex --;
            }
        }
    }

    public void moveUp() throws TileDesc.WrongTileException {
        if(mHorizontalIndex > 0){
            mHorizontalIndex --;
        }
        else{
            mHorizontalIndex = GameMap.ROWS_AMOUNT;
        }
        try {
            mGameMap.setSelectedTile(mHorizontalIndex, mVerticalIndex);
        } catch (TileView.NotInViewAreaException e) {
            System.out.println("up: rollback to old position");
            mHorizontalIndex = mHorizontalIndex == GameMap.ROWS_AMOUNT ? 0 : mHorizontalIndex + 1;
        }
    }

    public void moveDown() throws TileDesc.WrongTileException {
        if(mHorizontalIndex < GameMap.ROWS_AMOUNT){
            mHorizontalIndex ++;
        }
        else{
            mHorizontalIndex = 0;
        }
        try {
            mGameMap.setSelectedTile(mHorizontalIndex, mVerticalIndex);
        }catch (TileView.NotInViewAreaException e) {
            System.out.println("down: rollback to old position");
            mHorizontalIndex = mHorizontalIndex ==  0 ? GameMap.ROWS_AMOUNT  : mHorizontalIndex - 1;
        }
    }

    public void accept() throws TileDesc.WrongTileException  {
        mGameMap.acceptTile(mHorizontalIndex, mVerticalIndex);
    }
}
